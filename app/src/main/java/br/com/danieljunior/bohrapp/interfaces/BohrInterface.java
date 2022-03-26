package br.com.danieljunior.bohrapp.interfaces;

import android.graphics.Bitmap;

import br.com.danieljunior.bohrapp.models.Element;

/**
 * Created by danieljr on 09/02/17.
 */

public interface BohrInterface {

    public void showIsotopoMessage();
    public void showElementMessage();
    public void setCoreBitmap(Bitmap coreBitmap);
    public void setEletrosphereBitmap(Bitmap eletrosphereBitmap);
    public void setElementData(Element element);
    public void showCommonMessage(String title, String message);
    public void setEletronicDistribution(String distribution);
    public void clearData();
}
