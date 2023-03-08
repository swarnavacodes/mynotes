import java.io.IOException;

import org.apache.http.HttpHost;

import org.apache.http.entity.ContentType;

import org.apache.http.nio.entity.NStringEntity;

import org.elasticsearch.client.Request;

import org.elasticsearch.client.Response;

import org.elasticsearch.client.RestClient;



public class Example {



    public static void main(String[] args) throws IOException {



        // Create the REST client

        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();



        // Create the request

        String endpoint = "/test_index/_search";

        String requestBody = "{\n" +

                "  \"size\": 0,\n" +

                "  \"query\": {\n" +

                "    \"wildcard\": {\n" +

                "      \"field1\": \"*test123*\"\n" +

                "    }\n" +

                "  },\n" +

                "  \"aggs\": {\n" +

                "    \"categories\": {\n" +

                "      \"terms\": {\n" +

                "        \"size\": 100000,\n" +

                "        \"field\": \"field1\"\n" +

                "      }\n" +

                "    }\n" +

                "  }\n" +

                "}";

        Request request = new Request("GET", endpoint);

        request.setEntity(new NStringEntity(requestBody, ContentType.APPLICATION_JSON));



        // Execute the request

        Response response = restClient.performRequest(request);



        // Print the response

        System.out.println(response.getStatusLine());

        System.out.println(EntityUtils.toString(response.getEntity()));

        

        // Close the client

        restClient.close();

    }

}

