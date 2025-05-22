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


public class Meat extends AppCompatActivity {

    String[] countries = { "Уфа", "Новый Уренгой", "Санкт-Петербург", "Сеул"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meat);
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

        findViewById(R.id.steyk).setOnClickListener(v -> showSteykMeatDialog());
        findViewById(R.id.shashlyk).setOnClickListener(v -> showShashlykMeatDialog());
        findViewById(R.id.lagman).setOnClickListener(v -> showLagmanMeatDialog());

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
    private void showMeatDialog(String title, String description, int imageRes, int price, String[] nutrition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.meat_dialog, null);
        builder.setView(dialogView);

        // Настройка содержимого
        ImageView burgerImage = dialogView.findViewById(R.id.meat_image);
        TextView titleView = dialogView.findViewById(R.id.meat_title);
        TextView descView = dialogView.findViewById(R.id.meat_description);
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

    public void showSteykMeatDialog() {
        String[] nutrition = {"920 ккал", "65 г", "70 г", "4 г"};
        showMeatDialog(
                "Стейк",
                "В составе мраморная говядина",
                R.drawable.meat2,
                430,
                nutrition
        );
    }

    public void showShashlykMeatDialog() {
        String[] nutrition = {"560 ккал", "76 г", "24 г", "4 г"};
        showMeatDialog(
                "Шашлык",
                "В составе говядина и запеченый картофель",
                R.drawable.meat3,
                400,
                nutrition
        );
    }

    public void showLagmanMeatDialog() {
        String[] nutrition = {"320 ккал", "32 г", "13 г", "19 г"};
        showMeatDialog(
                "Лагман",
                "В составе куриное филе, болгарский перец, лук, помидоры и лапша",
                R.drawable.meat4,
                290,
                nutrition
        );
    }

}