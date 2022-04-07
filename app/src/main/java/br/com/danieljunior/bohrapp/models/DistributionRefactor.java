package br.com.danieljunior.bohrapp.models;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by danieljr on 21/03/17.
 */

public class DistributionRefactor {
    private final Queue<Layer> toAddLayers;
    private final Queue<Layer> toRemoveLayers;

    public DistributionRefactor() {
        Layer l1 = new Layer(1, 's', 2);
        Layer l2 = new Layer(2, 's', 2);
        Layer l3 = new Layer(2, 'p', 6);
        Layer l4 = new Layer(3, 's', 2);
        Layer l5 = new Layer(3, 'p', 6);
        Layer l6 = new Layer(4, 's', 2);
        Layer l7 = new Layer(3, 'd', 10);
        Layer l8 = new Layer(4, 'p', 6);

        toAddLayers = new ArrayDeque<>();
        toAddLayers.add(l1);
        toAddLayers.add(l2);
        toAddLayers.add(l3);
        toAddLayers.add(l4);
        toAddLayers.add(l5);
        toAddLayers.add(l6);
        toAddLayers.add(l7);
        toAddLayers.add(l8);

        toRemoveLayers = new ArrayDeque<>();
        toRemoveLayers.add(l8);
        toRemoveLayers.add(l6);
        toRemoveLayers.add(l7);
        toRemoveLayers.add(l5);
        toRemoveLayers.add(l4);
        toRemoveLayers.add(l3);
        toRemoveLayers.add(l2);
        toRemoveLayers.add(l1);
    }

    public void addEletron() {
        for (Layer layer : toAddLayers) {
            if (layer.increaseEletrons()) {
                break;
            }
        }
    }

    public void removeEletron() {
        for (Layer layer : toRemoveLayers) {
            if (layer.decreaseEletrons()) {
                break;
            }
        }
    }

    public String getEletronicDistribution() {
        String resp = "";
        for (Layer layer : toAddLayers) {
            if (layer.getCurrentEletrons() > 0) {
                resp+= layer.getNumberOfLayer();
                resp+= layer.getSubLayer();
                String currentEletrons = layer.getCurrentEletrons() + "";
                for (char c : currentEletrons.toCharArray()) {
                    resp+= charToSuperscript(c + "");
                }
            }
        }
        return resp;
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
}
