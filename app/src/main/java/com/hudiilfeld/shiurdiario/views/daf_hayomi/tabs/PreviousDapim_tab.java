package com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hudiilfeld.shiurdiario.App;
import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.DapimAdapter;
import com.hudiilfeld.shiurdiario.models.Daf;
import com.hudiilfeld.shiurdiario.models.WebResponse_daf;
import com.hudiilfeld.shiurdiario.repositories.DapimRepo;
import com.hudiilfeld.shiurdiario.view_models.DapimViewModel;
import com.hudiilfeld.shiurdiario.view_models.SuperViewModel;
import com.hudiilfeld.shiurdiario.view_models.viewModelProvides.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousDapim_tab extends Fragment {

    RecyclerView dapimRV;
    List<Daf> data;
    @Inject ViewModelFactory<DapimRepo> factory;
    @Inject DapimRepo dapimRepo;
    DapimViewModel viewModel;



    private PreviousDapim_tab.OnFragmentInteractionListener mListener;

    public static final String CURRENT_DATE = "currentDate";

    public static PreviousDapim_tab newInstance(String currentDate) {
        PreviousDapim_tab fragment = new PreviousDapim_tab();
        Bundle args = new Bundle();
        args.putString(CURRENT_DATE, currentDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PreviousDapim_tab.OnFragmentInteractionListener) {
            mListener = (PreviousDapim_tab.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab3, container, false);
        ((App)getActivity().getApplication()).getmAppComponent().inject(this);

        dapimRV = v.findViewById(R.id.previousDapimRV);

        dapimRV.setLayoutManager(new LinearLayoutManager(getContext()));

        String currentDate = getArguments().getString(CURRENT_DATE);

        factory = new ViewModelFactory<>(dapimRepo);
        viewModel = ViewModelProviders.of(this, factory).get(DapimViewModel.class);

        viewModel.init(currentDate);
        viewModel.getData().observe(this, new Observer<WebResponse_daf>() {
            @Override
            public void onChanged(@Nullable WebResponse_daf webResponse_daf) {
                dapimRV.setAdapter(new DapimAdapter(getActivity(), webResponse_daf.getPast_pages()));
            }
        });


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
