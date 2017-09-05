package pict.s2k.frameit.screens;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.ArrayList;

import pict.s2k.frameit.FirebaseTreeConstants;
import pict.s2k.frameit.R;
import pict.s2k.frameit.auth.AuthenticationBaseCode;

public class SignUpActivity extends AppCompatActivity {

    private int PICK_PHOTO_FOR_AVATAR = 200 ;
    private Uri selectedImageUri ;
    private ArrayList<EditText> editTextArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((Button) findViewById(R.id.user_registration_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegistration();
            }
        });

        ((ImageView) findViewById(R.id.profile_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void userRegistration() {

        editTextArrayList = new ArrayList<>() ;
        editTextArrayList.add(((EditText) findViewById(R.id.email))) ;
        editTextArrayList.add(((EditText) findViewById(R.id.password))) ;
        editTextArrayList.add(((EditText) findViewById(R.id.rePassword))) ;
        editTextArrayList.add(((EditText) findViewById(R.id.displayName))) ;
        editTextArrayList.add(((EditText) findViewById(R.id.mobileNo))) ;

        boolean ok = true ;
        for(EditText eT : editTextArrayList){
            if(TextUtils.isEmpty(eT.getText().toString()))
                ok=false;
        }
        if(editTextArrayList.get(1).getText().toString().equals(editTextArrayList.get(1).getText().toString()) && ok ){
            AuthenticationBaseCode.createAccountWithEmailAndPassword(editTextArrayList.get(0).getText().toString(),editTextArrayList.get(1).getText().toString(),SignUpActivity.this,editTextArrayList.get(3).getText().toString(),editTextArrayList.get(4).getText().toString(),selectedImageUri) ;
        }
        else{
            Toast.makeText(this, "Please Provide All Details", Toast.LENGTH_SHORT).show();
        }

    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    /*private void uploadProfilePicture(){
        StorageReference profilePictureNodeRef = FirebaseStorage.getInstance().getReference()
                .child(FirebaseTreeConstants.PROFILE_PICTURES) ;
        StorageReference saveSelectedImageRef = profilePictureNodeRef
                .child(System.currentTimeMillis() + " " + selectedImageUri.getLastPathSegment()) ;
        saveSelectedImageRef.putFile(selectedImageUri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()) {

                        }
                    }
                }) ;
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            selectedImageUri = data.getData() ;
            ImageView iV = ((ImageView) findViewById(R.id.profile_image));
            iV.setImageURI(null);
            iV.setImageURI(selectedImageUri);
        }
    }

}
