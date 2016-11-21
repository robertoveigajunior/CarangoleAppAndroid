package com.example.robertoveigajunior.carangole;

import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robertoveigajunior.carangole.model.Carro;

import java.io.IOException;
import java.io.InputStream;

import static com.example.robertoveigajunior.carangole.R.id.imageView;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imagem;
    private TextView desc;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        imagem = (ImageView) findViewById(R.id.imagem);
        desc = (TextView)findViewById(R.id.desc);

        if(getIntent() != null) {
            try {
                Carro carro = getIntent().getParcelableExtra("carro");
                InputStream ims = getAssets().open(carro.getFoto());
                Drawable d = Drawable.createFromStream(ims, null);
                imagem.setImageDrawable(d);
                collapsingToolbar.setTitle(carro.getNome());
                desc.setText(carro.getDescricao());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }
}
