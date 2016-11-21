package com.example.robertoveigajunior.carangole.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.robertoveigajunior.carangole.R;
import com.example.robertoveigajunior.carangole.listener.OnClickListener;
import com.example.robertoveigajunior.carangole.model.Carro;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by robertoveigajunior on 19/11/16.
 */

public class CarroListAdapter extends RecyclerView.Adapter<CarroListAdapter.CarrosViewHolder> {

    private final Context context;
    private final List<Carro> carros;

    private OnClickListener clickListener;

    public CarroListAdapter(Context context, List<Carro> carros, OnClickListener clickListener) {
        this.context = context;
        this.carros = carros;
        this.clickListener = clickListener;
    }

    @Override
    public CarrosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.carro_row, parent, false);
        return new CarrosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CarrosViewHolder holder, final int position) {
        Carro c = carros.get(position);
        holder.tvNome.setText(c.getNome());
        holder.tvDescricao.setText(c.getDescricao());
        //get image data and show
        Picasso.with(context)
                .load(c.getFoto())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivCarro);

        if (clickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(holder.itemView, position);

                }
            });
        }
    }

    public Carro getItem(int position) {
        return carros.get(position);
    }

    @Override
    public int getItemCount() {
        return this.carros != null ? this.carros.size() : 0;
    }

    public static class CarrosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvDescricao;
        ImageView ivCarro;

        public CarrosViewHolder(View itemView) {
            super(itemView);
            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvDescricao = (TextView) itemView.findViewById(R.id.tvDescricao);
            ivCarro = (ImageView) itemView.findViewById(R.id.ivCarro);
        }
    }
}
