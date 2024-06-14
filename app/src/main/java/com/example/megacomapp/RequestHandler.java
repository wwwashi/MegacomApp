package com.example.megacomapp;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandler {

    private static RequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private RequestHandler(Context context){
        mCtx = context;
        mRequestQueue = getmRequestQueue();
    }

    public static synchronized RequestHandler getInstance(Context context){
        if (mInstance == null){
            mInstance = new RequestHandler(context);
        }
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getmRequestQueue().add(req);
    }

}
