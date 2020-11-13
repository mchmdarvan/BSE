package personal.mine.bse.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import personal.mine.bse.Adapter.BookAdapter;
import personal.mine.bse.DetailBukuActivity;
import personal.mine.bse.Model.Book;
import personal.mine.bse.R;
import personal.mine.bse.modul.BukuBaruActivity;
import personal.mine.bse.modul.BukuNonkurikulumActivity;
import personal.mine.bse.modul.BukuPopulerActivity;
import personal.mine.bse.modul.PaudActivity;
import personal.mine.bse.modul.SdActivity;
import personal.mine.bse.modul.SmaActivity;
import personal.mine.bse.modul.SmkActivity;
import personal.mine.bse.modul.SmpActivity;
import personal.mine.bse.utils.Config;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {

    TextView tvPAUD, tvSD, tvSMP, tvSMK, tvSMA, tvTerbaru, tvTerpopuler, tvNonkurikulum;
    BookAdapter bookAdapter;
    List<Book> bookList;
    SharedPreferences sharedPreferences;
    RecyclerView rvBukuTerbaru, rvBukuTerpopuler, rvBukuNonKurikulum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        Log.d("Response: ", "OnResponse: " + this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).getString("token", ""));

        tvPAUD = view.findViewById(R.id.tvPAUD);
        tvSD = view.findViewById(R.id.tvSD);
        tvSMP = view.findViewById(R.id.tvSMP);
        tvSMA = view.findViewById(R.id.tvSMA);
        tvSMK = view.findViewById(R.id.tvSMK);
        tvNonkurikulum = view.findViewById(R.id.tvNonkurikulum);
        tvTerbaru = view.findViewById(R.id.tvTerbaru);
        tvTerpopuler = view.findViewById(R.id.tvTerpopuler);
        rvBukuTerbaru = view.findViewById(R.id.rvBukuTerbaru);
        rvBukuNonKurikulum = view.findViewById(R.id.rvBukuNonkurikulum);
        rvBukuTerpopuler = view.findViewById(R.id.rvBukuTerpopuler);

        bookList = new ArrayList<>();

        ImageSlider imageSlider=view.findViewById(R.id.slider);
        List<SlideModel> slideModelLs=new ArrayList<>();
        slideModelLs.add(new SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE));
        slideModelLs.add(new SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE));
        slideModelLs.add(new SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE));

        imageSlider.setImageList(slideModelLs, ScaleTypes.FIT);

        tvPAUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), PaudActivity.class);
                startActivity(paud);
            }
        });

        tvSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sd = new Intent(getActivity(), SdActivity.class);
                startActivity(sd);
            }
        });

        tvSMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), SmpActivity.class);
                startActivity(paud);
            }
        });

        tvSMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), SmkActivity.class);
                startActivity(paud);
            }
        });

        tvSMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), SmaActivity.class);
                startActivity(paud);
            }
        });

        tvTerpopuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), BukuPopulerActivity.class);
                startActivity(paud);
            }
        });

        tvTerbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), BukuBaruActivity.class);
                startActivity(paud);
            }
        });

        tvNonkurikulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paud = new Intent(getActivity(), BukuNonkurikulumActivity.class);
                startActivity(paud);
            }
        });

        getBukuTerbaru();
        getBukuNonKurikulum();
        getBukuTerpopuler();
    }

    public void getBukuTerbaru(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

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
                                bookList.add(data);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        rvBukuTerbaru.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        bookAdapter = new BookAdapter(bookList);
                        rvBukuTerbaru.setAdapter(bookAdapter);
                        bookAdapter.setOnItemClickCallback(new BookAdapter.OnItemClickCallback() {
                            @Override
                            public void OnItemClick(Book data) {
                                Intent move = new Intent(getContext(), DetailBukuActivity.class);
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
//                params.put("levelid", "3e462453fb024ae097ce7fd50d8cdb32");
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).getString("token", ""));
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getBukuTerpopuler(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

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
                                bookList.add(data);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        rvBukuTerpopuler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        bookAdapter = new BookAdapter(bookList);
                        rvBukuTerpopuler.setAdapter(bookAdapter);
                        bookAdapter.setOnItemClickCallback(new BookAdapter.OnItemClickCallback() {
                            @Override
                            public void OnItemClick(Book data) {
                                Intent move = new Intent(getContext(), DetailBukuActivity.class);
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
//                params.put("levelid", "3e462453fb024ae097ce7fd50d8cdb32");
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).getString("token", ""));
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getBukuNonKurikulum(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

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
                                bookList.add(data);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        rvBukuNonKurikulum.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        bookAdapter = new BookAdapter(bookList);
                        rvBukuNonKurikulum.setAdapter(bookAdapter);
                        bookAdapter.setOnItemClickCallback(new BookAdapter.OnItemClickCallback() {
                            @Override
                            public void OnItemClick(Book data) {
                                Intent move = new Intent(getContext(), DetailBukuActivity.class);
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
//                params.put("levelid", "3e462453fb024ae097ce7fd50d8cdb32");
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).getString("token", ""));
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }
}
