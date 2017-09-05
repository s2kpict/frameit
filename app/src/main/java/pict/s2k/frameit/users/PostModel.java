package pict.s2k.frameit.users;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import pict.s2k.frameit.FirebaseTreeConstants;

/**
 * Created by suryaa on 5/9/17.
 */

public class PostModel {

    private String information ;
    private double price ;
    private int likes ;
    private long time_when_posted ;

    PostModel(){}

    public PostModel(String information, double price, int likes, long time_when_posted) {
        this.information = information;
        this.price = price;
        this.likes = likes;
        this.time_when_posted = time_when_posted;
    }

    /*Example
    DatabaseReference userNodeReference = FirebaseDatabase.getInstance().getReference()
                        .child(FirebaseTreeConstants.USER)
                        .child("uid4MZONEXEVM6U6V3M5T")
                        .child("post")
                        .child("post_1") ;
                PostModel.addPhoto(userNodeReference,"http://cdn3-www.dogtime.com/assets/uploads/2011/01/file_23262_entlebucher-mountain-dog-300x189.jpg");

     */
    public static void addPhoto(DatabaseReference pathUntillPost,String urlOfPhoto){
        pathUntillPost
                .child(FirebaseTreeConstants.PHOTOS)
                .push()
                .setValue(urlOfPhoto) ;
    }
    /*Example
       DatabaseReference userNodeReference = FirebaseDatabase.getInstance().getReference()
                        .child(FirebaseTreeConstants.USER)
                        .child("uid4MZONEXEVM6U6V3M5T")
                        .child("post")
                        .child("post_1") ;
                PostModel.Comment comment = new PostModel.Comment("uidPJKFIJ6O7UJRAD4NVN","Short and Sweet Message") ;
                PostModel.addComment(userNodeReference,comment);
     */
    public static void addComment(DatabaseReference pathUntillPost,PostModel.Comment comment) {
        pathUntillPost
                .child(FirebaseTreeConstants.COMMENTS)
                .push()
                .setValue(comment) ;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getTime_when_posted() {
        return time_when_posted;
    }

    public void setTime_when_posted(long time_when_posted) {
        this.time_when_posted = time_when_posted;
    }

    public static class Comment {
        private String uid ;
        private String message ;

        Comment(){}

        public Comment(String uid, String message) {
            this.uid = uid;
            this.message = message;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
