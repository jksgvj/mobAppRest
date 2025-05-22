package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    String[] city = { "Уфа", "Новый Уренгой", "Санкт-Петербург", "Сеул"};
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
        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, city);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);


        ImageView ivBurgers = findViewById(R.id.imageView1);
        ImageView ivPizza = findViewById(R.id.imageView4);
        ImageView ivSushi = findViewById(R.id.imageView3);
        ImageView ivMeat = findViewById(R.id.imageView7);
        ImageView ivSoup = findViewById(R.id.imageView8);
        ImageView ivShawarma = findViewById(R.id.imageView6);
        ImageView ivSnack = findViewById(R.id.imageView9);
        ImageView ivDessert = findViewById(R.id.imageView10);
        ImageView ivDrink = findViewById(R.id.imageView11);

        ivBurgers.setOnClickListener((View.OnClickListener) this);
        ivPizza.setOnClickListener((View.OnClickListener) this);
        ivSushi.setOnClickListener((View.OnClickListener) this);
        ivMeat.setOnClickListener((View.OnClickListener) this);
        ivSoup.setOnClickListener((View.OnClickListener) this);
        ivShawarma.setOnClickListener((View.OnClickListener) this);
        ivSnack.setOnClickListener((View.OnClickListener) this);
        ivDessert.setOnClickListener((View.OnClickListener) this);
        ivDrink.setOnClickListener((View.OnClickListener) this);


    }

    //public void setNewActivity(View v) {
    //    Intent intent = new Intent(this, Burgers.class);
    //    startActivity(intent);
    //}
    @Override
    public void onClick(View v) {
        Intent intent;
        int clickedId = v.getId();

        if (clickedId == R.id.imageView1) {
            intent = new Intent(this, Burgers.class);
        } else if (clickedId == R.id.imageView4) {
            intent = new Intent(this, Pizza.class);
        } else if (clickedId == R.id.imageView3) {
            intent = new Intent(this, Sushi.class);
        } else if (clickedId == R.id.imageView7) {
            intent = new Intent(this, Meat.class);
        } else if (clickedId == R.id.imageView8) {
            intent = new Intent(this, Soup.class);
        } else if (clickedId == R.id.imageView6) {
            intent = new Intent(this, Shawarma.class);
        } else if (clickedId == R.id.imageView9) {
            intent = new Intent(this, Snack.class);
        } else if (clickedId == R.id.imageView10) {
            intent = new Intent(this, Dessert.class);
        } else if (clickedId == R.id.imageView11) {
            intent = new Intent(this, Drink.class);
        } else {
            return; // если ни один ImageView не совпал
        }

        startActivity(intent);
    }
}


