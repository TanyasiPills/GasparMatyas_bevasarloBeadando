package com.example.gm_bevasarlobeadando;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewStuff extends AppCompatActivity {

    Button back;

    RetrofitService service;

    ListView listViewnop;

    Adapter adapter;

    List<Termekek> termekek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        listViewnop = findViewById(R.id.listView);


        back = findViewById(R.id.backButton);
        back.setOnClickListener(e -> finish());

        service = RetrofitClient.getInstance().create(RetrofitService.class);

        termekek = new ArrayList<>();
        adapter = new Adapter(this,termekek);
        listViewnop.setAdapter(adapter);

        Call<List<Termekek>> call = service.getTermek();
        call.enqueue(new Callback<List<Termekek>>() {
            @Override
            public void onResponse(Call<List<Termekek>> call, Response<List<Termekek>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    termekek.clear();
                    termekek.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ListViewStuff.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Termekek>> call, Throwable t) {
                Toast.makeText(ListViewStuff.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
