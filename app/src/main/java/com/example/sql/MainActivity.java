package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText field1, field2, result;
    MyOpenHelper myHelper = null;
    SQLiteDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper=new MyOpenHelper(this, "myDB", null, 1);
        field1 = findViewById(R.id.editTextTextPersonName);
        field2 = findViewById(R.id.editTextTextPersonName2);
        result = findViewById(R.id.editTextTextPersonName3);

    }
    public void insertIntoDatabase(View view){
        DB = myHelper.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(myHelper.FIELD_NAME_1,field1.getText().toString());
        CV.put(myHelper.FIELD_NAME_2,field2.getText().toString());
        DB.insert(myHelper.TABLE_NAME,null,CV);
        DB.close();

    }
    public void readDatabase(View view){
        DB = myHelper.getReadableDatabase();
        String columns[]={"_id",myHelper.FIELD_NAME_1,
                myHelper.FIELD_NAME_2};
        Cursor cursor=DB.query(myHelper.TABLE_NAME, columns, null,
                null, null, null, "_id");
        if(cursor!=null){
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                do {
                    result.setText(result.getText().toString()+"\n" +cursor.getString(0)+") " + cursor.getString(1) + ", "+cursor.getString(2));
                } while (cursor.moveToNext());
            }
        }
        DB.close();


    }
    public void deleteDatabase(View view){
        DB = myHelper.getWritableDatabase();
        DB.delete(myHelper.TABLE_NAME, null, null);
        DB.close();

    }

}