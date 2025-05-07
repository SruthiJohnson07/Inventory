package com.project.inventorymanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    EditText editProductName, editQuantity;
    Button btnSave;
    TextView textSavedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // 🔄 Left-aligned default ActionBar title
        getSupportActionBar().setTitle("Inventory Vault");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // UI elements
        editProductName = findViewById(R.id.editProductName);
        editQuantity = findViewById(R.id.editQuantity);
        btnSave = findViewById(R.id.btnSave);
        textSavedMessage = findViewById(R.id.textSavedMessage);

        btnSave.setOnClickListener(v -> {
            String name = editProductName.getText().toString();
            String quantity = editQuantity.getText().toString();

            if (name.isEmpty() || quantity.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                textSavedMessage.setVisibility(View.GONE);
            } else {
                textSavedMessage.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
