package com.example.ngoveni.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zacharia on 16-Oct-16.
 */

public class Register extends AppCompatActivity {

    TextView cancel;
    ImageButton signup;
    TextView txtUsername;
    TextView txtLast;
    TextView txtFirst;
    TextView txtPass;
    TextView txtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername = (TextView) findViewById((R.id.txtUserName));
        txtLast = (TextView) findViewById((R.id.txtLastName));
        txtFirst = (TextView) findViewById((R.id.txtFirstName));
        txtPass = (TextView) findViewById((R.id.txtPassword));
        txtConfirm = (TextView) findViewById((R.id.txtComfirmPass));

        signup = (ImageButton) findViewById((R.id.btnSignUp));
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = "";
                String first = "";
                String last = "";
                String pass = "";
                String confirm = "";

                try{
                     username = txtUsername.getText().toString();
                     first = txtFirst.getText().toString();
                     last = txtLast.getText().toString();
                     pass = txtPass.getText().toString();
                     confirm = txtConfirm.getText().toString();
                }
                catch(Exception ex){

                }

                if(!(first.length()>0)){
                    Toast.makeText(Register.this,"First name cannot be empty",Toast.LENGTH_LONG).show();
                }
                else{
                    if(!(last.length()>0)){
                        Toast.makeText(Register.this,"Last name cannot be empty",Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(!(username.length()>0)){
                            Toast.makeText(Register.this,"Username name cannot be empty",Toast.LENGTH_LONG).show();
                        }
                        else{
                            if(!(pass.length()>0)){
                                Toast.makeText(Register.this,"Password cannot be empty",Toast.LENGTH_LONG).show();
                            }else{
                               if(!(confirm.length()>0)){
                                   Toast.makeText(Register.this,"Confirm your password",Toast.LENGTH_LONG).show();
                               }
                                else{
                                   if(pass.equals(confirm)){
                                       User user = new User(pass,username,first,last);

                                       DBHandler handler = new DBHandler(Register.this);

                                       boolean b = handler.insertUser(user);

                                       if(b){
                                           Toast.makeText(Register.this,"You can now login",Toast.LENGTH_LONG).show();
                                           Intent i = new Intent(Register.this, Login.class);
                                           startActivity(i);
                                           finish();
                                       }
                                       else
                                           Toast.makeText(Register.this,"Database Error",Toast.LENGTH_LONG).show();
                                   }
                                   else{
                                       Toast.makeText(Register.this,"Passwords do no match",Toast.LENGTH_LONG).show();
                                   }
                               }
                            }
                        }
                    }
                }
            }
        });

        cancel = (TextView)findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);

                startActivity(i);
                finish();
            }
        });
    }
}
