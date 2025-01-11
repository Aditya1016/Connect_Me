package com.example.adi_and_div;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CustomSearchableSpinner {

    private final Context context;
    private final String[] items;
    private final String dialogTitle;

    public CustomSearchableSpinner(Context context, String[] items, String dialogTitle) {
        this.context = context;
        this.items = items;
        this.dialogTitle = dialogTitle;
    }

    public void showSearchableSpinner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(dialogTitle);

        // Add a search box
        EditText input = new EditText(context);
        input.setHint("Type to search...");
        builder.setView(input);

        // Create a list adapter for the items
        ListView listView = new ListView(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        builder.setView(listView);

        AlertDialog dialog = builder.create();

        // Filter list as user types
        input.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        // Handle item selection
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = adapter.getItem(position);
            Toast.makeText(context, "Selected: " + selected, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}