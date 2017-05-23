package com.example.sam_nagesh.easy_track;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.sam_nagesh.easy_track.MainActivity.FORM_DATA_TYPE;

/**
 * Created by sam_nagesh on 5/19/17.
 */

public class PostDataTask extends AsyncTask<String, Void, Boolean> {

    public static final String STUDENT_ID = "entry.1812464555";
    private String id;
    private Context context;

    public PostDataTask(String id, Context context) {
        this.id = id;
        this.context = context;
    }
    @Override
    protected Boolean doInBackground(String... contactData) {
        Boolean result = true;
        String postBody="";
        String url = contactData[0];

        try {
            //all values must be URL encoded to make sure that special characters like & | ",etc.
            //do not cause problems
            postBody = STUDENT_ID+"=" + URLEncoder.encode(id,"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            result=false;
        }



        try {
            //Create OkHttpClient for sending request
            OkHttpClient client = new OkHttpClient();
            //Create the request body with the help of Media Type
            RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            //Send the request
            Response response = client.newCall(request).execute();
        }catch (IOException exception){
            result=false;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result){
        //Print Success or failure message accordingly
        Toast.makeText(context,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
    }

}

