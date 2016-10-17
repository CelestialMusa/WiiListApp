package com.example.ngoveni.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    ImageButton regi;
    ImageButton signIn;
    TextView txtUser;
    TextView txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regi = (ImageButton)findViewById(R.id.btnRegNewUser);
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                finish();
            }
        });

        txtUser = (TextView) findViewById(R.id.txtLoginUsername);
        txtPass = (TextView) findViewById(R.id.txtLoginPass);

        signIn = (ImageButton)findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "";
                String pass = "";

                try{
                    username = txtUser.getText().toString();
                    pass = txtPass.getText().toString();
                }
                catch (Exception ex){

                }

                if(!(username.length()>0)){
                    Toast.makeText(Login.this,"Username Empty",Toast.LENGTH_LONG).show();
                }
                else{
                    if(!(pass.length()>0)){
                        Toast.makeText(Login.this,"Password Empty",Toast.LENGTH_LONG).show();
                    }
                    else{
                        DBHandler handler = new DBHandler(Login.this);

                        String [] s = handler.logIn(username, pass);

                        if(s[0].length()>0){
                            Intent i = new Intent(Login.this, Home.class);
                            i.putExtra("username",s);

                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(Login.this,"Incorrect username or password",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}
