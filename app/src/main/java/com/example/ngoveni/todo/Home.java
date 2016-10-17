package com.example.ngoveni.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    ImageButton signOut;
    ImageButton addItem;
    TextView lblUsernames;
    TextView lblUsername;
    TextView lblDone;
    TextView lblToDO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent  i = getIntent();

        String [] arrUserDetails = i.getStringArrayExtra("username");

        lblUsernames = (TextView) findViewById((R.id.lblUserNames));
        lblUsername = (TextView) findViewById((R.id.lblUsername));
        lblDone = (TextView) findViewById((R.id.lblDone));
        lblToDO = (TextView) findViewById((R.id.lblToDo));

        lblUsernames.setText(arrUserDetails[0]+" "+arrUserDetails[1]);

        signOut = (ImageButton)findViewById(R.id.btnSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        addItem = (ImageButton)findViewById(R.id.btnAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, AddActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
