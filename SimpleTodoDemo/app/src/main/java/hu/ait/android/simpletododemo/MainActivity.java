package hu.ait.android.simpletododemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTodo)
    EditText etTodo;
    @BindView(R.id.layoutTodos)
    LinearLayout layoutTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSave)
    public void saveClickHandler(Button btnSave) {
        View todoRow = getLayoutInflater().inflate(
                R.layout.todo_row, null, false);
        TextView tvTodo = (TextView) todoRow.findViewById(R.id.tvTodo);
        tvTodo.setText(etTodo.getText().toString());

        layoutTodos.addView(todoRow, 0);
    }

    @OnClick(R.id.btnClear)
    public void clearClickHandler(Button btnClear) {
        layoutTodos.removeAllViews();
    }

}
