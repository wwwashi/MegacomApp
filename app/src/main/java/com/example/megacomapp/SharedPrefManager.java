package com.example.megacomapp;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARE_PREF_NAME = "mysharepref";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_USER_SURNAME = "usersurname";
    private static final String KEY_USER_PATRONYMIC = "userpatronymic";
    private static final String KEY_USER_ACCOUNT = "useraccount";
    private static final String KEY_ACCOUNT_ADDRESS = "accountaddress";
    private static final String KEY_ACCOUNT_BALANCE = "accountbalance";
    private static final String KEY_ACCOUNT_TARIFF = "accounttariff";

    private SharedPrefManager(Context context){
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String name, String surname, String patronymic, int account){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_SURNAME, surname);
        editor.putString(KEY_USER_PATRONYMIC, patronymic);
        editor.putInt(KEY_USER_ACCOUNT, account);

        editor.apply();

        return true;
    }

    public boolean userProfile(int account, String address, String balance, String tariff){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ACCOUNT, account);
        editor.putString(KEY_ACCOUNT_ADDRESS, address);
        editor.putString(KEY_ACCOUNT_BALANCE, balance);
        editor.putString(KEY_ACCOUNT_TARIFF, tariff);

        editor.apply();

        return true;
    }

    public boolean isLoginIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_ACCOUNT, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }
    public String getUseraccount() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        int userAccountInt = sharedPreferences.getInt(KEY_USER_ACCOUNT, 0);
        return String.valueOf(userAccountInt);
    }
    public String getAccountAddress(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCOUNT_ADDRESS, "");
    }
    public String getAccountBalance(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCOUNT_BALANCE, "");
    }
    public String getAccountTariff(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCOUNT_TARIFF, "");
    }

}
