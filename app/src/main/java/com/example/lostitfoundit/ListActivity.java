package com.example.lostitfoundit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private ListView myListView;
    ArrayAdapter<String> myAdapter;
    EditText inputSearch;
    ArrayList<HashMap<String, String>> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String products[] = {"Airpods 2", "Charger", "Keys", "Id Card", "Apple Pencil",
                "iPhone 4S", "Wallet",
                "Guess Watch", "MacBook Air", "Logitech Mouse", "Earring"};
        myListView = (ListView) findViewById(R.id.editlist_view);
        inputSearch = (EditText) findViewById(R.id.itemSearch);
        myAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        myListView.setAdapter(myAdapter);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                ListActivity.this.myAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }
}