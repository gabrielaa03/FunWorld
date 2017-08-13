package com.gabrielaangebrandt.funworld.main_activity.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.one_coutry_activity.view.CountryDetails;
import com.gabrielaangebrandt.funworld.R;

import java.util.List;

/**
 * Created by Gabriela on 25.7.2017..
 */

public class RecyclerViewAdapterCountries extends RecyclerView.Adapter<RecyclerViewAdapterCountries.ViewHolder> {

    private List<String> list;
    public RecyclerViewAdapterCountries(List<String> listOfCountries) {
        this.list = listOfCountries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_of_lis_for_countries, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String country = list.get(position);
        holder.name.setText(country);
    }

    @Override
    public int getItemCount() {
        { return (list == null) ? 0 : list.size();}
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView name ;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name_of_country);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent  = new Intent(view.getContext(), CountryDetails.class);
            intent.putExtra("nameOfCountry", list.get(getAdapterPosition()));
            view.getContext().startActivity(intent);
        }
    }
}
