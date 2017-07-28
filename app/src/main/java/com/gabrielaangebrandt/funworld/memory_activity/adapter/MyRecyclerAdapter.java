package com.gabrielaangebrandt.funworld.memory_activity.adapter;

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

    int[] drawables = new int[8];
    List<String> numbers;
    public MyRecyclerAdapter(int[] drawables) {
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
        numbers = new ArrayList();
        for (int j = 0; j < 8; j++) {
            String random = String.valueOf(Math.random() * 8 + 1);
            for (int i = 0; i < numbers.size(); i++) {
                if (random == numbers.get(i)) {
                    Log.d("error", "ovo već imamo" + random);

                } else {
                    numbers.add(random);
                    holder.flag.setImageResource(drawables[Integer.parseInt(random)]);
                    random = String.valueOf(Math.random() * 8 + 1);

                }
            }
     /*       list = android.R.drawable.class.getFields();
        for (Field f : list) {
            try {
              //
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
        }
    }


    @Override
    public int getItemCount() {

        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView flag;
        public ViewHolder(View view) {
            super(view);
            flag = (ImageView) view.findViewById(R.id.iv_memory_element);
        }
    }
}
