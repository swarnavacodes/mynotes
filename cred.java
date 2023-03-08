CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
credentialsProvider.setCredentials(AuthScope.ANY,
    new UsernamePasswordCredentials("username", "password"));
RestClientBuilder builder = RestClient.builder(
    new HttpHost("localhost", 9200, "http"))
    .setHttpClientConfigCallback(httpClientBuilder ->
        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
RestClient restClient = builder.build();

