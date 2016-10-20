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
 * Created by Zacharia Manyoni on 2016/10/17.
 */
public class DBActivity extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "toDo.db";

    //Task table
    public static final String TASK_TABLE_ID = "taskID";
    public static final String TASK_TABLE_NAME = "task_title";
    public static final String TASK_TABLE_USERNAME = "username";
    public static final String TASK_TABLE_DESCRIPT = "taskDescription";
    public static final String TASK_TABLE_DUE = "taskDueDate";
    public static final String TASK_TABLE_COMPLETED = "taskCompleted";

    public DBActivity(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE tasks" +
                        "(" +
                        "    taskID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "    task_title TEXT"+
                        "    username TEXT," +
                        "    taskDescription TEXT," +
                        "    taskDueDate TEXT," +
                        "    taskCompleted INTEGER,"  +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV,int newV){
        db.execSQL("drop table if exit tasks");
        onCreate(db);
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
        res.close();
        return  tasks;
    }
}
