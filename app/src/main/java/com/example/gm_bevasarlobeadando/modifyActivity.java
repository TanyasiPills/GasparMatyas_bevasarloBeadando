package com.example.gm_bevasarlobeadando;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class modifyActivity extends AppCompatActivity {

    EditText editName, editCount, editPrice, editUnit;

    Button backButton, deleteButton, modifyButton;

    RetrofitService service;

    Termekek termek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_item);

        int id = getIntent().getIntExtra("index",-0);

        service = RetrofitClient.getInstance().create(RetrofitService.class);

        Call<Termekek> call = service.getTermekById(id);
        call.enqueue(new Callback<Termekek>() {
            @Override
            public void onResponse(Call<Termekek> call, Response<Termekek> response) {
                if (response.isSuccessful()) {
                    termek = response.body();
                }
            }
            @Override
            public void onFailure(Call<Termekek> call, Throwable t) {
            }
        });

        if(termek == null) return;


        editName = findViewById(R.id.productName);
        editCount = findViewById(R.id.productCount);
        editPrice = findViewById(R.id.productPrice);
        editUnit = findViewById(R.id.productUnit);

        editName.setText(termek.getName());
        editCount.setText(termek.getCount());
        editPrice.setText(termek.getPricePerCount());
        editUnit.setText(termek.getUnit());

        backButton = findViewById(R.id.backToListBUtton);
        backButton.setOnClickListener(e -> finish());
        deleteButton = findViewById(R.id.deleteButton);
        modifyButton = findViewById(R.id.modifyButton);
    }
}
