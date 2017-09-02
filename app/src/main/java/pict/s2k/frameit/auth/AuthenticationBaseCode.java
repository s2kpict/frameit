package pict.s2k.frameit.auth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
}
