package com.example.megacomapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextAccount, editTextPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //if(SharedPrefManager.getInstance(this).isLoginIn()){
            //finish();
           // startActivity(new Intent(this, ProfileActivity.class));
           // return;
        //}

        editTextAccount = (EditText) findViewById(R.id.login);
        editTextPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Подождите..");

        btnLogin.setOnClickListener(this);
    }

    private void userLogin(){
        final String account = editTextAccount.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("idusers"),
                                                obj.getString("name"),
                                                obj.getString("surname"),
                                                obj.getString("patronymic"),
                                                obj.getInt("account")
                                        );
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                //finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
          @Override
          protected Map<String, String> getParams() throws AuthFailureError{
              Map<String, String> params = new HashMap<>();
              params.put("account", account);
              params.put("password", password);
              return params;
          }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        StringRequest stringRequest1 = new StringRequest(
                Request.Method.POST,
                Constants.URL_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userProfile(
                                                obj.getInt("idaccount"),
                                                obj.getString("address"),
                                                obj.getString("balance"),
                                                obj.getString("tariff")
                                        );
                                //finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("account", account);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest1);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            userLogin();
        }
    }
}