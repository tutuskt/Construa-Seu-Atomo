package br.com.danieljunior.bohrapp.models;

/**
 * Created by danieljr on 09/02/17.
 */

public class Particle {

    public static int PROTON = 1;
    public static int NEUTRON = 2;
    public static int ELETRON = 3;

    int x, y, type;

    public Particle(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
