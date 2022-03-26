package br.com.danieljunior.bohrapp.util;

import android.graphics.Color;
import android.graphics.Paint;

import br.com.danieljunior.bohrapp.models.Particle;

/**
 * Created by danieljr on 09/02/17.
 */

public class ParticleGenerator {

    private Paint red_paintbrush_fill, green_paintbrush_fill, black_paintbrush_stroke;
    private Paint fillPaint;
    private PositionGenerator positionGenerator;

    public ParticleGenerator() {
        positionGenerator = new PositionGenerator();
        setPincels();
    }

    public Particle generate(int type) {
        if (type == Particle.NEUTRON) {
            fillPaint = green_paintbrush_fill;
        } else {
            fillPaint = red_paintbrush_fill;
        }
        int[] position = positionGenerator.randomPositionGenerate();
        return new Particle(position[0], position[1], type);

    }

    public Paint getBorderPaint() {
        return black_paintbrush_stroke;
    }

    public Paint getFillPaint() {
        return fillPaint;
    }


    public Paint getFillPaint(Particle particle) {
        if(particle.getType() == Particle.NEUTRON){
            return green_paintbrush_fill;
        }else{
            return red_paintbrush_fill;
        }
    }

    public void setPincels() {
        black_paintbrush_stroke = new Paint();
        black_paintbrush_stroke.setColor(Color.BLACK);
        black_paintbrush_stroke.setStyle(Paint.Style.STROKE);

        red_paintbrush_fill = new Paint();
        red_paintbrush_fill.setColor(Color.RED);
        red_paintbrush_fill.setStyle(Paint.Style.FILL);

        green_paintbrush_fill = new Paint();
        green_paintbrush_fill.setColor(Color.GREEN);
        green_paintbrush_fill.setStyle(Paint.Style.FILL);
    }
}
