package com.mbrigadoi.singthechart.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mbrigadoi.singthechart.R;
import com.mbrigadoi.singthechart.presenter.ChartPresenter;
import com.mbrigadoi.singthechart.presenter.ChartPresenterImpl;
import com.mbrigadoi.singthechart.view.adapter.ChartAdapter;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements TrackClickListener {
    ChartPresenter mPresenter;

    Toolbar mToolbarMain;
    Spinner mSpinnerCountry;

    RecyclerView mChartView;
    ChartAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ChartPresenterImpl();
        setContentView(R.layout.activity_main);
        mChartView = findViewById(R.id.view_chart);
        mToolbarMain = findViewById(R.id.toolbar_main);
        mToolbarMain.setTitle(getResources().getString(R.string.app_name));
        setSpinner();

        setChartView(getResources().getString(R.string.default_country));
    }

    @Override
    public void onClick(View view, int position) {
        mPresenter.trackClick(this, position);
    }

    private void setChartView(String country) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mPresenter.loadChart(country);
        mChartView.setHasFixedSize(true);
        mChartView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChartAdapter(mPresenter);
        mChartView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void setSpinner() {
        mSpinnerCountry = findViewById(R.id.spinner_country);

        try {
            Field popup = Spinner.class.getDeclaredField(getString(R.string.field_pupop));
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow =
                    (android.widget.ListPopupWindow) popup.get(mSpinnerCountry);
            popupWindow.setHeight(500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(MainActivity.this,
                    R.layout.item_countries_spinner,
                    getResources().getStringArray(R.array.countries));
            countriesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            mSpinnerCountry.setAdapter(countriesAdapter);

            mSpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    setChartView(mSpinnerCountry.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) { }
            });
        }
    }
}
