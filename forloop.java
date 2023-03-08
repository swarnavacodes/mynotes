import org.json.JSONArray;

import org.json.JSONObject;



public class Example {

  public static void main(String[] args) {

    String response = "{\"aggregations\":{\"categories\":{\"buckets\":[{\"key\":\"test1234,test1233\",\"doc_count\":10},{\"key\":\"test1234,test126\",\"doc_count\":8},{\"key\":\"test1234\",\"doc_count\":6}]}}}";



    JSONObject json = new JSONObject(response);

    JSONArray buckets = json.getJSONObject("aggregations").getJSONObject("categories").getJSONArray("buckets");



    for (int i = 0; i < buckets.length(); i++) {

      String key = buckets.getJSONObject(i).getString("key");

      int docCount = buckets.getJSONObject(i).getInt("doc_count");



      for (String subKey : key.split(",")) {

        if (subKey.contains("test123")) {

          System.out.println(subKey + ": " + docCount);

        }

      }

    }

  }

}

