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
import android.widget.Toast;

import com.hudiilfeld.shiurdiario.App;
import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.MesechtotAdapter;
import com.hudiilfeld.shiurdiario.models.Masechta;
import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;
import com.hudiilfeld.shiurdiario.models.WebService;
import com.hudiilfeld.shiurdiario.repositories.MasechtotRepo;
import com.hudiilfeld.shiurdiario.view_models.MasechtotViewModel;
import com.hudiilfeld.shiurdiario.view_models.viewModelProvides.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;


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
        View v = inflater.inflate(R.layout.fragment_tab4, container, false);
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
                masechetotRV.setAdapter(new MesechtotAdapter(getContext(), webResponse_masechet.getD().getMasechtot()));
            }
        });

//        Retrofit.Builder builder = new Builder()
//                .baseUrl("http://ws.shiurdiario.com")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//
//        WebService client = retrofit.create(WebService.class);
//
//        String currentDate = getArguments().getString(CURRENT_DATE);
//
//        Call<WebResponse_masechet> call = client.getMasechtot(currentDate);
//
//        call.enqueue(new Callback<WebResponse_masechet>() {
//            @Override
//            public void onResponse(Call<WebResponse_masechet> call, Response<WebResponse_masechet> response) {
//                WebResponse_masechet jsonData = response.body();
//                data = jsonData.getD().getMasechtot();
//                if (getContext() != null)
//                    masechetotRV.setAdapter(new MesechtotAdapter(getContext(), data));
//            }
//
//            @Override
//            public void onFailure(Call<WebResponse_masechet> call, Throwable t) {
//                Log.d("FAILURE IS: ", t.getMessage());
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


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
