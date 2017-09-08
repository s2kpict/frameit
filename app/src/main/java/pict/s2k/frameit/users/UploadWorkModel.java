package pict.s2k.frameit.users;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import pict.s2k.frameit.FirebaseTreeConstants;

/**
 * Created by suryaa on 5/9/17.
 */

public class UploadWorkModel {
    private String to ;
    private String short_msg ;
    private int day_no ;
    private ArrayList<String> photosList ;

    UploadWorkModel(){}

    public UploadWorkModel(String to, String short_msg, int day_no , ArrayList<String> photosList) {
        this.to = to;
        this.short_msg = short_msg;
        this.day_no = day_no ;
        this.photosList = photosList ;
    }

    public static void addPhoto(DatabaseReference pathUntillPost, String urlOfPhoto){
        pathUntillPost
                .child(FirebaseTreeConstants.PHOTOS)
                .push()
                .setValue(urlOfPhoto) ;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getShort_msg() {
        return short_msg;
    }

    public void setShort_msg(String short_msg) {
        this.short_msg = short_msg;
    }

    public int getDay_no() {
        return day_no;
    }

    public void setDay_no(int day_no) {
        this.day_no = day_no;
    }
}
