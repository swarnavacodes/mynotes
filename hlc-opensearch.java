import org.apache.http.HttpHost;

import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RestClient;

import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.index.query.WildcardQueryBuilder;

import org.elasticsearch.search.aggregations.AggregationBuilders;

import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;



public class Example {



    public static void main(String[] args) throws Exception {



        // Create the REST client

        RestHighLevelClient client = new RestHighLevelClient(

                RestClient.builder(new HttpHost("localhost", 9200, "http")));



        // Create the search request

        SearchRequest searchRequest = new SearchRequest("test_index");

        WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("field1", "*test123*");

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("categories")

                .field("field1")

                .size(100000);

        searchRequest.source().query(queryBuilder).aggregation(aggregationBuilder);



        // Execute the request

        SearchResponse searchResponse = client.search(searchRequest);



        // Print the response

        System.out.println(searchResponse);



        // Close the client

        client.close();

    }

}

