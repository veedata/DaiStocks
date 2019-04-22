package com.random.stockmarket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    EditText Scrip_Id;
    Button Submit_Scrip_Id;
    String Scrip_Id_Str;
    String api_data;

    //    String api_key = "api_key=2UFnLzxo9thEs-7XiBFQ";
    String api_key = "MC418AZHRV8XP2TI";
    String url = "";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Scrip_Id = (EditText)findViewById(R.id.text_scripCode);
        Submit_Scrip_Id = (Button)findViewById(R.id.button_submitScripCode);

        Submit_Scrip_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Scrip_Id_Str = Scrip_Id.getText().toString();
                url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+Scrip_Id_Str+"&outputsize=compact&apikey"+api_key;

//                String reply_data = getApiData(url);
                OkHttpHandler okHttpHandler= new OkHttpHandler();
                okHttpHandler.execute(url);
//                Log.d("DATA",reply_data);
            }
        });
    }
//just thread it now...

//    String getApiData(String url) throws IOException {
//
//        int flag;
//
//        Request req = new Request.Builder()
//                .url(url)
//                .build();
//

//        try (Response response = client.newCall(req).execute()) {
//            if (response.body() != null) {
//                return response.body().string();
//            }
//            else{
//                return "boohoo";
//            }
//        }



//        client.newCall(req).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                call.cancel();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                final String myResponse = response.body().string();
//
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        txtString.setText(myResponse);
//                    }
//                });
//
//            }
//        });
//    }

    public class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String...params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            api_data = s;
        }
    }
}