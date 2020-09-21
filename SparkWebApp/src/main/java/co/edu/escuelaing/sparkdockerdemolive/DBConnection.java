package co.edu.escuelaing.sparkdockerdemolive;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jdk.nashorn.api.scripting.JSObject;
import org.bson.Document;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DBConnection {
    private MongoClient mongoClient;
    private MongoClientURI uri;
    private Map<String, String> messagesMap;

    public DBConnection() {
        uri = new MongoClientURI(
                "mongodb://arep:arep@MongoDB:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=AREP&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
//                "mongodb://arep:arep@localhost:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=AREP&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }

    public void createMessage(String message) {
        MongoDatabase db = mongoClient.getDatabase("AREP");
        MongoCollection<Document> collection = db.getCollection("Messages");
        Document document = new Document();

        Gson gson = new Gson();
        Message newMessage = gson.fromJson(message, Message.class);

        document.put("Message", newMessage.getMessage());
        document.put("Date", new Date());
        collection.insertOne(document);
    }

    public ArrayList<Message> getMessages() {

        ArrayList<Message> messageArrayList = new ArrayList<>();
        MongoDatabase db = mongoClient.getDatabase("AREP");
        MongoCollection<Document> collection = db.getCollection("Messages");
        FindIterable iterable = collection.find();
        ArrayList<Document> documents = new ArrayList<>();
        iterable.into(documents);
        documents.forEach(t -> messageArrayList.add(new Message(t.get("Message").toString(), (Date) t.get("Date"))));
        return messageArrayList;
    }
}
