package com.random.stockmarket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText Scrip_Id;
    Button Submit_Scrip_Id;
    String Scrip_Id_Str;
    String api_data;

    //    String api_key = "api_key=2UFnLzxo9thEs-7XiBFQ";
    String api_key = "MC418AZHRV8XP2TI";
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Scrip_Id = (EditText) findViewById(R.id.text_scripCode);
        Submit_Scrip_Id = (Button) findViewById(R.id.button_submitScripCode);

        Submit_Scrip_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Scrip_Id_Str = Scrip_Id.getText().toString();
                url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+Scrip_Id_Str+"&outputsize=compact&apikey="+api_key;

                OkHttpHandler url_fetch = new OkHttpHandler();
                url_fetch.execute(url);
            }
        });
    }

    public class OkHttpHandler extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();
        @Override
        protected String doInBackground(String... params){
            Request req = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(req).execute();
                //Log.d("Output", "onPostExecute: "+response.body().string());
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            String final_data_json = s;
            Log.d("Output", final_data_json);
        }
    }
}