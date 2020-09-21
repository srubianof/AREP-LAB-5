package co.edu.escuelaing.sparkdockerdemolive;

import java.util.Date;

public class Message {
    private String Message;
    private Date date;

    public Message(String message, Date date) {
        Message = message;
        this.date = date;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
