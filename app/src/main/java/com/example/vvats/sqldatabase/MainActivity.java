package com.example.vvats.sqldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    CustomSqlLiteDatabase customSqlLiteDatabase;
    EditText name, college;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customSqlLiteDatabase = new CustomSqlLiteDatabase(this);
        name = (EditText) findViewById(R.id.editText);
        college = (EditText) findViewById(R.id.editText2);
    }

    public void doSave(View view) {
        customSqlLiteDatabase.saveData(name.getText().toString(), college.getText().toString());
    }

    public void doLoad(View view) {
        customSqlLiteDatabase.loadData();
    }
}
