package br.com.danieljunior.bohrapp.models;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by danieljr on 15/02/17.
 */

public class Distribution {
    private final Queue<Layer> layers;
    private final Queue<Layer> actualLayers;
    private int actualEletrons = 0;

    public Distribution() {
        layers = new ArrayDeque<>();
        actualLayers = new ArrayDeque<>();
        fillLayers();
    }

    private void fillLayers() {
        layers.add(new Layer(1, 's', 2));
        layers.add(new Layer(2, 's', 2));
        layers.add(new Layer(2, 'p', 6));
        layers.add(new Layer(3, 's', 2));
        layers.add(new Layer(3, 'p', 6));
        layers.add(new Layer(4, 's', 2));
        layers.add(new Layer(3, 'd', 10));
        layers.add(new Layer(4, 'p', 6));
        layers.add(new Layer(5, 's', 2));
        layers.add(new Layer(4, 'd', 10));
        layers.add(new Layer(5, 'p', 6));
        layers.add(new Layer(6, 's', 2));
        layers.add(new Layer(4, 'f', 14));
        layers.add(new Layer(5, 'd', 10));
        layers.add(new Layer(6, 'p', 6));
        layers.add(new Layer(7, 's', 2));
        layers.add(new Layer(5, 'f', 14));
        layers.add(new Layer(6, 'd', 10));
        layers.add(new Layer(7, 'p', 6));
    }

    public String getEletronicDistribution(int numberOfEletrons) {
        if (numberOfEletrons == 0) {
            return "";
        }
        String actualDistribution = "";
        actualEletrons = numberOfEletrons;
        for (Layer layer : layers) {
            if (!actualLayers.contains(layer)) {
                actualLayers.add((Layer) layer.clone());
                actualDistribution += layer.getNumberOfLayer();
                actualDistribution += layer.getSubLayer();
                String eletrons;
                if (numberOfEletrons > layer.getMaxEletrons()) {
                    eletrons = layer.getMaxEletrons() + "";
                } else {
                    eletrons = numberOfEletrons + "";
                }
                for (char c : eletrons.toCharArray()) {
                    actualDistribution += charToSuperscript(c + "");
                }
                numberOfEletrons -= layer.getMaxEletrons();
                if (numberOfEletrons <= 0) {
                    break;
                }
            }
        }
        return actualDistribution;
    }

    private String buildDistributionString() {
        String actualDistribution = "";
        int numberOfEletrons = actualEletrons;
        for (Layer layer : actualLayers) {
            actualDistribution += layer.getNumberOfLayer();
            actualDistribution += layer.getSubLayer();
            String eletrons;
            if (numberOfEletrons > layer.getMaxEletrons()) {
                eletrons = layer.getMaxEletrons() + "";
            } else {
                eletrons = numberOfEletrons + "";
            }
            for (char c : eletrons.toCharArray()) {
                actualDistribution += charToSuperscript(c + "");
            }
            numberOfEletrons -= layer.getMaxEletrons();
            if (numberOfEletrons <= 0) {
                break;
            }
        }
        return actualDistribution;
    }

    private String charToSuperscript(String str) {
        str = str.replaceAll("0", "⁰");
        str = str.replaceAll("1", "¹");
        str = str.replaceAll("2", "²");
        str = str.replaceAll("3", "³");
        str = str.replaceAll("4", "⁴");
        str = str.replaceAll("5", "⁵");
        str = str.replaceAll("6", "⁶");
        str = str.replaceAll("7", "⁷");
        str = str.replaceAll("8", "⁸");
        str = str.replaceAll("9", "⁹");
        return str;
    }

    public String removeEletron() {
        actualEletrons -= 1;
        Layer toRemove = null;
        int c = 0;
        char l = 'a';
        for (Layer tmp : actualLayers) {
            if ((tmp.getNumberOfLayer() > c) || tmp.getNumberOfLayer() == c && greaterThan(tmp.getSubLayer(), l)) {
                toRemove = tmp;
                c = toRemove.getNumberOfLayer();
                l = toRemove.getSubLayer();
            }
        }
        if (toRemove.getMaxEletrons() > 1) {
            toRemove.setMaxEletrons(toRemove.getMaxEletrons() - 1);
        } else {
            actualLayers.remove(toRemove);
        }

        return buildDistributionString();
    }

    private boolean greaterThan(char subLayer, char l) {
        if (subLayer == 's' && l == 'a') {
            return true;
        } else if ((subLayer == 'p' && l == 's') || (subLayer == 'p' && l == 'p')) {
            return true;
        } else if ((subLayer == 'd' && l == 'p') || (subLayer == 'd' && l == 'd')) {
            return true;
        } else if ((subLayer == 'f' && l == 'd') || (subLayer == 'f' && l == 'f')) {
            return true;
        } else {
            return false;
        }

    }
}
