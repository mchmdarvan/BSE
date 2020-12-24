package personal.mine.bse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import personal.mine.bse.utils.Config;

public class DetailBukuActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView tvTitle, tvDescription, tvWriter;
    ImageView ivCover, ivRead, ivDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvWriter = findViewById(R.id.tvWriter);
        ivCover = findViewById(R.id.ivCover);
        ivRead = findViewById(R.id.ivRead);
        ivDownload = findViewById(R.id.ivDownload);

//        Glide.with(this)
//                .load(R.drawable.banner)
//                .into(ivCover);

        Log.e("Test", getIntent().getExtras().getString("Id"));

        getContents();

    }

    private void getContents(){
        String getLevel = "http://apps.belajar.kemdikbud.go.id:3000/bse/detail";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getLevel,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", "onResponse 1: "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.e("TAG", "onResponse 2: " + jsonObject.getJSONArray("data"));
                            JSONArray data = jsonObject.getJSONArray("data");
                            for(int i = 0; i<data.length();i++){
                                JSONObject item = data.getJSONObject(i);
                                tvTitle.setText(item.getString("Title"));
                                tvDescription.setText(item.getString("Description"));
                                tvWriter.setText(item.getString("Writer"));
                                final String coverUrl = item.getString("Cover");
                                final String url = item.getString("Attachment");
                                Glide.with(getApplicationContext())
                                        .load(coverUrl)
                                        .into(ivCover);
                                ivRead.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent read = new Intent(Intent.ACTION_VIEW);
                                        read.setData(Uri.parse(url));
                                        startActivity(read);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("id", getIntent().getExtras().getString("Id"));
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + getSharedPreferences("login", MODE_PRIVATE).getString("token", ""));
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

//    public void getContents(){
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.getDetail,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("TAG", "onResponse 1: "+response);
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//
//                            Log.e("TAG", "onResponse 2: " + jsonObject.getJSONArray("data"));
//                            JSONArray data = jsonObject.getJSONArray("data");
//                            for(int i = 0; i<data.length();i++){
//                                JSONObject item = data.getJSONObject(i);
//                                tvTitle.setText(item.getString("Title"));
//                                tvDescription.setText(item.getString("Description"));
//                                tvWriter.setText(item.getString("Writer"));
//                                final String url = item.getString("Attachment");
//                                Log.e("Tag", "On Response: " + url);
//                                ivRead.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Log.e("Tag", "On Response: " + url);
//                                    }
//                                });
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.getMessage();
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("id", getIntent().getExtras().getString("Id"));
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer " + getSharedPreferences("login", MODE_PRIVATE).getString("token", ""));
//                return headers;
//            }
//        };
//
//        requestQueue.add(stringRequest);
//    }
}