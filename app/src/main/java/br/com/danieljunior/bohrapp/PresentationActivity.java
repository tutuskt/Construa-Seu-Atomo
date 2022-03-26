package br.com.danieljunior.bohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.danieljunior.bohrapp.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresentationActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button start;

    @BindView(R.id.tutoriais)
    Button tutoriais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        ButterKnife.bind(this);
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
