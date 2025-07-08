package com.nizetic.yuumi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ZdjelaAdapter extends RecyclerView.Adapter<ZdjelaAdapter.ViewHolder> {

    List<Zdjela> zdjele;

    public ZdjelaAdapter(){
        zdjele = new ArrayList<>();
    }

    @NonNull
    @Override
    public ZdjelaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_zdjela, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ZdjelaAdapter.ViewHolder holder, int position) {
        Zdjela z = zdjele.get(position);
        holder.txtVaga.setText(String.valueOf(z.getStanje()));
    }

    @Override
    public int getItemCount() {
        return zdjele.size();
    }

    public void replaceAll(List<Zdjela> novaZdjela) {
        zdjele.clear();
        zdjele.addAll(novaZdjela);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVaga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVaga = itemView.findViewById(R.id.txtVaga);
        }
    }
}
