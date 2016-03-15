import com.google.gson.annotations.SerializedName;

import java.util.Scanner;

/**
 * Created by Никита on 24.02.2016.
 */
public class Message {
    @SerializedName("id")
    public String id;
    @SerializedName("author")
    public String author;
    @SerializedName("timestamp")
    public long timestamp;
    @SerializedName("message")
    public String message;

    @Override
    public String toString() {
        return "\nid : " + id + "\nauthor : " + author + "\ntimestamp : " + timestamp + "\nmessage : " + message;
    }

    public void put(Scanner sc) {
        String str;
        System.out.println("Author:");
        str = sc.next();
        author = str;
        System.out.println("Input message:");
        str = sc.next();
        message = str;
        System.out.println("id");
        str = sc.next();
        id = str;
        System.out.println("timestamp");
        str = sc.next();
        timestamp = Long.valueOf(str);
    }

    public String getID() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
    public String getAuthor(){
        return author;
    }
}
