package hu.aut.bme.android.aittodo;

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

public class MainActivity extends AppCompatActivity {

    private TodoRecyclerAdapter todoRecyclerAdapter;
    private RecyclerView recyclerTodo;

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
        todoRecyclerAdapter = new TodoRecyclerAdapter();

        ItemTouchHelper.Callback callback = new TodoItemTouchHelperCallback(todoRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerTodo);

        recyclerTodo.setAdapter(todoRecyclerAdapter);
    }

    private void setupFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
