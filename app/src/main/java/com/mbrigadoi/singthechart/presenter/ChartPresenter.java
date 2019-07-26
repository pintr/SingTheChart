package com.mbrigadoi.singthechart.presenter;

import android.app.Activity;

import com.mbrigadoi.singthechart.view.adapter.ChartAdapter.ViewHolder;

public interface ChartPresenter {
    void loadChart(String country);
    void bindViewHolder(ViewHolder holder, int position);
    int getTracksCount();
    void trackClick(Activity activity, int position);


}
