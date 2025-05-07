package com.project.inventorymanagement;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

public class ViewInventoryActivity extends AppCompatActivity {

    ListView inventoryListView;
    FirebaseFirestore db;
    ArrayList<String> inventoryData;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);

        // Set ActionBar title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Inventory Vault");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        inventoryListView = findViewById(R.id.inventoryListView);
        inventoryData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inventoryData);
        inventoryListView.setAdapter(adapter);

        // Fetch data from Firestore "products" collection
        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    inventoryData.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String name = doc.getString("name");
                        String quantity = doc.getString("quantity");
                        String item = " " + name + " - " + quantity + " units";
                        inventoryData.add(item);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to load inventory", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
