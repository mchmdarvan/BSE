package personal.mine.bse.modul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import personal.mine.bse.Adapter.BookAdapterVertical;
import personal.mine.bse.DetailBukuActivity;
import personal.mine.bse.Model.Book;
import personal.mine.bse.R;
import personal.mine.bse.utils.Config;

public class BukuNonkurikulumActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    RecyclerView rvNonkurikulum;
    List<Book> bookList;
    BookAdapterVertical bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku_non_kurikulum);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        rvNonkurikulum = findViewById(R.id.rvNonkurikulum);

        bookList = new ArrayList<>();

        getContents();
    }

    public void getContents(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.getContents,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse: ", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("contents");
                            Log.d("onResponse: ", jsonArray.toString());
                            for (int i = 0; i< jsonArray.length(); i++){
                                JSONObject item = jsonArray.getJSONObject(i);
                                Book data = new Book();
                                data.setId(item.getString("Id"));
                                data.setTitle(item.getString("Title"));
                                data.setDescription(item.getString("Description"));
                                bookList.add(data);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        rvNonkurikulum.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        bookAdapter = new BookAdapterVertical(bookList);
                        rvNonkurikulum.setAdapter(bookAdapter);
                        bookAdapter.setOnItemClickCallback(new BookAdapterVertical.OnItemClickCallback() {
                            @Override
                            public void OnItemClick(Book data) {
                                Intent move = new Intent(BukuNonkurikulumActivity.this, DetailBukuActivity.class);
                                move.putExtra("Id", data.getId());
                                startActivity(move);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        })
        {
            //            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("levelid", "30b80853b22742dd9d6c61e58d7129e7");
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + getSharedPreferences("login", MODE_PRIVATE).getString("token", ""));
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }
}