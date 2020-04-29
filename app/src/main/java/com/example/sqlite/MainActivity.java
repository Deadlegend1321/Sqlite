package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    databasehelper mydb;
    EditText et1,et2;
    Button b,v;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new databasehelper(this);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        b= (Button)findViewById(R.id.b);
        v =(Button)findViewById(R.id.v);
        adddata();
        viewall();
    }

    public void adddata()
    {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean x =  mydb.insertdata(et1.getText().toString(),et2.getText().toString());
              if (x==true){
                  Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                  count++;
              }
              else
                  Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewall(){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getAllData();
                if (res.getCount()==0){
                    showmsg("Error","Nothing found");
                    return;
                }
                StringBuffer bf = new StringBuffer();
                while (res.moveToNext())
                {
                    bf.append("S.No :"+res.getString(0)+"\n");
                    bf.append("Name :"+res.getString(1)+"\n");
                    bf.append("GENRE :"+res.getString(2)+"\n");

                }
                showmsg("Data",bf.toString());
            }
        });
    }
    public void showmsg(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
