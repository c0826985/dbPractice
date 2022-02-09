package com.example.dbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.dbpractice.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;

    // sqLite DB
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAddEmployee.setOnClickListener(this);
        binding.tvDisplayEmployees.setOnClickListener(this);

        //initialize the sqLite db
        sqLiteDatabase = openOrCreateDatabase("employeedb",MODE_PRIVATE,null);
        //create tables
        createTables();
    }

    private void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "id INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(20) NOT NULL, " +
                "department VARCHAR(20) NOT NULL," +
                "joining_date DATETIME NOT NULL, " +
                "salary DOUBLE NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_employee:
                addEmployee();
                break;
            case R.id.tvDisplayEmployees:
                startActivity(new Intent(this,EmployeeActivity.class));
                break;
        }
    }

    private void addEmployee() {
        String name = binding.etName.getText().toString().trim();
        String salary = binding.etSalary.getText().toString().trim();
        String department = binding.spinnerDept.getSelectedItem().toString();
        //joining date
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);
        String joiningDate = sdf.format(calender.getTime());

        if(name.isEmpty())
        {
            binding.etName.setError("name field is empty");
            binding.etName.requestFocus();
            return;
        }

        if(salary.isEmpty())
        {
            binding.etSalary.setError("salary field is empty");
            binding.etSalary.requestFocus();
            return;
        }

        // first approach
        String sql = "INSERT INTO employee (name,department,joining_date, salary)" +
                "VALUES (?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{name,department,joiningDate,salary});
    }
}