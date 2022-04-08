package br.com.danieljunior.bohrapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.danieljunior.bohrapp.controllers.ElementController;
import br.com.danieljunior.bohrapp.interfaces.BohrInterface;
import br.com.danieljunior.bohrapp.models.Element;
import br.com.danieljunior.bohrapp.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BorhFragment extends Fragment implements View.OnTouchListener, BohrInterface {

    @BindView(R.id.jj_conteudo)
    FrameLayout JJ_conteudo;

    @BindView(R.id.Btntabela)
    Button Btntabela;

    @BindView(R.id.btn_proton)
    Button protonBtn;

    @BindView(R.id.btn_neutron)
    Button neutronBtn;

    @BindView(R.id.btn_eletron)
    Button eletronBtn;

    @BindView(R.id.core)
    ImageView coreView;

    @BindView(R.id.eletrosphere)
    ImageView eletrosphereView;

    @BindView(R.id.chemist_greeting)
    TextView chemistGreeting;

    @BindView(R.id.protons)
    TextView protonsNumber;

    @BindView(R.id.eletrons)
    TextView eletronsNumber;

    @BindView(R.id.neutrons)
    TextView neutronsNumber;

    @BindView(R.id.mass)
    TextView A;

    @BindView(R.id.atomic)
    TextView Z;

    @BindView(R.id.charge)
    TextView charge;

    @BindView(R.id.symbol)
    TextView symbol;

    @BindView(R.id.element)
    TextView element;

    @BindView(R.id.eletronic_distribution)
    TextView eletronicDistribution;

    @BindView(R.id.eletronic_distribution_layout)
    LinearLayout eletronicDistributionLayout;

    @BindView(R.id.add)
    Button add;

    @BindView(R.id.remove)
    Button remove;

    @BindView(R.id.clear)
    Button clear;

    private ElementController elementController;
    private boolean addBtnPressed = true;
    private boolean removeBtnPressed = false;

    int[] coreViewCoordinates;
    int[] eletrosphereViewCoordinates;
    int X = 0;
    int Y = 1;

    public BorhFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borh, container, false);
        view.setOnTouchListener(this);
        ButterKnife.bind(this, view);
        elementController = new ElementController(this, getActivity());
        add.setPressed(true);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        coreViewCoordinates = new int[2];
        coreView.getLocationOnScreen(coreViewCoordinates);
        eletrosphereViewCoordinates = new int[2];
        eletrosphereView.getLocationOnScreen(eletrosphereViewCoordinates);

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            float x = motionEvent.getRawX();
            float y = motionEvent.getRawY();
            if (checkCoreClick(x, y)) {
                elementController.protonOrNeutronAction();
            } else if (checkEletrophereClick(x, y)) {
                elementController.eletronAction();
            }
        }
        return false;
    }

    private boolean checkCoreClick(float x, float y) {
        int[] coor = new int[2];
        coreView.getLocationOnScreen(coor);
        if (x > coor[0] && x < (coor[0] + coreView.getWidth())
                && y > (coor[1]) && y < (coor[1] + coreView.getHeight())) {
            return true;
        }
        return false;
    }

    private boolean checkEletrophereClick(float x, float y) {
        if (inEletrosphereBottomBound(x, y) || inEletrosphereTopBound(x, y) || inEletrosphereLeftSide(x, y) || inEletrosphereRightSide(x, y)) {
            return true;
        }
        return false;
    }

    @OnClick(R.id.Btntabela)
    public void HideBtnClick(View v){
        if(JJ_conteudo.getVisibility() == View.INVISIBLE ){
            JJ_conteudo.setVisibility(View.VISIBLE);
        }
        else{
            JJ_conteudo.setVisibility(View.INVISIBLE);
        }
    }


    @OnClick(R.id.btn_proton)
    public void protonBtnClick(View v) {
        elementController.setProtonSelected();
        Button btn = (Button) v;
        setDefaultButtonsColor();
        btn.setBackgroundColor(getResources().getColor(R.color.red));
    }

    @OnClick(R.id.btn_neutron)
    public void neutronBtnClick(View v) {
        elementController.setNeutronSelected();
        Button btn = (Button) v;
        setDefaultButtonsColor();
        btn.setBackgroundColor(getResources().getColor(R.color.green));
    }

    @OnClick(R.id.btn_eletron)
    public void eletronBtnClick(View v) {
        elementController.setEletronSelected();
        Button btn = (Button) v;
        setDefaultButtonsColor();
        btn.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    @OnClick(R.id.add)
    public void addBtnClick(View v) {
        if (addBtnPressed) {
            addBtnPressed = false;
            add.setBackgroundColor(getResources().getColor(R.color.btnDefault));
            elementController.setActionSelected(elementController.NONE_ACTION);
        } else {
            addBtnPressed = true;
            add.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            remove.setBackgroundColor(getResources().getColor(R.color.btnDefault));
            removeBtnPressed = false;
            elementController.setActionSelected(elementController.ADD_ACTION);
        }
    }

    @OnClick(R.id.remove)
    public void removeBtnClick(View v) {
        if (removeBtnPressed) {
            removeBtnPressed = false;
            remove.setBackgroundColor(getResources().getColor(R.color.btnDefault));
            elementController.setActionSelected(elementController.NONE_ACTION);
        } else {
            removeBtnPressed = true;
            remove.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            add.setBackgroundColor(getResources().getColor(R.color.btnDefault));
            addBtnPressed = false;
            elementController.setActionSelected(elementController.REMOVE_ACTION);
        }
    }

    @OnClick(R.id.clear)
    public void clearBtnClick(View v){
        clearData();
    }

    @OnClick(R.id.close)
    public void closeBtnClick(View v){
        getActivity().moveTaskToBack(true);
        getActivity().finish();
    }

    public void clearData(){
        elementController = new ElementController(this, getContext());
        chemistGreeting.setVisibility(View.INVISIBLE);
        eletronicDistributionLayout.setVisibility(View.INVISIBLE);
        coreView.setImageResource(android.R.color.transparent);
        setEletrosphereBitmap(BitmapFactory.decodeResource(getContext().getResources(), elementController.getFirstEletrosphere()));
        element.setText("ELEMENTO");
        symbol.setText("X");
        A.setText("A");
        Z.setText("Z");
        charge.setText("c");
        protonsNumber.setText("0");
        neutronsNumber.setText("0");
        eletronsNumber.setText("0");
        setDefaultButtonsColor();
    }

    public void setDefaultButtonsColor() {
        eletronBtn.setBackgroundColor(getResources().getColor(R.color.btnDefault));
        neutronBtn.setBackgroundColor(getResources().getColor(R.color.btnDefault));
        protonBtn.setBackgroundColor(getResources().getColor(R.color.btnDefault));
    }

    @Override
    public void showIsotopoMessage() {
        chemistGreeting.setText("Uau! Você achou um isótopo!");
        if (chemistGreeting.getVisibility() == View.INVISIBLE) {
            chemistGreeting.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showElementMessage() {
        chemistGreeting.setText("Uau! Você achou um elemento!");
        if (chemistGreeting.getVisibility() == View.INVISIBLE) {
            chemistGreeting.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCoreBitmap(Bitmap coreBitmap) {
        coreView.setImageBitmap(coreBitmap);
    }

    @Override
    public void setEletrosphereBitmap(Bitmap eletrosphereBitmap) {
        eletrosphereView.setImageBitmap(eletrosphereBitmap);
    }

    @Override
    public void setElementData(Element elementItem) {
        element.setText(elementItem.getNome());
        symbol.setText(elementItem.getSimbolo());
        A.setText(elementItem.getA() + "");
        Z.setText(elementItem.getZ() + "");
        charge.setText(elementItem.getCharge());
        protonsNumber.setText(elementItem.getProton() + "");
        neutronsNumber.setText(elementItem.getNeutron() + "");
        eletronsNumber.setText(elementItem.getEletron() + "");
    }

    @Override
    public void showCommonMessage(String title, String message) {
        DialogFactory.showInfoMessage(title, message, getContext());
    }

    @Override
    public void setEletronicDistribution(String distribution) {
        if (!distribution.equals("")) {
            eletronicDistributionLayout.setVisibility(View.VISIBLE);
            eletronicDistribution.setText(distribution);
        } else {
            eletronicDistributionLayout.setVisibility(View.INVISIBLE);
            eletronicDistribution.setText("");
        }
    }

    public boolean inEletrosphereTopBound(float x, float y) {
        boolean inside = x > eletrosphereViewCoordinates[X] &&
                x < eletrosphereViewCoordinates[X] + eletrosphereView.getWidth() &&
                y > eletrosphereViewCoordinates[Y] &&
                y < coreViewCoordinates[Y];
        return inside;
    }

    public boolean inEletrosphereBottomBound(float x, float y) {
        boolean inside = x > eletrosphereViewCoordinates[X] &&
                x < eletrosphereViewCoordinates[X] + eletrosphereView.getWidth() &&
                y > coreViewCoordinates[Y] + coreView.getHeight() &&
                y < eletrosphereViewCoordinates[Y] + eletrosphereView.getHeight();
        return inside;
    }

    public boolean inEletrosphereRightSide(float x, float y) {
        boolean inside = x > coreViewCoordinates[X] &&
                x < eletrosphereViewCoordinates[X] + eletrosphereView.getWidth() &&
                y > eletrosphereViewCoordinates[Y] &&
                y < eletrosphereViewCoordinates[Y] + eletrosphereView.getHeight();
        return inside;
    }

    public boolean inEletrosphereLeftSide(float x, float y) {
        boolean inside = x > eletrosphereViewCoordinates[X] &&
                x < coreViewCoordinates[X] &&
                y > eletrosphereViewCoordinates[Y] &&
                y < eletrosphereViewCoordinates[Y] + eletrosphereView.getHeight();
        return inside;
    }

}
