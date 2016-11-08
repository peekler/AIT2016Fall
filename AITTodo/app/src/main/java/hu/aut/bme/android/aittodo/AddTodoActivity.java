package hu.aut.bme.android.aittodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.aut.bme.android.aittodo.data.Todo;

public class AddTodoActivity extends AppCompatActivity {

    public static final String KEY_TODO = "KEY_TODO";

    private Todo todoToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        final EditText etTodo = (EditText) findViewById(R.id.etTodo);

        if (getIntent() != null
                && getIntent().hasExtra(MainActivity.KEY_TODO_TO_EDIT)) {
            todoToEdit = (Todo) getIntent().getSerializableExtra(MainActivity.KEY_TODO_TO_EDIT);
            etTodo.setText(todoToEdit.getTodoTitle());
        }



        Button btnSave = (Button) findViewById(R.id.btnAddTodoItem);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();

                Todo newTodo = todoToEdit;
                if (newTodo == null) {
                    newTodo = new Todo(etTodo.getText().toString(),false);
                } else {
                    newTodo.setTodoTitle(etTodo.getText().toString());
                }


                result.putExtra(KEY_TODO, newTodo);

                setResult(RESULT_OK, result);
                finish();
            }
        });

    }
}
