package com.example.dbpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dbpractice.model.EmployeeModel;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter {
    Context context;
    int layoutRes;
    List<EmployeeModel> employeeModelList;
    SQLiteDatabase sqLiteDatabase;

    //first approach

    public EmployeeAdapter(@NonNull Context context,
                           int resource,
                           @NonNull List<EmployeeModel> employeeModelList,
                           SQLiteDatabase sqLiteDatabase) {
        super(context, resource,employeeModelList);
        this.context = context;
        this.layoutRes = resource;
        this.employeeModelList = employeeModelList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = convertView;
        if(view == null) view = layoutInflater.inflate(layoutRes,null);
        


        return super.getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return employeeModelList.size();
    }


}












