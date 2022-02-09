package com.example.dbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.dbpractice.databinding.ActivityEmployeeBinding;
import com.example.dbpractice.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private ActivityEmployeeBinding binding;
    private SQLiteDatabase sqLiteDatabase;

    private List<EmployeeModel> employeeModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sqLiteDatabase = openOrCreateDatabase("employeedb",MODE_PRIVATE,null);
        employeeModelList = new ArrayList<>();

        //load data from DB
        loadEmployees();
    }
    private void loadEmployees() {
        //Writing a query
        String sql = "SELECT * FROM employee";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                // create an instance on employee model
                employeeModelList.add(new EmployeeModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                            )
                );
            }while (cursor.moveToNext());
            cursor.close();
        }


    }
}