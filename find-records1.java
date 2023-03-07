import org.opensearch.action.search.SearchRequest;

import org.opensearch.action.search.SearchResponse;

import org.opensearch.client.RequestOptions;

import org.opensearch.client.RestHighLevelClient;

import org.opensearch.common.unit.TimeValue;

import org.opensearch.index.query.QueryBuilders;

import org.opensearch.search.SearchHit;

import org.opensearch.search.aggregations.AggregationBuilders;

import org.opensearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;

import org.opensearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;

import org.opensearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import org.opensearch.search.builder.SearchSourceBuilder;



import java.io.IOException;



public class UniquePoliciesSearch {

    

    private static final String INDEX_NAME = "my_policy";

    

    public static void main(String[] args) {

        String[] fields = {"policy_number.keyword", "policy_holder_name.keyword", "dob", "contact.keyword"};

        

        try (RestHighLevelClient client = new RestHighLevelClient()) {

            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);

            

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            searchSourceBuilder.size(0);

            

            CompositeAggregationBuilder compositeAggregationBuilder = AggregationBuilders.composite("unique_policies", getCompositeSources(fields));

            searchSourceBuilder.aggregation(compositeAggregationBuilder);

            

            searchRequest.source(searchSourceBuilder);

            

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            

            CompositeAggregationBuilder aggregation = searchSourceBuilder.aggregation(compositeAggregationBuilder);

            

            for (CompositeAggregationBuilder.Entry bucket : searchResponse.getAggregations().get(compositeAggregationBuilder.getName()).getBuckets()) {

                System.out.println("Policy Number: " + bucket.getKey().get("policy_number.keyword") + 

                                   ", Policy Holder Name: " + bucket.getKey().get("policy_holder_name.keyword") + 

                                   ", DOB: " + bucket.getKey().get("dob") + 

                                   ", Contact: " + bucket.getKey().get("contact.keyword") + 

                                   ", Count: " + bucket.getDocCount());

            }

            

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    

    private static CompositeValuesSourceBuilder<?>[] getCompositeSources(String[] fields) {

        CompositeValuesSourceBuilder<?>[] sources = new CompositeValuesSourceBuilder<?>[fields.length];

        for (int i = 0; i < fields.length; i++) {

            sources[i] = new CompositeValuesSourceBuilder<>(fields[i]).order(CompositeValuesSourceBuilder.Order.term(true));

        }

        return sources;

    }

    

}

