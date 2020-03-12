package com.cse.cynosure.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.cse.cynosure.Model.Sponsors_Model;
import com.cse.cynosure.R;

import java.util.ArrayList;

public class sponsors_adapter extends RecyclerView.Adapter<sponsors_adapter.ViewHolder> {
    private ArrayList<Sponsors_Model> android;
    private Context context;

    public sponsors_adapter(Context context,ArrayList<Sponsors_Model> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public sponsors_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sponsors_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(sponsors_adapter.ViewHolder viewHolder, int i) {
        viewHolder.img.setImageResource(android.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.sponsors_image);
        }
    }

}
