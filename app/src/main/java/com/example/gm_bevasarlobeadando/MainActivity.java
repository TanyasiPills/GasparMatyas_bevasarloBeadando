package com.example.gm_bevasarlobeadando;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText itemName, itemCount, itemPrice, itemUnit;

    Button submitButton, listButton;

    RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
    }
    private void initialize(){
        itemName = findViewById(R.id.itemName);
        itemCount = findViewById(R.id.itemCount);
        itemPrice = findViewById(R.id.itemPrice);
        itemUnit = findViewById(R.id.itemUnit);

        submitButton = findViewById(R.id.submitButton);
        listButton = findViewById(R.id.listButton);

        service = RetrofitClient.getInstance().create(RetrofitService.class);
        listButton.setOnClickListener(e -> {
            Intent intent = new Intent(MainActivity.this, ListViewStuff.class);
            startActivity(intent);
        });
        submitButton.setOnClickListener( e -> {
            String nameOfItem = itemName.getText().toString();
            String countOfItem = itemCount.getText().toString();
            String priceOfItem = itemPrice.getText().toString();
            String unitOfItem = itemUnit.getText().toString();

            if (nameOfItem.isEmpty() || countOfItem.isEmpty() || priceOfItem.isEmpty() || unitOfItem.isEmpty()) {
                Toast.makeText(this, "Töltsd ki az összes bemenetet", Toast.LENGTH_SHORT).show();
                return;
            }

            int countToItem = Integer.parseInt(countOfItem);
            int priceToItem = Integer.parseInt(priceOfItem);

            Termekek termek = new Termekek(nameOfItem, priceToItem, countToItem, unitOfItem);


            Call<Termekek> call = service.createTermek(termek);
            call.enqueue(new Callback<Termekek>() {
                @Override
                public void onResponse(Call<Termekek> call, Response<Termekek> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Termék hozzáadva!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Hiba történt!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Termekek> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Hálózati hiba: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}