package com.nizetic.yuumi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private List<String> logLista;

    public LogAdapter(List<String> logLista) {
        this.logLista = logLista;
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_item, parent, false);
        return new LogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        String red = logLista.get(position);
        holder.txtLog.setText(red);
    }

    @Override
    public int getItemCount() {
        return logLista.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder {
        public TextView txtLog;

        public LogViewHolder(View itemView) {
            super(itemView);
            txtLog = itemView.findViewById(R.id.txtLog);
        }
    }
}
