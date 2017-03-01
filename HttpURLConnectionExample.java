package com.company;

import json.JSONArray;
import json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();
        http.getApptoken();

    }


    // HTTP GET request
    private void getApptoken() throws Exception {

        //  String TargetUrl = "https://api.ebay.com/identity/v1/oauth2/token";
        String TargetUrl = "https://api.sandbox.ebay.com/identity/v1/oauth2/token";

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(TargetUrl);
        postRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
        postRequest.addHeader("Authorization", "Basic RWJyaWRnZUwtZWJyaWRnZS1TQlgtZWU2ZWQ4MDYzLWU2ODQ4NDliOlNCWC1lNmVkODA2Mzg4Y2YtOWZlOC00ZGNiLTk5MDctMTU0OQ==");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("scope", "https://api.ebay.com/oauth/api_scope"));
        params.add(new BasicNameValuePair("redirect_uri", "Ebridge_LLC-EbridgeL-ebridg-lkijna"));
        postRequest.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));


        CloseableHttpResponse response = client.execute(postRequest);

        String bodyAsString = EntityUtils.toString(response.getEntity());
        JSONObject myObject = new JSONObject(response.getEntity());

        System.out.println("Here is response " + bodyAsString);
        System.out.println("\n" + "Json" + myObject);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                instream.close();
            }
        } finally {
            response.close();
        }
    }
}
