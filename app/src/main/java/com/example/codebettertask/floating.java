package com.example.codebettertask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class floating extends AppCompatActivity {
EditText fname,fage,fgender;
Button fbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        fname = findViewById(R.id.fname);
        fage = findViewById(R.id.fage);
        fgender = findViewById(R.id.fgender);
        fbutton = findViewById(R.id.fbutton);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().isEmpty() || fage.getText().toString().isEmpty() || fgender.getText().toString().isEmpty())
                {
                    Toast.makeText(floating.this, "fill all details", Toast.LENGTH_SHORT).show();
                }else
                {
                    postpatientdata(fname.getText().toString(),fage.getText().toString(),fgender.getText().toString());
                }
            }
        });
    }

    public void postpatientdata(String ffname, String ffage, String ffgender) {
//        Log.("my",ffage +"  "+ ffgender);
        Log.e("sky",ffage +"  "+ ffgender);
       String url = "https://todearhemant.pythonanywhere.com/patient/api/patients/";
        RequestQueue queue2 = Volley.newRequestQueue(floating.this);


        StringRequest request2 = new StringRequest(Request.Method.POST,url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty
               // loadingPB.setVisibility(View.GONE);
                fname.setText("");
                fage.setText("");
                fgender.setText("");

                // on below line we are displaying a success toast message.
                Toast.makeText(getApplicationContext(), "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    String name = respObj.getString("name");
                    String job = respObj.getString("job");

                    // on below line we are setting this string s to our text view.
                   // responseTV.setText("Name : " + name + "\n" + "Job : " + job);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(getApplicationContext(), "Fail to get response = " +error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("name", fage.getText().toString());
                params.put("age", fname.getText().toString().toString());
                params.put("gender",fgender.getText().toString());

                  // at last we are
                // returning our params.

                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue2.add(request2);
    }





    }


