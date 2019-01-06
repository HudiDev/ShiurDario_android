package com.hudiilfeld.shiurdiario.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DafViewHolder;
import com.hudiilfeld.shiurdiario.models.Daf;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.DafHayomiActivity;

import java.util.List;

import static com.hudiilfeld.shiurdiario.views.LaunchActivity.DAF;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.MASECHET;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class DapimAdapter extends RecyclerView.Adapter {

    public static final String DAF_DATE = "dafDate";

    private final int VIEW_ITEM = 1;
    private final int VIEW_LOADER = 2;


    private List<Daf> mData;
    private LayoutInflater inflater;
    FragmentActivity mContext;

    public DapimAdapter(FragmentActivity context, List<Daf> data) {
        mData = data;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setData(List<Daf> data) {
        mData.remove(null);
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addProgressBar() {
        mData.add(null);
        notifyItemInserted(mData.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) != null ? VIEW_ITEM : VIEW_LOADER;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_ITEM) {
            View v = inflater.inflate(R.layout.daf_view, parent, false);
            return new DafViewHolder(v);
        } else {
            View v = inflater.inflate(R.layout.loader_view, parent, false);
            return new LoaderViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof DafViewHolder) {
            DafViewHolder dafVH = (DafViewHolder) holder;
            Daf daf = mData.get(position);
            dafVH.dafNameTV.setText(daf.getMasechet() + " " + daf.getDaf());
            dafVH.durationTV.setText("Duration: " + daf.getDuration());

            String date = daf.getDate() != null ? daf.getDate() : daf.getDafdate();
            dafVH.dateTV.setText(date);

            if (daf.getHebmonth() != null) {
                dafVH.hebMonthDayTV.setText(daf.getHebmonth() + " " + daf.getHebdate());
                dafVH.hebYearTV.setText(daf.getHebyear());
            } else {
                dafVH.hebMonthDayTV.setText("");
                dafVH.hebYearTV.setText("");
            }
            dafVH.passPrefix(daf.getPrefix(), daf.getSqldate(), daf.getMasechet(), daf.getDaf());
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class DafViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentView;
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

        public void passPrefix(final String prefix, final String dafDate, final String masechet, final String daf) {
            parentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DafHayomiActivity.class);
                    intent.putExtra(PREFIX, prefix);
                    intent.putExtra(DAF_DATE, dafDate);
                    intent.putExtra(MASECHET, masechet);
                    intent.putExtra(DAF, daf);
                    mContext.startActivity(intent);
                }
            });
        }

    }

    class LoaderViewHolder extends RecyclerView.ViewHolder {

        public LoaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
