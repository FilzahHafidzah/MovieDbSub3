package com.filzah.moviedbsub3duty.adapter;

import android.view.View;

public class ClickItemAdapter implements View.OnClickListener {
    private onCallback onCallback;

    public ClickItemAdapter(onCallback onCallback) {
        this.onCallback = onCallback;
    }

    @Override
    public void onClick(View view) {
        onCallback.onItemClicked(view);
    }

    public interface onCallback{
        void onItemClicked(View view);
    }
}
