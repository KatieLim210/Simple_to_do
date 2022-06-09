package sg.edu.rp.c346.id21023701.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTasks;
    Button btnAdd;
    Button btnClear;
    Button btnDel;
    ListView listView;
    Spinner spinnerlist;
    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTasks = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        listView = findViewById(R.id.lv);
        spinnerlist = findViewById(R.id.spinner);

        alTodo = new ArrayList<>();

        aaTodo = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,alTodo);
        listView.setAdapter(aaTodo);
        spinnerlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTasks.setHint("Enter a new task");

                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alTodo.clear();
                                aaTodo.notifyDataSetChanged();
                            }
                        });
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newtask = etTasks.getText().toString();
                                alTodo.add(newtask);
                                aaTodo.notifyDataSetChanged();
                            }


                        });
                        break;
                    case 1:
                        btnDel.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTasks.setHint("Enter the index of the task to be removed");

                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int taskid = Integer.parseInt(etTasks.getText().toString());

                                if(alTodo.size() == 0){
                                    Toast.makeText(MainActivity.this,"You don't have any tasks to remove",Toast.LENGTH_LONG).show();

                                }else if(alTodo.size() != 0 ){
                                    for(int i = 0; i < alTodo.size();i++){
                                        if(taskid == i){
                                            alTodo.remove(taskid);
                                            aaTodo.notifyDataSetChanged();
                                        }else{
                                            Toast.makeText(MainActivity.this,"Wrong index entered",Toast.LENGTH_LONG).show();

                                        }
                                    }
                                }



                                }

                        });
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alTodo.clear();
                                aaTodo.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}