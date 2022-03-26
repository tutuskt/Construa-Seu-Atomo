package br.com.danieljunior.bohrapp;

/**
 * Created by Arthur on 05/03/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import br.com.danieljunior.bohrapp.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class tutorial extends AppCompatActivity {

    @BindView(R.id.start_tutorial)
    Button start_tutorial;

    //@BindView(R.id.tutorial_video)
    //Button tutorial_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
    }

    public boolean onOptionsItemSelected(MenuItem item){     //Função que faz voltar para a pagina anterior
        Intent myIntent = new Intent(getApplicationContext(), PresentationActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    @OnClick(R.id.start_tutorial)
    public void startBtnClick(View v){
        Intent that = new Intent(this, BohrActivity.class);
        that.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(that);
    }

    //@OnClick(R.id.tutorial_video)
    //public void startBtnClick(View v){
      //  Intent j = new Intent(this, BohrActivity.class);
        //j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(j);
    //}

    @OnClick(R.id.tutorial_video)
    public void videoBtnClick(View v){
    DialogFactory.showInfoMessage("Informação", "Funcionalidade em desenvolvimento...", this);
    }


}
