package com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class Video_fragment extends Fragment {

    VideoView videoView;
    Button btnPlay;
    ProgressBar videoProgressBar;
    String prefix;


    ImageView videoThumbnail;

    private static final String PREFIX = "prefix";

    private Video_fragment.OnFragmentInteractionListener mListener;


    public static Video_fragment newInstance(String prefix) {
        Video_fragment fragment = new Video_fragment();
        Bundle args = new Bundle();
        args.putString(PREFIX, prefix);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Video_fragment.OnFragmentInteractionListener) {
            mListener = (Video_fragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        prefix = getArguments().getString(PREFIX);

        videoThumbnail = v.findViewById(R.id.videoThumbnail);
        videoView = v.findViewById(R.id.videoView);
        btnPlay = v.findViewById(R.id.btnPlay);
        videoProgressBar = v.findViewById(R.id.videoProgressBar);

        videoView.setOnInfoListener(new OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
                    videoProgressBar.setVisibility(View.INVISIBLE);
                } else if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
                    videoProgressBar.setVisibility(View.VISIBLE);
                } else if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                    videoProgressBar.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                videoThumbnail.setVisibility(View.GONE);
                videoView.start();
                btnPlay.setVisibility(View.INVISIBLE);

            }
        });


        new LoadVideoThumbnail(getActivity()).execute("http://shiurdiario.com/media/video/" + prefix + ".mp4");


        String videoEndPoint = "http://shiurdiario.com/media/video/" + prefix + ".mp4";
        Uri videoUri = Uri.parse(videoEndPoint);
        videoView.setVideoURI(videoUri);

        MediaController vidController = new MediaController(getContext());
        vidController.setAnchorView(videoView);
        videoView.setMediaController(vidController);

        return v;
    }


    private class LoadVideoThumbnail extends AsyncTask<String, Void, Bitmap> {

        Activity context;
        Bitmap bitmap;

        public LoadVideoThumbnail(Activity context) {
            this.context = context;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                bitmap = Utils.retrieveVideoFrameFromVideo(strings[0]);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {
            super.onPostExecute(bitmap);
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnPlay.setVisibility(View.VISIBLE);
                    videoProgressBar.setVisibility(View.INVISIBLE);
                    videoThumbnail.setImageBitmap(bitmap);
                    videoView.setVisibility(View.VISIBLE);
                }
            });

        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
