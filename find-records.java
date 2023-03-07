import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RequestOptions;

import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.unit.TimeValue;

import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.search.aggregations.AggregationBuilders;

import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;

import org.elasticsearch.search.aggregations.bucket.composite.CompositeKey;

import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;

import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import org.elasticsearch.search.builder.SearchSourceBuilder;



import java.io.IOException;

import java.util.ArrayList;

import java.util.List;



public class UniqueRecordsExample {

    

    public static void main(String[] args) throws IOException {

        // create a new instance of the OpenSearch High Level REST Client

        RestHighLevelClient client = new RestHighLevelClient(...);



        // define the fields to group by

        String[] groupByFields = {"policy_number", "policy_holder_name", "dob", "contact"};



        // build the composite aggregation

        CompositeAggregationBuilder compositeAggregation = AggregationBuilders.composite("unique_policies")

            .sources(getCompositeSources(groupByFields));



        // build the terms aggregation to count the number of unique records

        TermsAggregationBuilder countAggregation = AggregationBuilders.terms("count").field("_id");



        // build the search request

        SearchRequest searchRequest = new SearchRequest("my_policy");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()

            .size(0)

            .query(QueryBuilders.matchAllQuery())

            .aggregation(compositeAggregation.subAggregation(countAggregation));

        searchRequest.source(searchSourceBuilder);



        // execute the search request

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);



        // process the search response to extract the unique records

        List<CompositeKey> uniqueKeys = new ArrayList<>();

        for (CompositeKey compositeKey : searchResponse.getAggregations().get("unique_policies")) {

            uniqueKeys.add(compositeKey);

        }



        // print the number of unique records

        System.out.println("Number of unique records: " + uniqueKeys.size());



        // close the client

        client.close();

    }



    private static CompositeValuesSourceBuilder<?>[] getCompositeSources(String[] fields) {

        CompositeValuesSourceBuilder<?>[] sources = new CompositeValuesSourceBuilder<?>[fields.length];

        for (int i = 0; i < fields.length; i++) {

            sources[i] = new CompositeValuesSourceBuilder<>(fields[i]).order(CompositeValuesSourceBuilder.Order.term(true));

        }

        return sources;

    }

}

