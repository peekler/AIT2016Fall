package hu.aut.bme.android.aittodo.adapter;

public interface TodoTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);

}
