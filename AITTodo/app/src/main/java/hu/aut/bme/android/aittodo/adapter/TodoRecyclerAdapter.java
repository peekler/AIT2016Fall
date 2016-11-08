package hu.aut.bme.android.aittodo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.aut.bme.android.aittodo.EditInterface;
import hu.aut.bme.android.aittodo.R;
import hu.aut.bme.android.aittodo.data.Todo;

public class TodoRecyclerAdapter extends
        RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private List<Todo> todoList;
    private EditInterface editInterface;

    public TodoRecyclerAdapter(EditInterface editInterface) {
        this.editInterface = editInterface;

        todoList = Todo.listAll(Todo.class);

        /*todoList = new ArrayList<Todo>();
        for (int i = 0; i < 20; i++) {
            todoList.add(new Todo("Todo"+i,false));
        }*/

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_row,parent,false);
        return new ViewHolder(todoRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cbDone.setChecked(todoList.get(position).isDone());
        holder.tvTodo.setText(todoList.get(position).getTodoTitle());

        holder.tvTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInterface.showEditDialog(
                        todoList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        todoList.get(position).delete();

        todoList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        /*if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todoList, i, i - 1);
            }
        }*/

        //Collections.

        todoList.add(toPosition, todoList.get(fromPosition));
        todoList.remove(fromPosition);


        notifyItemMoved(fromPosition, toPosition);
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

    public void addTodo(Todo todo) {
        todo.save();
        todoList.add(0, todo);

        // refresh the whole list
        //notifyDataSetChanged();
        // refresh only one position
        notifyItemInserted(0);
    }

    public void edit(Todo todo, int position) {

        todo.save();
        todoList.set(position, todo);
        notifyItemChanged(position);
    }
}
