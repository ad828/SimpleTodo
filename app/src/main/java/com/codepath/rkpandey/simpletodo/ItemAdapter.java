package com.codepath.rkpandey.simpletodo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;

    public ItemAdapter(List<String> items, OnLongClickListener longClickListener){
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate a view
        View todoView;
        todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
    //Grab Item at the position
        String item = items.get(position);
        //Bind the item onto the spiecified itm in virw holder
    holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views hat represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{
            TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
     public void bind(String item){
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
     }
    }
}
