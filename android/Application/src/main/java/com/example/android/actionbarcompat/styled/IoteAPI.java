package com.example.android.actionbarcompat.styled;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by maxp on 15-11-13.
 */
public class IoteAPI {

    // set to false when debugging
    private boolean production = false;

    // web api url
    private static String urlPrefix = "http://getiote.com/api";

    // todo: login
    // change some settings cloud data?

    // upload blip from a discovered device
    public void uploadBlip(String seenBy, String tagId, Date timestamp) {
        // uploads a blip to the cloud history
    }


    // get list of devices associated with this account

    public IoteTag[] getDevices(String userId) {
        return new IoteTag[0]; // todo impletment
    }


    public static boolean registerNewIoteTag(String userId, String tagId) {

        String url = urlPrefix + "/register";

        // pairs the user with a new tag
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        mTxtDisplay.setText("Response: " + response.toString());
                        Log.d("networking",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("networking",error.toString());
                    }
                });

        Log.d("hello",jsObjRequest.toString());

        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);

        // todo: remove one api integrated
        if (true) {
            return true;
        }



        // errors if tag already paired
        return false;
    }







}
