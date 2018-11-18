package com.hudiilfeld.shiurdiario.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.MesechtotAdapter.MesechtaViewHolder;
import com.hudiilfeld.shiurdiario.models.Masechta;

import java.util.List;

public class MesechtotAdapter extends Adapter<MesechtaViewHolder>{

    Context context;
    List<Masechta> data;
    LayoutInflater inflater;

    public MesechtotAdapter(Context context, List<Masechta> data) {
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

        public MesechtaViewHolder(View itemView) {
            super(itemView);
            masechetTV = itemView.findViewById(R.id.masechetTV);
        }
    }
}
