package com.hudiilfeld.shiurdiario.adapters;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.Utils;
import com.hudiilfeld.shiurdiario.adapters.MesechtotAdapter.MesechtaViewHolder;
import com.hudiilfeld.shiurdiario.models.Masechta;
import com.hudiilfeld.shiurdiario.views.HomeActivity;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_tab;

import java.util.List;

public class MesechtotAdapter extends Adapter<MesechtaViewHolder>{

    private FragmentActivity context;
    private List<Masechta> data;
    private LayoutInflater inflater;

    public MesechtotAdapter(FragmentActivity context, List<Masechta> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MesechtaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.masechet_view, parent, false);
        return new MesechtaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MesechtaViewHolder holder, int position) {
        Masechta masechta = data.get(position);
        holder.masechetTV.setText(masechta.getMasechet());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MesechtaViewHolder extends RecyclerView.ViewHolder {

        TextView masechetTV;

        public MesechtaViewHolder(final View itemView) {
            super(itemView);
            masechetTV = itemView.findViewById(R.id.masechetTV);



            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id;

                    if (context instanceof HomeActivity) {
                        Log.d("TAG", "coming from home activity");
                        id = R.id.fragmentContainer;
                    } else {
                        Log.d("TAG", "coming from daf-hayomi activity");
                        id = R.id.mainContainer;
                    }
                    Log.d("wedfwa", "fvsfsfvsfvs");
                    context.getSupportFragmentManager().beginTransaction()
                            .add(id,
                                    Dapim_tab.newInstance(Utils.getCurrentDate(),
                                            data.get(getAdapterPosition())
                                                    .getMasechet()))
                            .addToBackStack(null).commit();
                }
            });
        }
    }
}
