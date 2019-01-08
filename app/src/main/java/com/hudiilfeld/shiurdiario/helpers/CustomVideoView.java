package com.hudiilfeld.shiurdiario.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

public class CustomVideoView extends VideoView {

    OnPlayPauseListener mListener;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setPlayPauseListener(OnPlayPauseListener listener) {
        mListener = listener;
    }

    @Override
    public void start() {
        super.start();
        mListener.onPlay();
    }

    @Override
    public void pause() {
        super.pause();
        mListener.onPause();
    }

    public interface OnPlayPauseListener {
        void onPlay();
        void onPause();
    }
}
