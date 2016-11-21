package com.example.robertoveigajunior.carangole.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.robertoveigajunior.carangole.DetalheActivity;
import com.example.robertoveigajunior.carangole.R;
import com.example.robertoveigajunior.carangole.adapter.CarroListAdapter;
import com.example.robertoveigajunior.carangole.api.CarroAPI;
import com.example.robertoveigajunior.carangole.listener.OnClickListener;
import com.example.robertoveigajunior.carangole.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.robertoveigajunior.carangole.R.id.rvCarros;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends Fragment implements Callback<List<Carro>> {

    private RecyclerView rvCarros;
    private String tipo;
    private CarroListAdapter adapter;

    public CarrosFragment() {

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if(getArguments() != null) {
            tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_carros, container, false);
        rvCarros = (RecyclerView)v.findViewById(R.id.rvCarros);
        rvCarros.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCarros.setItemAnimator(new DefaultItemAnimator());
        rvCarros.setHasFixedSize(true);
        return v;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        loadCarros();
    }

    private void loadCarros() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.heiderlopes.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarroAPI api = retrofit.create(CarroAPI.class);
        Call<List<Carro>> call = api.findBy(tipo);
        call.enqueue(this);


    }

    private OnClickListener onClickListener() {
        return new OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Carro c = adapter.getItem(position);
                Intent i = new Intent(getContext(), DetalheActivity.class);
                i.putExtra("carro", c);
                startActivity(i);
            }
        };
    }

    @Override
    public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
        adapter = new CarroListAdapter(getContext(), response.body(), onClickListener());
        rvCarros.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<Carro>> call, Throwable t) {
        Toast.makeText(getContext(),
                t.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
