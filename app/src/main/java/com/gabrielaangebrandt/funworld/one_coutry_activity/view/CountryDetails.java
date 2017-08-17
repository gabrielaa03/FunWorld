package com.gabrielaangebrandt.funworld.one_coutry_activity.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.one_coutry_activity.CountryContract;
import com.gabrielaangebrandt.funworld.one_coutry_activity.presenter.CountryPresenterImpl;
import com.gabrielaangebrandt.funworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryDetails extends AppCompatActivity implements CountryContract.CountryView {
    @BindView(R.id.countryTitle)
    TextView tv_name;
    @BindView(R.id.tv_population_value)
    TextView tv_population;
    @BindView(R.id.tv_region_value)
    TextView tv_region;
    @BindView(R.id.tv_area_value)
    TextView tv_area;
    @BindView(R.id.tv_alphacode_value)
    TextView tv_alphaCode;
    @BindView(R.id.tv_capital_value)
    TextView tv_capital;
    @BindView(R.id.tv_language_value)
    TextView tv_language;
    @BindView(R.id.tv_currency_value)
    TextView tv_currency;
    @BindView(R.id.iv_flag)
    ImageView imageView;

    CountryContract.CountryPresenter presenter;
    String nameOfCountry = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_view_layout);
        ButterKnife.bind(this);

        presenter = new CountryPresenterImpl(this);
        nameOfCountry = getIntent().getExtras().getString("nameOfCountry");
        presenter.getName(nameOfCountry);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void sendData(String name, Integer population, Double area, String region, String alphaCode3, String alphaCode2, String capital, String language, String currency) {
        String lowercaseAlphaCode2 = alphaCode2.toLowerCase();
        tv_name.setText(nameOfCountry);
        tv_population.setText(String.valueOf(population));
        tv_region.setText(region);
        tv_alphaCode.setText(alphaCode3);
        tv_capital.setText(capital);
        tv_area.setText(String.valueOf(area));
        tv_currency.setText(String.valueOf(currency));
        tv_language.setText(String.valueOf(language));
        imageView.setImageResource(getResources().getIdentifier("com.gabrielaangebrandt.funworld:drawable/" + lowercaseAlphaCode2, null, null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
