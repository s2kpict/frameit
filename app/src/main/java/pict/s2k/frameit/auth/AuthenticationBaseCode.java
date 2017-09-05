package pict.s2k.frameit.auth;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pict.s2k.frameit.FirebaseTreeConstants;
import pict.s2k.frameit.ImageManipulation;
import pict.s2k.frameit.users.UserModel;

/**
 * Created by suryaa on 3/9/17.
 */

public class AuthenticationBaseCode {

    private static boolean accountCreated = false ;

    public static boolean createAccountWithEmailAndPassword(String email, String password,final Activity activity) {
        accountCreated = false ;
        FirebaseAuth mAuth = FirebaseAuth.getInstance() ;
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            accountCreated = true ;
                            AuthResult authResult = task.getResult() ;
                            FirebaseUser firebaseUser = authResult.getUser() ;

                            UserModel.BasicPersonalInformation basicPersonalInformation = new UserModel.BasicPersonalInformation(Long.parseLong(firebaseUser.getPhoneNumber(),10)) ;   ;
                            UserModel.Photo photo = new UserModel.Photo( ImageManipulation.storeAndGetThumbnail(firebaseUser.getPhotoUrl())) ;

                            createAFreshNewAccount(firebaseUser.getUid(),basicPersonalInformation,photo);
                            Toast.makeText(activity, "Created A new Acconunt", Toast.LENGTH_LONG).show() ;

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) ;
        if(accountCreated)
            return true ;
        else
            return false ;
    }

    public static boolean createAccountWithEmailAndPassword(String email, String password, final Activity activity, final String displayName , final String mobileNo , final Uri selectedProfilePhtoo) {
        accountCreated = false ;
        FirebaseAuth mAuth = FirebaseAuth.getInstance() ;
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            accountCreated = true ;
                            AuthResult authResult = task.getResult() ;
                            final FirebaseUser firebaseUser = authResult.getUser() ;
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(displayName)
                                    .setPhotoUri(selectedProfilePhtoo).build() ;
                            firebaseUser.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                UserModel.BasicPersonalInformation basicPersonalInformation = new UserModel.BasicPersonalInformation(Long.parseLong(mobileNo,10)) ;   ;
                                                UserModel.Photo photo = new UserModel.Photo(ImageManipulation.storeAndGetThumbnail(firebaseUser.getPhotoUrl())) ;

                                                createAFreshNewAccount(firebaseUser.getUid(),basicPersonalInformation,photo);
                                                Toast.makeText(activity, "Created A new Acconunt", Toast.LENGTH_LONG).show() ;
                                            }
                                        }
                                    }) ;

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) ;
        if(accountCreated)
            return true ;
        else
            return false ;
    }

    public static void createAFreshNewAccount(String uid , UserModel.BasicPersonalInformation basicPersonalInformation , UserModel.Photo photo) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference currentUserNodeReference = firebaseDatabase.getReference()
                .child(FirebaseTreeConstants.USER)
                .child(uid) ;
        currentUserNodeReference
                .child(FirebaseTreeConstants.PERSONAL_INFORMATION)
                .child(FirebaseTreeConstants.PERSONAL_INFORMATION_BASIC)
                .setValue(basicPersonalInformation) ;
        currentUserNodeReference
                .child(FirebaseTreeConstants.PERSONAL_INFORMATION)
                .child(FirebaseTreeConstants.PHOTOS)
                .setValue(photo) ;
        currentUserNodeReference
                .child(FirebaseTreeConstants.SETTINGS)
                .child(FirebaseTreeConstants.COUNTERS)
                .child(FirebaseTreeConstants.POST_COUNTERS)
                .setValue(0) ;
        currentUserNodeReference
                .child(FirebaseTreeConstants.SETTINGS)
                .child(FirebaseTreeConstants.COUNTERS)
                .child(FirebaseTreeConstants.WORK_COUNTERS)
                .setValue(0) ;
    }
}
