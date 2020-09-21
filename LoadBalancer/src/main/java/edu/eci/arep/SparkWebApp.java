package edu.eci.arep;

import static spark.Spark.*;

public class SparkWebApp {
    private static LoadBalancer loadBalancer = new LoadBalancer();

    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("static");
        get("/", ((request, response) -> {
            response.redirect("index.html");
            return "";
        }));
        post("/messages", (request, response) -> {
            loadBalancer.roundRobin();
            loadBalancer.postMessage(request.body(), "/messages");
            return "";
        });
        get("/messages", (request, response) -> {
            loadBalancer.roundRobin();
            return loadBalancer.getMessages("/messages");
        });
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
