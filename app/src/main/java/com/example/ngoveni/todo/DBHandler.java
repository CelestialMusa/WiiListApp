package com.example.ngoveni.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Mofokeng on 16-Oct-16.
 */
public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "toDo.db";
    //User table
    public static final String USER_TABLE = "user";
    public static final String USER_TABLE_USERNAME = "username";
    public static final String USER_TABLE_FIRST = "first";
    public static final String USER_TABLE_LAST = "last";
    public static final String USER_PASSWORD = "password";

    //Task table
    public static final String TASK_TABLE_ID = "taskID";
    public static final String TASK_TABLE_NAME = "task_title";
    public static final String TASK_TABLE_USERNAME = "username";
    public static final String TASK_TABLE_DESCRIPT = "taskDescription";
    public static final String TASK_TABLE_DUE = "taskDueDate";
    public static final String TASK_TABLE_COMPLETED = "taskCompleted";


    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE user" +
                        "(" +
                        "    username TEXT PRIMARY KEY NOT NULL," +
                        "    password TEXT," +
                        "    first TEXT," +
                        "    last TEXT" +
                        ");" +
                        "CREATE TABLE tasks" +
                        "(" +
                        "    taskID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    task_title TEXT"+
                        "    username TEXT," +
                        "    taskDescription TEXT," +
                        "    taskDueDate TEXT," +
                        "    taskCompleted INTEGER," +
                        "    FOREIGN KEY (username) REFERENCES user (username) ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED" +
                        ");"
        );

        /*db.execSQL(
                "CREATE TABLE tasks" +
                        "(" +
                        "    taskID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    task_title TEXT"+
                        "    username TEXT," +
                        "    taskDescription TEXT," +
                        "    taskDueDate TEXT," +
                        "    taskCompleted INTEGER," +
                        "    FOREIGN KEY (username) REFERENCES user (username) ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED" +
                        ");"
        );*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exit tasks");
        onCreate(db);
    }

    public boolean insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUserName());
        contentValues.put("password", user.getPassword());
        contentValues.put("first", user.getfName());
        contentValues.put("last", user.getlName());
        db.insert("user", null, contentValues);
        return true;
    }

    public boolean insertTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task_title", task.getTask());
        contentValues.put("username", task.getUsername());
        contentValues.put("taskDescription", task.getTaskDescr());
        contentValues.put("taskDueDate", task.getTaskDuedate());
        contentValues.put("taskCompleted", task.isTaskCompleted());
        db.insert("tasks", null, contentValues);
        return true;
    }

    public String [] logIn(String username,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor reso = db.rawQuery("select * from user where username = '"+username+"' and password = '"+pass+"' limit 1;",null);
        reso.moveToFirst();

        String userDetails [] = new String[3];

        String fname = "";
        String lname = "";

        while(reso.isAfterLast() == false){
            fname = reso.getString(reso.getColumnIndex(USER_TABLE_FIRST));
            lname = reso.getString(reso.getColumnIndex(USER_TABLE_LAST));
            username = reso.getString(reso.getColumnIndex(USER_TABLE_USERNAME));
            userDetails[0]= fname;
            userDetails[1] = lname;
            userDetails[2] = username;
            reso.moveToNext();
        }
        return  userDetails;
    }

    public ArrayList<Task> getTasks(String username){
        ArrayList<Task> tasks = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tasks where username = '"+username+"';",null);
        res.moveToFirst();

        String task_id = "";
        String title = "";
        String descript = "";
        String date = "";
        String complete = "";

        Task newTask;

        while(res.isAfterLast() == false){
            task_id = res.getString(res.getColumnIndex(TASK_TABLE_ID));
            title = res.getString(res.getColumnIndex(TASK_TABLE_NAME));
            descript = res.getString(res.getColumnIndex(TASK_TABLE_DESCRIPT));
            username  = res.getString(res.getColumnIndex(TASK_TABLE_USERNAME));
            complete = res.getString(res.getColumnIndex(TASK_TABLE_COMPLETED));
            date = res.getString(res.getColumnIndex(TASK_TABLE_DUE));

            newTask = new Task(task_id, title, descript, date, complete, username);
            tasks.add(newTask);
            res.moveToNext();
        }
        return  tasks;
    }
}
