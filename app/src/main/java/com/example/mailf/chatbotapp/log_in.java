package com.example.mailf.chatbotapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class log_in extends AppCompatActivity {
Button button_log_in;
Button button_sign_up;
EditText mail_adress;
EditText password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);
        button_log_in = (Button) findViewById(R.id.button_log_in) ;
        button_sign_up = (Button) findViewById(R.id.button_sign_up) ;
        mail_adress = (EditText) findViewById(R.id.mail_adress);
        password = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();



        button_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loging_validation(mail_adress.getText().toString(),password.getText().toString());

            }
        });

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(log_in.this,sign_up.class);
                startActivity(i2);
            }
        });

    }
    public void loging_validation(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                             //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent i = new Intent(log_in.this,User.class);
                            startActivity(i);

                        } else {


                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                             Toast.makeText(log_in.this, "Authentication failed.",
                              Toast.LENGTH_SHORT).show();
                            // updateUI(null);

                        }

                        // ...
                    }
                });


    }
    @Override
    public void onStart(){
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    }

