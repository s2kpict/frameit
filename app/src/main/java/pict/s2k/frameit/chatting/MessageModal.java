package pict.s2k.frameit.chatting;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by suryaa on 3/9/17.
 */

public class MessageModal {

    private String fromUID ;
    private String toUID ;
    private long   timeWhenSend ;
    private long   timeWhenRecieved ;
    private String message ;

    public MessageModal(String fromUID, String toUID, long timeWhenSend, long timeWhenRecieved, String message) {
        this.fromUID = fromUID;
        this.toUID = toUID;
        this.timeWhenSend = timeWhenSend;
        this.timeWhenRecieved = timeWhenRecieved;
        this.message = message;
    }

    public String getFromUID() {
        return fromUID;
    }

    public void setFromUID(String fromUID) {
        this.fromUID = fromUID;
    }

    public String getToUID() {
        return toUID;
    }

    public void setToUID(String toUID) {
        this.toUID = toUID;
    }

    public long getTimeWhenSend() {
        return timeWhenSend;
    }

    public void setTimeWhenSend(long timeWhenSend) {
        this.timeWhenSend = timeWhenSend;
    }

    public long getTimeWhenRecieved() {
        return timeWhenRecieved;
    }

    public void setTimeWhenRecieved(long timeWhenRecieved) {
        this.timeWhenRecieved = timeWhenRecieved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
