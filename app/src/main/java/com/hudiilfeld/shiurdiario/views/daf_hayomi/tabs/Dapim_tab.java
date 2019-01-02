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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hudiilfeld.shiurdiario.App;
import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.DapimAdapter;
import com.hudiilfeld.shiurdiario.models.Daf;
import com.hudiilfeld.shiurdiario.models.WebResponse_previousDaf;
import com.hudiilfeld.shiurdiario.models.WebResponse_shiurDaf;
import com.hudiilfeld.shiurdiario.repositories.DapimRepo;
import com.hudiilfeld.shiurdiario.view_models.DapimViewModel;
import com.hudiilfeld.shiurdiario.view_models.viewModelProvides.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dapim_tab extends Fragment {

    RecyclerView dapimRV;
    List<Daf> data;
    @Inject ViewModelFactory<DapimRepo> factory;
    @Inject DapimRepo dapimRepo;
    DapimViewModel viewModel;



    private Dapim_tab.OnFragmentInteractionListener mListener;

    public static final String CURRENT_DATE = "currentDate";
    public static final String MASECHET = "masechet";

    public static Dapim_tab newInstance(String currentDate,
                                        @Nullable String masechet) {
        Dapim_tab fragment = new Dapim_tab();
        Bundle args = new Bundle();
        args.putString(CURRENT_DATE, currentDate);
        args.putString(MASECHET, masechet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Dapim_tab.OnFragmentInteractionListener) {
            mListener = (Dapim_tab.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dapim, container, false);
        ((App)getActivity().getApplication()).getmAppComponent().inject(this);

        dapimRV = v.findViewById(R.id.previousDapimRV);

        dapimRV.setLayoutManager(new LinearLayoutManager(getContext()));

        String currentDate = getArguments().getString(CURRENT_DATE);

        factory = new ViewModelFactory<>(dapimRepo);
        viewModel = ViewModelProviders.of(this, factory).get(DapimViewModel.class);

        final DapimAdapter adapter = new DapimAdapter(getActivity(), new ArrayList<Daf>());
        dapimRV.setAdapter(adapter);


        if (getArguments().getString(MASECHET) == null) {

            viewModel.initPreviousDapim(currentDate);
            viewModel.getPreviousDapim().observe(this, new Observer<WebResponse_previousDaf>() {
                @Override
                public void onChanged(@Nullable WebResponse_previousDaf webResponse_previousDaf) {
                    adapter.setData(webResponse_previousDaf.getPast_pages());
                }
            });

        } else {

            viewModel.initShiurimDapim(getArguments().getString(MASECHET), 0);
            viewModel.getShiurDapim().observe(this, new Observer<WebResponse_shiurDaf>() {
                @Override
                public void onChanged(@Nullable WebResponse_shiurDaf webResponse_shiurDaf) {
                    adapter.setData(webResponse_shiurDaf.getDapim());
                    //dapimRV.setAdapter(new DapimAdapter(getActivity(), webResponse_shiurDaf.getDapim()));
                }
            });
        }

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
