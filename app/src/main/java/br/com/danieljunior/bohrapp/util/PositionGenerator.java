package br.com.danieljunior.bohrapp.util;

/**
 * Created by danieljr on 15/02/17.
 */

public class PositionGenerator {

    int R = 32;
    int xx = 0;
    int yy = 0;
    int xini = 512;
    int yini = 512;

    int J = 0;
    double dist = 0;
    double rotacao = 0;

    int w = 0;
    int S = 1;
    double m = 1.0;

    boolean takenPosition[] = new boolean[1];

    public int[] positionGenerate() {
        if (J == S) {
            w++;
        }
        dist = m * 2 * R * w;
        S = (int) (((w * w + w) / 2) * 6) + 1;
        rotacao = (2 * Math.PI) * (J - S) / (6 * w);

        yy = (int) ((dist) * Math.sin(rotacao));
        xx = (int) ((dist) * Math.cos(rotacao));
        J++;
        int resp[] = new int[2];
        resp[0] = xx + xini;
        resp[1] = yy + yini;
        return resp;
    }

    public int[] randomPositionGenerate() {
        if (J == S) {
            w++;
            S = (int) (((w * w + w) / 2) * 6) + 1;
            takenPosition = new boolean[S-J];
        }
        dist = m * 2 * R * w;

        int randomNumber = 0;
        do {
            randomNumber = (int) (Math.random() * takenPosition.length);
        } while (takenPosition[randomNumber]);
        takenPosition[randomNumber] = true;
        rotacao = (2 * Math.PI) * (randomNumber) / (6 * w);

        yy = (int) ((dist) * Math.sin(rotacao));
        xx = (int) ((dist) * Math.cos(rotacao));
        J++;
        int resp[] = new int[2];
        resp[0] = xx + xini;
        resp[1] = yy + yini;
        return resp;
    }
}
