package com.gabrielaangebrandt.funworld.memory_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gabrielaangebrandt.funworld.R;

import java.util.List;


/**
 * Created by Gabriela on 25.7.2017..
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    List<String> drawables;
    public MyRecyclerAdapter(List<String> drawables) {
        this.drawables = drawables;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_element_image, parent, false);
        ViewHolder memoryViewHolder = new ViewHolder(view);
        return memoryViewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
                 holder.flag.setImageResource(R.drawable.ad);

        }

    @Override
    public int getItemCount() {
        return drawables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView flag;
        public ViewHolder(View view) {
            super(view);
            flag = (ImageView) view.findViewById(R.id.iv_memory_element);
        }
    }
}
