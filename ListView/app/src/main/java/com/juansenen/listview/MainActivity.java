package com.juansenen.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Task> taskslist;
    public ArrayAdapter<Task> adapter;
    private Button butAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicalización de la lista

        taskslist = new ArrayList<>();
        taskslist.add(new Task(""));

        //Recuperamos el elemento ListView
        ListView listView = findViewById(R.id.listView_task);
        //Le asignamos el adapter
        listView.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskslist);
        listView.setAdapter(adapter);
        //Menu contextual ListView
        registerForContextMenu(listView);




    }

    //Inflamos el submenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.submenu,menu);
    }
    //Metodos al pulsar sobre el submenu

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Por medio del Adapter recuperamos el elemento que se ha pulsado
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int itemPosition = menuInfo.position;

        if (item.getItemId() == R.id.item_delete){
        taskslist.remove(itemPosition);
        //Notificamos cmabios al adapter
        adapter.notifyDataSetChanged();
        //Mensaje emergente de eliminado
        Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT);
            return true;
        }
        return super.onContextItemSelected(item);

    }

    public void presionaBoton(View view){
        EditText editText = findViewById(R.id.editText_task);
        String txt = editText.getText().toString();

        if (txt.isEmpty()){
            return;
        }

            taskslist.add(new Task(txt));
            adapter.notifyDataSetChanged();
            editText.setText("");
            Toast.makeText(this,"Añadido",Toast.LENGTH_SHORT);
        }


}
