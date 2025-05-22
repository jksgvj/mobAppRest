package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Soup extends AppCompatActivity {

    String[] countries = { "Уфа", "Новый Уренгой", "Санкт-Петербург", "Сеул"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_soup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        HorizontalScrollView scrollView = findViewById(R.id.horizontalScrollView);
        View leftGradient = findViewById(R.id.left_gradient);
        View rightGradient = findViewById(R.id.right_gradient);

        // Слушатель прокрутки
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            // Проверяем позицию прокрутки
            boolean atStart = scrollView.getScrollX() == 0;
            boolean atEnd = scrollView.getChildAt(0).getRight() <=
                    scrollView.getWidth() + scrollView.getScrollX();

            // Управляем градиентами
            leftGradient.setVisibility(atStart ? View.INVISIBLE : View.VISIBLE);
            rightGradient.setVisibility(atEnd ? View.INVISIBLE : View.VISIBLE);
        });

        findViewById(R.id.myso).setOnClickListener(v -> showMysoSoupDialog());
        findViewById(R.id.solyanka).setOnClickListener(v -> showSolyankaSoupDialog());
        findViewById(R.id.tomyam).setOnClickListener(v -> showTomyamSoupDialog());

    }

    public void setNewActivity(MenuItem item) {
        // Проверяем ID пункта меню
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (itemId == R.id.cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        //else if (itemId == R.id.more) {
        //    Intent intent = new Intent(this, MoreActivity.class);
        //    startActivity(intent);
        //}
    }





    // Общий метод для создания диалога
    @SuppressLint("StringFormatInvalid")
    private void showSoupDialog(String title, String description, int imageRes, int price, String[] nutrition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.soup_dialog, null);
        builder.setView(dialogView);

        // Настройка содержимого
        ImageView burgerImage = dialogView.findViewById(R.id.soup_image);
        TextView titleView = dialogView.findViewById(R.id.soup_title);
        TextView descView = dialogView.findViewById(R.id.soup_description);
        TableLayout nutritionTable = dialogView.findViewById(R.id.nutrition_table);
        Button btnAdd = dialogView.findViewById(R.id.btn_add_to_cart);

        burgerImage.setImageResource(imageRes);
        titleView.setText(title);
        descView.setText(description);
        btnAdd.setText(getString(R.string.add_to_cart, price));

        // Добавляем данные о питательности
        addNutritionRows(nutritionTable, nutrition);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        dialog.show();
    }

    private void addToCart(String title, int price) {

    }

    // Добавление строк с питательностью
    private void addNutritionRows(TableLayout table, String[] nutrition) {
        String[] labels = {"Калорийность", "Белки", "Жиры", "Углеводы"};

        for (int i = 0; i < labels.length; i++) {
            TableRow row = new TableRow(this);

            TextView label = new TextView(this);
            label.setText(labels[i]);
            label.setTextSize(15);
            row.addView(label);

            TextView dots = new TextView(this);
            //dots.setText("......");
            dots.setTextSize(15);
            row.addView(dots);

            TextView value = new TextView(this);
            value.setText(nutrition[i]);
            value.setTextSize(15);
            row.addView(value);

            table.addView(row);
        }
    }

    public void showMysoSoupDialog() {
        String[] nutrition = {"134 ккал", "13 г", "5 г", "3 г"};
        showSoupDialog(
                "Мисо суп",
                "В составе грибы Шиитаке, Тофу и рыбный бульон",
                R.drawable.soup3,
                300,
                nutrition
        );
    }

    public void showSolyankaSoupDialog() {
        String[] nutrition = {"805 ккал", "41 г", "58 г", "8 г"};
        showSoupDialog(
                "Солянка",
                "В составе ветчина, копченая колбаса, оливки, корнишоны и каперсы",
                R.drawable.soup4,
                390,
                nutrition
        );
    }

    public void showTomyamSoupDialog() {
        String[] nutrition = {"1465 ккал", "25 г", "147 г", "13 г"};
        showSoupDialog(
                "Том Ям",
                "В составе креветки, шампиньоны, помидоры Черри, лайм и зелень кинзы",
                R.drawable.soup2,
                390,
                nutrition
        );
    }

}