package com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.view_models.RetrievePdf;


/**
 * A simple {@link Fragment} subclass.
 */
public class GemaraText_tab extends Fragment {

    PDFView pdfViewer;
    ProgressBar pdfProgressBar;
    String prefix;

    public static final String PREFIX = "prefix";

    private GemaraText_tab.OnFragmentInteractionListener mListener;


    public static GemaraText_tab newInstance(String prefix) {
        GemaraText_tab fragment = new GemaraText_tab();
        Bundle args = new Bundle();
        args.putString(PREFIX, prefix);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GemaraText_tab.OnFragmentInteractionListener) {
            mListener = (GemaraText_tab.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        prefix = getArguments().getString(PREFIX);

        pdfViewer = v.findViewById(R.id.pdfViewer);
        pdfProgressBar = v.findViewById(R.id.pdfProgressBar);


        new RetrievePdf(getActivity(), pdfViewer, pdfProgressBar).execute("http://shiurdiario.com/media/pdf/" + prefix + ".pdf");

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
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
