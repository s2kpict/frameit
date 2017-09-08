package pict.s2k.frameit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

import pict.s2k.frameit.auth.AuthenticationBaseCode;
import pict.s2k.frameit.users.PostModel;
import pict.s2k.frameit.users.UploadWorkModel;
import pict.s2k.frameit.users.UserModel;

import pict.s2k.frameit.fragments.ExploreFragment;
import pict.s2k.frameit.fragments.WishListFragment;

public class MainActivity extends AppCompatActivity{
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction transaction;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content,new WishListFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content,new ExploreFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }

            return false;
        }

    };
    void setDefaultFragment(){
        transaction.replace(R.id.content,new ExploreFragment()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference userNodeReference = FirebaseDatabase.getInstance().getReference()
                        .child(FirebaseTreeConstants.USER)
                        .child("uid4MZONEXEVM6U6V3M5T")
                        .child("post")
                        .child("post_1") ;
                PostModel.Comment comment = new PostModel.Comment("uidPJKFIJ6O7UJRAD4NVN","Short and Sweet Message") ;
                PostModel.addComment(userNodeReference,comment);
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        transaction=fragmentManager.beginTransaction();
        setDefaultFragment();
    }

    //For Adding User
    public void tryCode() {
        UserModel.BasicPersonalInformation basicPersonalInformation = new UserModel.BasicPersonalInformation(8087583519L)  ;
        UserModel.Photo photo = new UserModel.Photo("http://cdn3-www.dogtime.com/assets/uploads/2011/01/file_23262_entlebucher-mountain-dog-300x189.jpg") ;
        AuthenticationBaseCode.createAFreshNewAccount("uid" + getSaltString() , basicPersonalInformation,photo);
    }

    //for adding PostModel
    public void tryCode1() {
        final PostModel post = new PostModel("Information",400.0,2,18900088000L) ;
        final DatabaseReference userNodeReference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseTreeConstants.USER)
                .child("uid4MZONEXEVM6U6V3M5T") ;

        userNodeReference
                .child(FirebaseTreeConstants.SETTINGS)
                .child(FirebaseTreeConstants.COUNTERS)
                .child(FirebaseTreeConstants.POST_COUNTERS)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int counterValue = dataSnapshot.getValue(Integer.class) ;
                        final int cV = ++counterValue ;
                        userNodeReference
                                .child(FirebaseTreeConstants.SETTINGS)
                                .child(FirebaseTreeConstants.COUNTERS)
                                .child(FirebaseTreeConstants.POST_COUNTERS)
                                .setValue(cV)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        userNodeReference
                                                .child(FirebaseTreeConstants.POST)
                                                .child(FirebaseTreeConstants.POST + "_" + cV )
                                                .setValue(post)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(MainActivity.this, "Posted A New PostModel", Toast.LENGTH_SHORT).show();
                                                    }
                                                }) ;
                                    }
                                }) ;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    //for adding uploadWork
    private void tryCode2(){
        ArrayList<String> listOfPhotos = new ArrayList<>() ;
        listOfPhotos.add("http://cdn3-www.dogtime.com/assets/uploads/2011/01/file_23262_entlebucher-mountain-dog-300x189.jpg");
        listOfPhotos.add("http://cdn3-www.dogtime.com/assets/uploads/2011/01/file_23262_entlebucher-mountain-dog-300x189.jpg") ;
        final UploadWorkModel work = new UploadWorkModel("uidPJKFIJ6O7UJRAD4NVN","Short and Sweet Message",20,listOfPhotos) ;
        final DatabaseReference userNodeReference = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseTreeConstants.USER)
                .child("uid4MZONEXEVM6U6V3M5T") ;

        userNodeReference
                .child(FirebaseTreeConstants.SETTINGS)
                .child(FirebaseTreeConstants.COUNTERS)
                .child(FirebaseTreeConstants.WORK_COUNTERS)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int counterValue = dataSnapshot.getValue(Integer.class) ;
                        final int cV = ++counterValue ;
                        userNodeReference
                                .child(FirebaseTreeConstants.SETTINGS)
                                .child(FirebaseTreeConstants.COUNTERS)
                                .child(FirebaseTreeConstants.WORK_COUNTERS)
                                .setValue(cV)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        userNodeReference
                                                .child(FirebaseTreeConstants.UPLOAD_WORK)
                                                .child(FirebaseTreeConstants.UPLOAD_WORK + "_" + cV )
                                                .push()
                                                .setValue(work)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(MainActivity.this, "Posted A New workmodel", Toast.LENGTH_SHORT).show();
                                                    }
                                                }) ;
                                    }
                                }) ;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
