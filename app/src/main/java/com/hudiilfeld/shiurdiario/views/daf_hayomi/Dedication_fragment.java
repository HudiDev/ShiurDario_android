package com.hudiilfeld.shiurdiario.views.daf_hayomi;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hudiilfeld.shiurdiario.App;
import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.Utils;
import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.repositories.DedicationRepo;
import com.hudiilfeld.shiurdiario.view_models.DedicationViewModel;
import com.hudiilfeld.shiurdiario.view_models.viewModelProvides.ViewModelFactory;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dedication_fragment extends Fragment {

    TextView dedicationTV;

    @Inject ViewModelFactory<DedicationRepo> factory;
    @Inject DedicationRepo repository;
    DedicationViewModel viewModel;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dedication_tab, container, false);
        ((App)getActivity().getApplication()).getmAppComponent().inject(this);


        dedicationTV = v.findViewById(R.id.dedicationTV);
        progressBar = v.findViewById(R.id.dedicationProgressBar);

        factory = new ViewModelFactory<>(repository);
        viewModel = ViewModelProviders.of(this, factory).get(DedicationViewModel.class);

        viewModel.init(Utils.getCurrentDate());

        viewModel.getLiveData().observe(this, new Observer<WebResponse_masechet>() {
            @Override
            public void onChanged(@Nullable WebResponse_masechet webResponse_masechet) {

                String name = webResponse_masechet.getD().getDedication().split(":")[1];

                progressBar.setVisibility(View.GONE);
                dedicationTV.setVisibility(View.VISIBLE);
                dedicationTV.setText(name);
            }
        });

        return v;
    }

}
