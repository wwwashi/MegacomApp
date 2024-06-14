package com.example.megacomapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity{

    private TextView TextViewUsername, TextViewAccount, TextViewBalance, TextViewAddress, TextViewTariff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        //if(!SharedPrefManager.getInstance(this).isLoginIn()){
            //finish();
           // startActivity(new Intent(this, MainActivity.class));
        //}
        TextViewUsername = (TextView) findViewById(R.id.name);
        TextViewAccount = (TextView) findViewById(R.id.account);
        TextViewBalance = (TextView) findViewById(R.id.balance);
        TextViewAddress = (TextView) findViewById(R.id.address);
        TextViewTariff = (TextView) findViewById(R.id.tariff);

        TextViewUsername.setText(SharedPrefManager.getInstance(this).getUsername()+", рады вас видеть!");
        TextViewAccount.setText(SharedPrefManager.getInstance(this).getUseraccount());
        TextViewAddress.setText(SharedPrefManager.getInstance(this).getAccountAddress());
        TextViewBalance.setText(SharedPrefManager.getInstance(this).getAccountBalance());
        TextViewTariff.setText(SharedPrefManager.getInstance(this).getAccountTariff());
    }
}
