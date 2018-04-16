package com.example.mailf.chatbotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class first_screen_button extends AppCompatActivity {
    Button about_app;
    Button use_app;
    Button about_developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

       about_app = (Button)findViewById(R.id.about_app);
        use_app = (Button)findViewById(R.id.use_app);
        about_developer = (Button)findViewById(R.id.about_developer);


        about_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(first_screen_button.this,about_app.class);
                startActivity(i1);
            }
        });

        use_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(first_screen_button.this,log_in.class);
                startActivity(i2);
            }
        });


        about_developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(first_screen_button.this,developer_profile.class);
                startActivity(i3);
            }
        });
    }
}
