package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MoreActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more);
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
}
