package com.andi.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ToDo> toDos = new ArrayList<>();
        toDos.add(new ToDo("1", "Pray", "18:25"));
        RecyclerView rView =
                (RecyclerView) findViewById(R.id.rv);
        Context ctx = this;

        Handler h = new Handler(Looper.getMainLooper());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue =
                        Volley.newRequestQueue(ctx);

                String url = "https://mgm.ub.ac.id/todo.php";
                StringRequest stringRequest =
                        new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Gson gson = new Gson();
                                        List<ToDo> obj = gson.fromJson(response, new TypeToken<List<ToDo>>() {
                                        }.getType());
                                        h.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                ToDoAdapter mAdapter = new ToDoAdapter(ctx, (ArrayList<ToDo>) obj);
                                                rView.setAdapter(mAdapter);
                                                rView.setLayoutManager(new LinearLayoutManager(ctx));
                                            }
                                        });
                                        Log.e("TEZ", response);

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                queue.add(stringRequest);
            }
        });
        t.start();
    }
}