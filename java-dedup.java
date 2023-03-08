import org.json.JSONArray;

import org.json.JSONObject;



import java.util.Arrays;



public class Example {

  public static void main(String[] args) {

    String response = "{\"aggregations\":{\"categories\":{\"buckets\":[{\"key\":\"test1234,test1233\",\"doc_count\":10},{\"key\":\"test1234,test126\",\"doc_count\":8},{\"key\":\"test1234\",\"doc_count\":6}]}}}";



    JSONObject json = new JSONObject(response);

    JSONArray buckets = json.getJSONObject("aggregations").getJSONObject("categories").getJSONArray("buckets");



    buckets.forEach(bucket -> {

      String key = bucket.getString("key");

      int docCount = bucket.getInt("doc_count");



      Arrays.stream(key.split(","))

            .filter(subKey -> subKey.contains("test123"))

            .forEach(subKey -> System.out.println(subKey + ": " + docCount));

    });

  }

}

