package co.edu.escuelaing.sparkdockerdemolive;

import com.google.gson.Gson;
import jdk.nashorn.api.scripting.JSObject;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import spark.*;

import java.util.*;

import static spark.Spark.*;

/**
 * The type Spark web server.
 */
public class SparkWebServer {

    private static DBConnection dbConnection = new DBConnection();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String... args) {
        port(getPort());

        try {
            get("/messages", (req, res) -> {
                res.status(200);
                res.type("application/json");
                return new Gson().toJson(dbConnection.getMessages());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            post("/messages", (req, res) -> {
                res.status(200);
                res.type("application/json");
                dbConnection.createMessage(req.body());
                return "";
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}

