package com.codepath.rkpandey.simpletodo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);

        loadItems();

        ItemAdapter.OnLongClickListener onLongClickListener = new ItemAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position){
                items.remove(position);
                itemAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item removed", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };
        itemAdapter = new ItemAdapter(items, onLongClickListener);
        rvItems.setAdapter(itemAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

btnAdd.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        String todoItem = etItem.getText().toString();
        items.add(todoItem);
        itemAdapter.notifyItemInserted(items.size()  - 1 );
        etItem.setText("");
        Toast.makeText(getApplicationContext(), "Item was Added", Toast.LENGTH_SHORT).show();
        saveItems();

    }
});
    }
    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }
    private void loadItems() {
        try{
            items= new ArrayList<>(FileUtils.readLines(getDataFile(), String.valueOf(Charset.defaultCharset())));
        } catch (IOException e){
            Log.e("MainActivity", "error reading", e);
            items = new ArrayList<>();
        }
    }
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        }catch (IOException e){
            Log.e("Mainactivity", "Error Writing", e);
        }
    }

}