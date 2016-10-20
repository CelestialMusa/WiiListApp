package com.example.ngoveni.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    TextView cancelItemAdd;
    TextView txtTask;
    TextView txtDescript;
    TextView txtDate;
    TextView txtTime;

    ImageButton btnshowdate;
    ImageButton btnshowtime;
    ImageButton btnInsertTask;

    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;

    static final int DIALOG_ID_T = 1;
    int hour_x,minute_x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        showDialogOnButtonClick();

        showTimePicker();

        txtTask = (TextView) findViewById((R.id.txtTaskTitle));
        txtDescript = (TextView) findViewById((R.id.txtDescript));
        txtDate = (TextView) findViewById((R.id.txtDate));
        txtTime = (TextView) findViewById((R.id.txtTime));

        btnInsertTask = (ImageButton) findViewById(R.id.btnInsertTask);
        btnInsertTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String title = "";
                String descript = "";
                String date = "";
                String time = "";
                String complete ="";
                String username = "";
                String finalDate = "";

                try{
                    title = txtTask.getText().toString();
                    descript = txtDescript.getText().toString();
                    date = txtDate.getText().toString();
                    time = txtTime.getText().toString();
                    finalDate = date+" "+time;
                    complete = "Incomplete";
                }
                catch(Exception ex){

                }



                try{
                    Task task = new Task(title, descript, finalDate, complete, username);

                    DBActivity handler = new DBActivity(AddActivity.this);

                    boolean status = handler.insertTask(task);

                    if(status){
                        Toast.makeText(AddActivity.this,"Task Added",Toast.LENGTH_LONG).show();

                        Intent i = new Intent(AddActivity.this, Home.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(AddActivity.this,"Database Error",Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception ex){
                    Toast.makeText(AddActivity.this,"Database Read Error",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelItemAdd = (TextView)findViewById(R.id.btnCancelAddItem);
        cancelItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }



    public void showTimePicker(){
        btnshowtime = (ImageButton)findViewById(R.id.btnTime);
        btnshowtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID_T);
            }
        });
    }

    public  void  showDialogOnButtonClick()
    {
        btnshowdate = (ImageButton)findViewById(R.id.btnDate);
        btnshowdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    private  DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month;
            day_x = dayOfMonth;
            txtDate.setText(year_x + "/" + month_x + "/" + day_x);
            //Toast.makeText(AddActivity.this,year_x + "/" + month_x + "/" + day_x,Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        else if(id == DIALOG_ID_T) {
            return  new TimePickerDialog(AddActivity.this, KTimePickerListener, hour_x,minute_x,false);
        }
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener KTimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour_x = hourOfDay;
                    minute_x = minute;
                    txtTime.setText(hour_x + " : " + minute_x);
                    //Toast.makeText(AddActivity.this,hour_x + " : " + minute_x,Toast.LENGTH_LONG).show();
                }
            };



}
