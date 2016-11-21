package com.example.robertoveigajunior.carangole;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robertoveigajunior.carangole.model.Carro;
import com.squareup.picasso.Picasso;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imagem;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        imagem = (ImageView) findViewById(R.id.imagemCarro);
        desc = (TextView)findViewById(R.id.desc);

        if(getIntent() != null) {
            Carro carro = getIntent().getParcelableExtra("carro");
            Picasso.with(this).load(carro.getFoto()).placeholder(R.mipmap.ic_launcher).into(imagem);
            desc.setText(carro.getDescricao());
        }
    }
}
