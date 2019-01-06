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
import android.support.v7.widget.RecyclerView.OnScrollListener;
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

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dapim_fragment extends Fragment {

    private RecyclerView dapimRV;
    @Inject ViewModelFactory<DapimRepo> factory;
    @Inject DapimRepo dapimRepo;
    private DapimViewModel viewModel;
    private LinearLayoutManager mLinearLayoutManager;
    private int maxNumOfPages;
    private int numOfSkips = 1;
    private boolean loading = false;

    private Dapim_fragment.OnFragmentInteractionListener mListener;

    public static final String CURRENT_DATE = "currentDate";
    public static final String MASECHET = "masechet";



    public static Dapim_fragment newInstance(String currentDate,
                                             @Nullable String masechet) {
        Dapim_fragment fragment = new Dapim_fragment();
        Bundle args = new Bundle();
        args.putString(CURRENT_DATE, currentDate);
        args.putString(MASECHET, masechet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Dapim_fragment.OnFragmentInteractionListener) {
            mListener = (Dapim_fragment.OnFragmentInteractionListener) context;
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

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        dapimRV.setLayoutManager(mLinearLayoutManager);

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

            viewModel.initShiurDapim(getArguments().getString(MASECHET));
            viewModel.getShiurDapim().observe(this, new Observer<WebResponse_shiurDaf>() {
                @Override
                public void onChanged(@Nullable WebResponse_shiurDaf webResponse_shiurDaf) {
                    maxNumOfPages = webResponse_shiurDaf.getPages().size();
                    adapter.setData(webResponse_shiurDaf.getDapim());
                    loading = true;
                    //dapimRV.setAdapter(new DapimAdapter(getActivity(), webResponse_shiurDaf.getDapim()));
                }
            });
        }


        dapimRV.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (getArguments().getString(MASECHET) == null || maxNumOfPages == 0)
                    return;

                if (dy > 0) {
                    if (!loading) { return; }
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                    int visibleThreshold = 3;

                    if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        loading = false;
                        if (numOfSkips >= maxNumOfPages)
                            return;

                        numOfSkips++;
                        adapter.addProgressBar();
                        viewModel.loadNewData(getArguments().getString(MASECHET), numOfSkips);
                    }
                }
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
