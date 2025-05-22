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

import java.util.Objects;

public class Sushi extends AppCompatActivity {

    String[] countries = { "Уфа", "Новый Уренгой", "Санкт-Петербург", "Сеул"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sushi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        HorizontalScrollView scrollView = findViewById(R.id.horizontalScrollView);
        View leftGradient = findViewById(R.id.left_gradient);
        View rightGradient = findViewById(R.id.right_gradient);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            boolean atStart = scrollView.getScrollX() == 0;
            boolean atEnd = scrollView.getChildAt(0).getRight() <=
                    scrollView.getWidth() + scrollView.getScrollX();

            leftGradient.setVisibility(atStart ? View.INVISIBLE : View.VISIBLE);
            rightGradient.setVisibility(atEnd ? View.INVISIBLE : View.VISIBLE);
        });

        findViewById(R.id.ugor).setOnClickListener(v -> showUgorSushiDialog());
        findViewById(R.id.omlet).setOnClickListener(v -> showOmletSushiDialog());
        findViewById(R.id.losos).setOnClickListener(v -> showLososSushiDialog());
        findViewById(R.id.slivochny).setOnClickListener(v -> showSlivochnySushiDialog());
    }

    public void setNewActivity(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (itemId == R.id.cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("StringFormatInvalid")
    private void showSushiDialog(String title, String description, int imageRes, int price, String[] nutrition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.sushi_dialog, null);
        builder.setView(dialogView);

        ImageView pizzaImage = dialogView.findViewById(R.id.sushi_image);
        TextView titleView = dialogView.findViewById(R.id.sushi_title);
        TextView descView = dialogView.findViewById(R.id.sushi_description);
        TableLayout nutritionTable = dialogView.findViewById(R.id.nutrition_table);
        Button btnAdd = dialogView.findViewById(R.id.btn_add_to_cart);

        pizzaImage.setImageResource(imageRes);
        titleView.setText(title);
        descView.setText(description);
        btnAdd.setText(getString(R.string.add_to_cart, price));

        addNutritionRows(nutritionTable, nutrition);

        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        dialog.show();
    }

    private void addToCart(String title, int price) {
        // Реализация добавления в корзину
    }

    private void addNutritionRows(TableLayout table, String[] nutrition) {
        String[] labels = {"Калорийность", "Белки", "Жиры", "Углеводы"};

        for (int i = 0; i < labels.length; i++) {
            TableRow row = new TableRow(this);

            TextView label = new TextView(this);
            label.setText(labels[i]);
            label.setTextSize(15);
            row.addView(label);

            TextView dots = new TextView(this);
            dots.setTextSize(15);
            row.addView(dots);

            TextView value = new TextView(this);
            value.setText(nutrition[i]);
            value.setTextSize(15);
            row.addView(value);

            table.addView(row);
        }
    }


    public void showUgorSushiDialog() {
        String[] nutrition = {"181 ккал", "7 г", "8 г", "21 г"};
        showSushiDialog(
                "Роллы с угрем",
                "В составе угорь, авокадо, икра рыбная и сливочный сыр",
                R.drawable.sushi2,
                200,
                nutrition
        );
    }

    public void showOmletSushiDialog() {
        String[] nutrition = {"200 ккал", "13 г", "12 г", "30 г"};
        showSushiDialog(
                "Роллы с омлетом",
                "В составе омлет, свежие огурцы и помидоры",
                R.drawable.sushi3,
                190,
                nutrition
        );
    }

    public void showLososSushiDialog() {
        String[] nutrition = {"175 ккал", "8 г", "9 г", "20 г"};
        showSushiDialog(
                "Роллы с лососем",
                "В составе красная рыба, сливочный сыр и свежие огурцы",
                R.drawable.sushi4,
                220,
                nutrition
        );
    }

    public void showSlivochnySushiDialog() {
        String[] nutrition = {"170 ккал", "6 г", "7 г", "20 г"};
        showSushiDialog(
                "Роллы со сливочным сыром",
                "В составе сливочный сыр, соус Терияки и кунжутные семечки",
                R.drawable.sushi5,
                180,
                nutrition
        );
    }
}