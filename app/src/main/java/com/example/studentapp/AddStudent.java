package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddStudent extends AppCompatActivity {
   EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
   AppCompatButton b1,b2;
   String apiUrl="https://courseapplogix.onrender.com/addstudents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ed1=(EditText) findViewById(R.id.fname);
        ed2=(EditText) findViewById(R.id.lname);
        ed3=(EditText) findViewById(R.id.cname);
        ed4=(EditText) findViewById(R.id.birth);
        ed5=(EditText) findViewById(R.id.course);
        ed6=(EditText) findViewById(R.id.mob);
        ed7=(EditText) findViewById(R.id.email);
        ed8=(EditText) findViewById(R.id.address);
        b1=(AppCompatButton) findViewById(R.id.addbtn);
        b2=(AppCompatButton) findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=ed1.getText().toString();
                String last=ed2.getText().toString();
                String clg=ed3.getText().toString();
                String getbirth=ed4.getText().toString();
                String getcourse=ed5.getText().toString();
                String getmob=ed6.getText().toString();
                String getemail=ed7.getText().toString();
                String getaddr=ed8.getText().toString();
                //Json Object creation
                JSONObject addstu=new JSONObject();
                try {
                    addstu.put("firstname",first);
                    addstu.put("lastname",last);
                    addstu.put("college",clg);
                    addstu.put("dob",getbirth);
                    addstu.put("course",getcourse);
                    addstu.put("mobile",getmob);
                    addstu.put("address",getemail);
                    addstu.put("email",getaddr);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //Json object req
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        addstu,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}