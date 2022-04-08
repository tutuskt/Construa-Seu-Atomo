package br.com.danieljunior.bohrapp;

/**
 * Created by Arthur on 05/03/2018.
 */

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;


import br.com.danieljunior.bohrapp.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class tutorial extends AppCompatActivity {

    @BindView(R.id.start_tutorial)
    Button start_tutorial;

    @BindView(R.id.tutorial_video)
    Button tutorial_video;

    private ImageView imdedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão de Menu
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão de Menu

        imdedo = (ImageView)findViewById(R.id.imdedo);
        imdedo.setBackgroundResource(R.drawable.dedo);

        ObjectAnimator vai = ObjectAnimator.ofFloat(imdedo, "translationX",35f);
        vai.setDuration(1000);
        vai.setRepeatMode(ValueAnimator.REVERSE);
        vai.setRepeatCount(Animation.INFINITE);

        vai.start();
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

    @OnClick(R.id.tutorial_video)
    public void tutorialVideoBtnClick(View v){
        //DialogFactory.showInfoMessage("Informação", "Funcionalidade em desenvolvimento...", this);
        Intent j = new Intent(this, VideoActivity.class);
        j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(j);
    }

}
