package com.project.inventorymanagement;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddProductActivity extends AppCompatActivity {

    EditText editProductName, editQuantity;
    Button btnSave;
    TextView textSavedMessage;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Set ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("Inventory Vault");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Initialize UI
        editProductName = findViewById(R.id.editProductName);
        editQuantity = findViewById(R.id.editQuantity);
        btnSave = findViewById(R.id.btnSave);
        textSavedMessage = findViewById(R.id.textSavedMessage);

        btnSave.setOnClickListener(view -> {
            String name = editProductName.getText().toString().trim();
            String quantity = editQuantity.getText().toString().trim();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(quantity)) {
                Map<String, Object> product = new HashMap<>();
                product.put("name", name);
                product.put("quantity", quantity);

                db.collection("products")
                        .add(product)
                        .addOnSuccessListener(documentReference -> {
                            textSavedMessage.setVisibility(View.VISIBLE);
                            textSavedMessage.setText("✅ Product added!");
                            editProductName.setText("");
                            editQuantity.setText("");
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                textSavedMessage.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
