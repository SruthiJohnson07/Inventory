package com.project.inventorymanagement;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ViewInventoryActivity extends AppCompatActivity {

    ListView inventoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Inventory Vault");

        inventoryListView = findViewById(R.id.inventoryListView);


        ArrayList<String> inventoryItems = loadInventoryFromJson();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inventoryItems);
        inventoryListView.setAdapter(adapter);
    }

    private ArrayList<String> loadInventoryFromJson() {
        ArrayList<String> stockList = new ArrayList<>();

        try {
            InputStream is = getAssets().open("stock_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String jsonStr = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonStr);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("name");
                int quantity = obj.getInt("quantity");
                stockList.add(name + " - " + quantity + " units");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stockList;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
