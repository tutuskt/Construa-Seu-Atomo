package br.com.danieljunior.bohrapp.models;

/**
 * Created by danieljr on 15/02/17.
 */

public class Layer implements Cloneable {
    private int numberOfLayer;
    private char subLayer;
    private int maxEletrons;
    private int currentEletrons = 0;

    public Layer(int numberOfLayer, char subLayer, int maxEletrons) {
        this.numberOfLayer = numberOfLayer;
        this.subLayer = subLayer;
        this.maxEletrons = maxEletrons;
    }

    public int getNumberOfLayer() {
        return numberOfLayer;
    }

    public void setNumberOfLayer(int numberOfLayer) {
        this.numberOfLayer = numberOfLayer;
    }

    public char getSubLayer() {
        return subLayer;
    }

    public void setSubLayer(char subLayer) {
        this.subLayer = subLayer;
    }

    public int getMaxEletrons() {
        return maxEletrons;
    }

    public void setMaxEletrons(int maxEletrons) {
        this.maxEletrons = maxEletrons;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean increaseEletrons() {
        if (currentEletrons + 1 <= maxEletrons) {
            currentEletrons++;
            return true;
        }
        return false;
    }

    public boolean decreaseEletrons() {
        if (currentEletrons - 1 >= 0) {
            currentEletrons--;
            return true;
        }
        return false;
    }

    public int getCurrentEletrons() {
        return currentEletrons;
    }

    public void setCurrentEletrons(int currentEletrons) {
        this.currentEletrons = currentEletrons;
    }
}
