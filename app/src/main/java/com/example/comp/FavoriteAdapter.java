package com.example.comp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    int []arr;

    public FavoriteAdapter(int[] arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_recycle_content, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.image_product.setImageResource(arr[position]);
        holder.title_pruduct.setText("Intel Core i5 4780k");
        holder.price_product.setText("20900p");
        readData();
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_product;
        TextView title_pruduct;
        TextView price_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_product = itemView.findViewById(R.id.image_product_fav);
            title_pruduct = itemView.findViewById(R.id.title_product);
            price_product = itemView.findViewById(R.id.price_product);
        }
    }
    public void readData(){


    }
}
