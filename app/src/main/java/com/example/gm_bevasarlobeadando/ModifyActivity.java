package com.example.gm_bevasarlobeadando;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyActivity extends AppCompatActivity {

    EditText editName, editCount, editPrice, editUnit;

    Button backButton, deleteButton, modifyButton;

    RetrofitService service;

    Termekek termek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_item);

        int id = getIntent().getIntExtra("index",-1);

        service = RetrofitClient.getInstance().create(RetrofitService.class);

        Call<Termekek> call = service.getTermekById(id);
        call.enqueue(new Callback<Termekek>() {
            @Override
            public void onResponse(Call<Termekek> call, Response<Termekek> response) {
                if (response.isSuccessful() && response.body() != null) {
                    termek = response.body();

                    editName = findViewById(R.id.productName);
                    editCount = findViewById(R.id.productCount);
                    editPrice = findViewById(R.id.productPrice);
                    editUnit = findViewById(R.id.productUnit);

                    editName.setText(termek.getName());
                    editCount.setText(termek.getCount());
                    editPrice.setText(termek.getPricePerCount());
                    editUnit.setText(termek.getUnit());
                }
            }
            @Override
            public void onFailure(Call<Termekek> call, Throwable t) {
                Log.e("shanya",t.getMessage());
            }
        });

        backButton = findViewById(R.id.backToListBUtton);
        backButton.setOnClickListener(e -> finish());
        deleteButton = findViewById(R.id.deleteButton);
        int finalId = id;
        deleteButton.setOnClickListener(e -> {
            Call<Void> call2 = service.deleteTermek(finalId);
            call2.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful()) {
                        Log.e("shanya","cant delete :c");
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("shanya",t.getMessage());
                }
            });
            Intent intent = new Intent(ModifyActivity.this, ListViewStuff.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
        modifyButton = findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(e -> {
            Termekek newTermek = new Termekek(editName.getText().toString(),Integer.valueOf(editPrice.getText().toString()),Integer.valueOf(editCount.getText().toString()),editUnit.getText().toString());
            Call<Termekek> call3 = service.updateTermek(id,newTermek);
            call3.enqueue(new Callback<Termekek>() {
                @Override
                public void onResponse(Call<Termekek> call, Response<Termekek> response) {
                    if (!response.isSuccessful()) {
                        Log.e("shanya","cant delete :c");
                    }
                }
                @Override
                public void onFailure(Call<Termekek> call, Throwable t) {
                    Log.e("shanya",t.getMessage());
                }
            });
            Intent intent = new Intent(ModifyActivity.this, ListViewStuff.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
