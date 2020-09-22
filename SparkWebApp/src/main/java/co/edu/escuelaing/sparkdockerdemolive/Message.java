package co.edu.escuelaing.sparkdockerdemolive;

import java.util.Date;

/**
 * The type Message.
 */
public class Message {
    private String Message;
    private Date date;

    /**
     * Instantiates a new Message.
     *
     * @param message the message
     * @param date    the date
     */
    public Message(String message, Date date) {
        Message = message;
        this.date = date;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        Message = message;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
