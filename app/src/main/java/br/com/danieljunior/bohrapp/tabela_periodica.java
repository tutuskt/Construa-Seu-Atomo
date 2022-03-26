package br.com.danieljunior.bohrapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;


/**
 * Created by Arthur on 31/05/2018.
 */


public class tabela_periodica extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
    }

    public boolean onOptionsItemSelected(MenuItem item) {     //Função que faz voltar para a pagina anterior
        Intent myIntent = new Intent(getApplicationContext(), BohrActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
