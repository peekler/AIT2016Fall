package hu.aut.bme.android.aittodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.aut.bme.android.aittodo.adapter.TodoItemTouchHelperCallback;
import hu.aut.bme.android.aittodo.adapter.TodoRecyclerAdapter;
import hu.aut.bme.android.aittodo.data.Todo;

public class MainActivity extends AppCompatActivity implements EditInterface {

    public static final int REQUEST_CODE_ADD = 100;
    public static final String KEY_TODO_TO_EDIT = "KEY_TODO_TO_EDIT";
    public static final int REQUEST_CODE_EDIT = 101;
    private TodoRecyclerAdapter todoRecyclerAdapter;
    private RecyclerView recyclerTodo;
    private int positionToEdit = -1;
    private Long idToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI() {
        setupToolbar();
        setupFloatingActionButton();
        setupRecyclerView();
        setupAddTodo();
    }

    private void setupRecyclerView() {
        recyclerTodo = (RecyclerView) findViewById(
                R.id.recyclerTodo);
        recyclerTodo.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerTodo.setLayoutManager(mLayoutManager);


        todoRecyclerAdapter = new TodoRecyclerAdapter(this);



        ItemTouchHelper.Callback callback = new TodoItemTouchHelperCallback(todoRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerTodo);

        recyclerTodo.setAdapter(todoRecyclerAdapter);
    }

    private void setupFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddTodo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShowAdd = new Intent();
                intentShowAdd.setClass(MainActivity.this, AddTodoActivity.class);
                startActivityForResult(intentShowAdd, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD) {
                // add todo to the list
                Todo newTodo = (Todo) data.getSerializableExtra(
                        AddTodoActivity.KEY_TODO);

                todoRecyclerAdapter.addTodo(newTodo);
                recyclerTodo.scrollToPosition(0);
            } else if (requestCode == REQUEST_CODE_EDIT) {

                Todo changedTodo = (Todo) data.getSerializableExtra(
                        AddTodoActivity.KEY_TODO);
                changedTodo.setId(idToEdit);

                todoRecyclerAdapter.edit(changedTodo, positionToEdit);


            }
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void setupAddTodo() {
        final EditText etTodo = (EditText) findViewById(R.id.etTodo);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoRecyclerAdapter.addTodo(new Todo(etTodo.getText().toString(), false));
                recyclerTodo.scrollToPosition(0);
            }
        });
    }

    @Override
    public void showEditDialog(Todo todoToEdit, int position) {
        positionToEdit = position;
        idToEdit = todoToEdit.getId();
        Intent intentShowEdit = new Intent();
        intentShowEdit.setClass(MainActivity.this, AddTodoActivity.class);
        intentShowEdit.putExtra(KEY_TODO_TO_EDIT,todoToEdit);
        startActivityForResult(intentShowEdit, REQUEST_CODE_EDIT);
    }
}
