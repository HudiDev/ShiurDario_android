package com.hudiilfeld.shiurdiario.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DafViewHolder;
import com.hudiilfeld.shiurdiario.models.DafGemara;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.MainActivity;

import java.util.List;

import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class DapimAdapter extends Adapter<DafViewHolder> {

    public static final String DAF_DATE = "dafDate";


    private List<DafGemara> data;
    private LayoutInflater inflater;
    Context mContext;

    public DapimAdapter(Context context, List<DafGemara> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public DafViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.daf_view, parent, false);
        return new DafViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DafViewHolder holder, int position) {
        DafGemara daf = data.get(position);
        holder.dafNameTV.setText(daf.getMasechet() + " " + daf.getDaf());
        holder.durationTV.setText("Duration: " + daf.getDuration());
        holder.dateTV.setText(daf.getDate());
        holder.hebMonthDayTV.setText(daf.getHebmonth() + " " + daf.getHebdate());
        holder.hebYearTV.setText(daf.getHebyear());

        holder.passPrefix(daf.getPrefix(), daf.getSqldate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DafViewHolder extends RecyclerView.ViewHolder{

        CardView parentView;
        TextView dafNameTV, durationTV, dateTV, hebMonthDayTV, hebYearTV;

        public DafViewHolder(View itemView) {
            super(itemView);

            dafNameTV = itemView.findViewById(R.id.dafNameTV);
            durationTV = itemView.findViewById(R.id.durationTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            hebMonthDayTV = itemView.findViewById(R.id.hebMonthDayTV);
            hebYearTV = itemView.findViewById(R.id.hebYearTV);
            parentView = itemView.findViewById(R.id.parentView);
        }

        public void passPrefix(final String prefix, final String dafDate) {
            parentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra(PREFIX, prefix);
                    intent.putExtra(DAF_DATE, dafDate);
                    mContext.startActivity(intent);
                }
            });
        }

    }
}
