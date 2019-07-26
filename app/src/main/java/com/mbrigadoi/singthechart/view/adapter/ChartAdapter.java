package com.mbrigadoi.singthechart.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbrigadoi.singthechart.R;
import com.mbrigadoi.singthechart.presenter.ChartPresenter;
import com.mbrigadoi.singthechart.view.TrackClickListener;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {
    private final ChartPresenter mPresenter;
    private TrackClickListener mClickListener;

    public ChartAdapter(ChartPresenter presenter) {
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mPresenter.bindViewHolder(holder, position);

    }

    @Override
    public int getItemCount() {
        return mPresenter.getTracksCount();
    }

    public void setClickListener(TrackClickListener trackClickListener) {
        this.mClickListener = trackClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView coverArt;
        public TextView position;
        public TextView name;
        public TextView artist;
        public TextView album;

        private ViewHolder(View itemView) {
            super(itemView);

            coverArt = itemView.findViewById(R.id.image_cover);
            name = itemView.findViewById(R.id.text_song);
            position = itemView.findViewById(R.id.text_position);
            artist = itemView.findViewById(R.id.text_artist);
            album = itemView.findViewById(R.id.text_album);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null) {
                mClickListener.onClick(view, getAdapterPosition());
            }
        }
    }
}
