package com.neighborly.swapnilpatil.neighborly;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    String first_name,last_name,user_gender,user_phone,user_age;
    EditText firstName,lastName,phoneNumber,gender,age;
    String user_latitude,user_longitude;
    Button save_details;
    AQuery aQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        gender = (EditText)findViewById(R.id.user_gender);
        age = (EditText)findViewById(R.id.user_age);
        phoneNumber = (EditText)findViewById(R.id.user_phone_number);
        save_details = (Button)findViewById(R.id.save_details);
        save_details.setOnClickListener(this);

        if(savedInstanceState != null){
           Bundle b = new Intent().getBundleExtra("user_name");
            String name = b.get("name").toString();
            if(name != null && !name.equals("")) {
                firstName.setText(name.split(" ")[0]);
                lastName.setText(name.split(" ")[1]);
            }
        }
        aQuery = new AQuery(this);

    }




    @Override
    public void onClick(View v) {
        if(firstName.getText()!=null && !firstName.equals("")){
            first_name = firstName.getText().toString();

        }
        else{
            firstName.setBackgroundColor(Color.RED);
        }
        if(lastName.getText()!=null && !lastName.equals("")){
                last_name = lastName.getText().toString();

        } else
            lastName.setBackgroundColor(Color.RED);

        if(age.getText()!=null && !age.equals("")){
            user_age = age.getText().toString();

        } else
                age.setBackgroundColor(Color.RED);

        if(gender.getText()!=null && !gender.equals("")){
            user_gender = gender.getText().toString();

        } else{
            gender.setBackgroundColor(Color.RED);
        }
        if(phoneNumber.getText()!=null && !phoneNumber.equals("")){
            user_phone = phoneNumber.getText().toString();

        }
        else
            phoneNumber.setBackgroundColor(Color.RED);
        JSONObject user_info = new JSONObject();
        JSONObject user_object= new JSONObject();
        try {


            user_object.put("first_name",first_name);
            user_object.put("last_name",last_name);
            user_object.put("age",user_age);
            JSONArray user_location = new JSONArray();
            JSONObject latitude = new JSONObject();
            latitude.put("latitude",user_latitude);
            JSONObject longitude = new JSONObject();
            longitude.put("longitude",user_longitude);
            user_location.put(0,latitude);
            user_location.put(1,longitude);
            user_object.put("location",user_location);
            user_object.put("gender",user_gender);
            user_object.put("phone_number",user_phone);
            user_info.put("user_info",user_object);
            Map<String,Object> dataToUpload = new HashMap<>();
            dataToUpload.put("user_info",user_object);
            String urlToUpload = "10.3.17.144/Users/Prasanna/Documents/workspace_1/A_Neighborly/src/Backend.java";
            aQuery.ajax(urlToUpload,dataToUpload,String.class,new AjaxCallback<String>(){

                @Override
                public void callback(String url, String object, AjaxStatus status) {
                    super.callback(url, object, status);
                    processResult(object);
                }


            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
   public void  processResult(String object){
        Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
        String name = first_name + " "+ last_name;
        intent.putExtra("username",name);
       startActivity(intent);
   }
}
