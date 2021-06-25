package com.example.codebettertask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
FloatingActionButton postbutton;
RecyclerView recyclerView;
List<patientdata> patientdatas;
private static String JSON_URL = "https://todearhemant.pythonanywhere.com/patient/api/patients";
Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyler);
        patientdatas = new ArrayList<>();
        extractpatientdata();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(getApplicationContext(),patientdatas);
        recyclerView.setAdapter(adapter);
        postbutton = findViewById(R.id.postbutton);
       postbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,floating.class));
           }
       });
    }

    private void extractpatientdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject patientobj = response.getJSONObject(i);
                        patientdata patientdata = new patientdata();
                        patientdata.setId(patientobj.getInt("id"));
                        patientdata.setName(patientobj.getString("name").toString());
                        patientdata.setAge(patientobj.getInt("age"));
                        patientdata.setGender(patientobj.getString("gender").toString());
                        patientdatas.add(patientdata);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new Adapter(getApplicationContext(),patientdatas);
                    recyclerView.setAdapter(adapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}