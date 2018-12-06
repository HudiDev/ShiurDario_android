package com.hudiilfeld.shiurdiario.views.daf_hayomi;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.DapimAdapter;
import com.hudiilfeld.shiurdiario.models.Daf;
import com.hudiilfeld.shiurdiario.models.WebResponse_daf;
import com.hudiilfeld.shiurdiario.view_models.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousDapim_tab extends Fragment {

    RecyclerView previousDapimRV;
    List<Daf> data;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab3, container, false);

        previousDapimRV = v.findViewById(R.id.previousDapimRV);

        previousDapimRV.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit.Builder builder = new Builder()
                .baseUrl("http://ws.shiurdiario.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WebService client = retrofit.create(WebService.class);

        String currentDate = getArguments().getString(CURRENT_DATE);

        Call<WebResponse_daf> call = client.getDapim(currentDate);

        call.enqueue(new Callback<WebResponse_daf>() {
            @Override
            public void onResponse(Call<WebResponse_daf> call, Response<WebResponse_daf> response) {
                WebResponse_daf jsonData = response.body();
                data = jsonData.getPast_pages();
                Log.d("jsonDataSize:", "" + data.size());
                if (getActivity() != null) {
                    previousDapimRV.setAdapter(new DapimAdapter(getActivity(), data));
                }
            }

            @Override
            public void onFailure(Call<WebResponse_daf> call, Throwable t) {
                Toast.makeText(getContext(), "error :( Something went wrong.", Toast.LENGTH_SHORT).show();
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
