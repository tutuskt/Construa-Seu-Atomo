package br.com.danieljunior.bohrapp;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import br.com.danieljunior.bohrapp.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresentationActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button start;

    @BindView(R.id.tutoriais)
    Button tutoriais;

    private ImageView imdedo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        ButterKnife.bind(this);

        imdedo = (ImageView)findViewById(R.id.imdedo);
        imdedo.setBackgroundResource(R.drawable.dedo);

        ObjectAnimator vai = ObjectAnimator.ofFloat(imdedo, "translationX",35f);
        vai.setDuration(1000);
        vai.setRepeatMode(ValueAnimator.REVERSE);
        vai.setRepeatCount(Animation.INFINITE);

        vai.start();

    }


    @OnClick(R.id.start)
    public void startBtnClick(View v){
        Intent intent = new Intent(this, BohrActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.tutoriais)
    public void tutorialBtnClick(View view){
        Intent it = new Intent(this, tutorial.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }

}
