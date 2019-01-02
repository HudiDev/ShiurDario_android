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
import com.hudiilfeld.shiurdiario.adapters.MesechtotAdapter;
import com.hudiilfeld.shiurdiario.models.Masechta;
import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.repositories.MasechtotRepo;
import com.hudiilfeld.shiurdiario.view_models.MasechtotViewModel;
import com.hudiilfeld.shiurdiario.view_models.viewModelProvides.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;


public class AllMasechtot_tab extends Fragment {

    RecyclerView masechetotRV;
    List<Masechta> data;
    @Inject MasechtotRepo repository;
    @Inject ViewModelFactory<MasechtotRepo> factory;
    MasechtotViewModel viewModel;


    private OnFragmentInteractionListener mListener;
    public static final String CURRENT_DATE = "currentDate";


    public static AllMasechtot_tab newInstance(String currentDate) {
        AllMasechtot_tab fragment = new AllMasechtot_tab();
        Bundle args = new Bundle();
        args.putString(CURRENT_DATE, currentDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_masechtot, container, false);
        ((App)getActivity().getApplication()).getmAppComponent().inject(this);

        String currentDate = getArguments().getString(CURRENT_DATE);

        masechetotRV = v.findViewById(R.id.masechetotRV);
        masechetotRV.setLayoutManager(new LinearLayoutManager(getContext()));

        factory = new ViewModelFactory<>(repository);
        viewModel = ViewModelProviders.of(this, factory).get(MasechtotViewModel.class);

        viewModel.init(currentDate);
        viewModel.getliveData().observe(this, new Observer<WebResponse_masechet>() {
            @Override
            public void onChanged(@Nullable WebResponse_masechet webResponse_masechet) {
                masechetotRV.setAdapter(new MesechtotAdapter(getActivity(), webResponse_masechet.getD().getMasechtot()));
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
