package hu.aut.bme.android.aittodo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.aut.bme.android.aittodo.R;
import hu.aut.bme.android.aittodo.data.Todo;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private List<Todo> todoList;

    public TodoRecyclerAdapter() {
        todoList = new ArrayList<Todo>();
        for (int i = 0; i < 20; i++) {
            todoList.add(new Todo("Todo"+i,false));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_row,null,false);
        return new ViewHolder(todoRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cbDone.setChecked(todoList.get(position).isDone());
        holder.tvTodo.setText(todoList.get(position).getTodoTitle());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cbDone;
        private TextView tvTodo;

        public ViewHolder(View itemView) {
            super(itemView);
            cbDone = (CheckBox) itemView.findViewById(R.id.cbDone);
            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
        }
    }
}
