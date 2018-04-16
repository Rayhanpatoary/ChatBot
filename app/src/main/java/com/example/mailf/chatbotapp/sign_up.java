package com.example.mailf.chatbotapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_up extends AppCompatActivity {

    EditText user_name;
    EditText new_user_mail_adress;
    EditText set_password;
    EditText reentered_password;
    Button create_account;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user_name = (EditText) findViewById(R.id.user_name);
        new_user_mail_adress = (EditText) findViewById(R.id.mail_adress);
        set_password = (EditText) findViewById(R.id.password);
        reentered_password = (EditText) findViewById(R.id.reentered_password);
        create_account = (Button) findViewById(R.id.create_account);
        mAuth = FirebaseAuth.getInstance();

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation(user_name.getText().toString(),
                        new_user_mail_adress.getText().toString(),
                        set_password.getText().toString(),
                        reentered_password.getText().toString());
            }
        });


    }

    public void validation(String username, String email,
                           String password, String reentered_password) {


        if((isValidEmailAddress(email)==true)&&isnamevalid(username)==true&&
                ispasswordvalid(password)==true && match_password(password,reentered_password)){
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Successfully Created Your account!");
            dlgAlert.setTitle("Welcome. Dear New User !");
            dlgAlert.setPositiveButton(" Go For Log In", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                               // Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                               // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                               // Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                 //       Toast.LENGTH_SHORT).show();
                               // updateUI(null);
                            }

                            // ...
                        }
                    });
            after_sign_up();

        }
        else{

             if(isnamevalid(username)==false){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Invalid username! U should Use more than 2 and less than 15 character. ");
                dlgAlert.setTitle("Sorry");
                dlgAlert.setPositiveButton("Try again", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
           else if(isValidEmailAddress(email)==false){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Invalid mail adreess Used");
                dlgAlert.setTitle("Sorry!");
                dlgAlert.setPositiveButton("Try again", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            else if (isnamevalid(password)==false){
                 AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                 dlgAlert.setMessage("password Invail! U should use more than 5  & less than 20 characters");
                 dlgAlert.setTitle("Sorry!");
                 dlgAlert.setPositiveButton("Try again", null);
                 dlgAlert.setCancelable(true);
                 dlgAlert.create().show();
            }
            else if(match_password(password,reentered_password)==false){
                 AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                 dlgAlert.setMessage("Ur both passwords didn't matched");
                 dlgAlert.setTitle("Sorry!");
                 dlgAlert.setPositiveButton("Try again", null);
                 dlgAlert.setCancelable(true);
                 dlgAlert.create().show();
            }

        }

    }
        public boolean isnamevalid(String name){

            boolean result = false;

            if(name.length()>=3 && name.length()<=15){
                result = true;
            }
            return result;

        }

        public boolean isValidEmailAddress(String email) {

            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            return m.matches();
        }

        public boolean ispasswordvalid (String password){
            boolean result=false;
            if(password.length()>=6 && password.length()<20 ){
                result = true;
            }

            return result;

        }
        public boolean match_password(String password,String reentered_password){
                    boolean result=false;
                    if(password.equals(reentered_password)==true){
                        result = true;
                    }
                    return result;
        }
        public void after_sign_up(){
            Intent i = new Intent(sign_up.this,User.class);
            startActivity(i);
        }




}
