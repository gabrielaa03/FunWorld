package com.gabrielaangebrandt.funworld.memory_activity.adapter;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gabrielaangebrandt.funworld.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gabriela on 25.7.2017..
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    List<Integer> drawables = new ArrayList<>();
    private int clickCounter =0;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_element_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final List<Integer> first = new ArrayList<>();
        final List<Integer> second = new ArrayList<>();
        holder.setFlagDrawable(drawables.get(position));
    }

    @Override
    public int getItemCount() {
        return drawables.size();
    }

    public void addDataToAdapter(List<Integer> definedDrawables) {
        drawables.clear();
        drawables.addAll(definedDrawables);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        boolean isClicked = false;
        ImageView flag;
        int flagDrawable;

        ViewHolder(View view) {
            super(view);
            flag = (ImageView) view.findViewById(R.id.iv_memory_element);
            flag.setOnClickListener(this);
        }

        public void setFlagDrawable(int flagDrawable) {
            this.flagDrawable = flagDrawable;
        }

        public void onClick(View v){
            isClicked = !isClicked;
            flag.setImageResource(isClicked ? flagDrawable : R.drawable.back);
/*
            if (clickCounter == 0) {
                first.add(drawables.get(position));
                clickCounter++;
            } else if (clickCounter==1) {
                second.add(drawables.get(position));

                if (first.equals(second)) {
                    Log.d("error" , "povecao se broj pronadjenih parova ");
                    first.clear();
                    second.clear();

                } else {

                }

                clickCounter = 0;
            }
            */
        }
    }
}
