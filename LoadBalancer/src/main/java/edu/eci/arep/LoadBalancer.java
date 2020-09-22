package edu.eci.arep;

import okhttp3.*;

import java.io.IOException;


/**
 * The type Load balancer.
 */
public class LoadBalancer {

    private final OkHttpClient httpClient;
    private final String baseUrl = "http://172.17.0.1";
    //    private final String baseUrl = "http://127.0.0.1";
    private final String[] ports = {":4444", ":4445", ":4446"};
    /**
     * The constant JSON.
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private int serverNumber = 0;


    /**
     * Instantiates a new Load balancer.
     */
    public LoadBalancer() {
        httpClient = new OkHttpClient();
    }


    /**
     * Gets messages.
     *
     * @param path the path
     * @return the messages
     * @throws IOException the io exception
     */
    public String getMessages(String path) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + ports[serverNumber] + path)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Post message string.
     *
     * @param message the message
     * @param path    the path
     * @return the string
     * @throws IOException the io exception
     */
    public String postMessage(String message, String path) throws IOException {
        RequestBody body = RequestBody.create(message, JSON);
        Request request = new Request.Builder()
                .url(baseUrl + ports[serverNumber] + path)
                .post(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Round robin.
     */
    public void roundRobin() {
        this.serverNumber = (this.serverNumber + 1) % ports.length;
    }
}
