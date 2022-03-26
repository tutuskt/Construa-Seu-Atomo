package br.com.danieljunior.bohrapp.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.danieljunior.bohrapp.R;
import br.com.danieljunior.bohrapp.database.ElementDAO;
import br.com.danieljunior.bohrapp.interfaces.BohrInterface;
import br.com.danieljunior.bohrapp.models.Distribution;
import br.com.danieljunior.bohrapp.models.DistributionRefactor;
import br.com.danieljunior.bohrapp.models.Element;
import br.com.danieljunior.bohrapp.models.Particle;
import br.com.danieljunior.bohrapp.util.ParticleGenerator;

/**
 * Created by danieljr on 09/02/17.
 */

public class ElementController {


    public int NONE_ACTION = 0;
    public int ADD_ACTION = 1;
    public int REMOVE_ACTION = 2;
    private int ACTION_SELECTED = 1;
    private int PARTICLE_TYPE_SELECTED = 0;
    private int MAXIMUM_ELETRONS = 36;
    private int MAXIMUM_NEUTRONS = 48;
    private int MAXIMUM_PROTONS = 36;
    private int protons = 0;
    private int neutrons = 0;
    private int eletrons = 0;
    private static int PARTICLE_RADIUS = 40;
    private static int PARTICLE_BORDER_RADIUS = 40;
    private ParticleGenerator generator;
    private List<Particle> particleList;
    private BohrInterface bohrInterface;
    Context context;
    private ElementDAO dao;
//    private Distribution eletronicDistribution;
    private DistributionRefactor refactorDistribution;
    private int[] eletronsResourcesList = {
            R.mipmap.e0,
            R.mipmap.e1,
            R.mipmap.e2,
            R.mipmap.e3,
            R.mipmap.e4,
            R.mipmap.e5,
            R.mipmap.e6,
            R.mipmap.e7,
            R.mipmap.e8,
            R.mipmap.e9,
            R.mipmap.e10,
            R.mipmap.e11,
            R.mipmap.e12,
            R.mipmap.e13,
            R.mipmap.e14,
            R.mipmap.e15,
            R.mipmap.e16,
            R.mipmap.e17,
            R.mipmap.e18,
            R.mipmap.e19,
            R.mipmap.e20,
            R.mipmap.e21,
            R.mipmap.e22,
            R.mipmap.e23,
            R.mipmap.e24,
            R.mipmap.e25,
            R.mipmap.e26,
            R.mipmap.e27,
            R.mipmap.e28,
            R.mipmap.e29,
            R.mipmap.e30,
            R.mipmap.e31,
            R.mipmap.e32,
            R.mipmap.e33,
            R.mipmap.e34,
            R.mipmap.e35,
            R.mipmap.e36,
    };

    public ElementController(BohrInterface bohrInterface, Context context) {
        this.bohrInterface = bohrInterface;
        this.context = context;
        try {
            dao = new ElementDAO(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        generator = new ParticleGenerator();
        particleList = new ArrayList<>();
//        eletronicDistribution = new Distribution();
        refactorDistribution = new DistributionRefactor();
    }

    public void setProtonSelected() {
        PARTICLE_TYPE_SELECTED = Particle.PROTON;
    }

    public void setNeutronSelected() {
        PARTICLE_TYPE_SELECTED = Particle.NEUTRON;
    }

    public void setEletronSelected() {
        PARTICLE_TYPE_SELECTED = Particle.ELETRON;
    }

    public void protonOrNeutronAction() {
        if (ACTION_SELECTED == ADD_ACTION) {
            addProtonOrNeutron();
        } else if (ACTION_SELECTED == NONE_ACTION) {
            bohrInterface.showCommonMessage("Atenção", "Você precisa selecionar 'Adicionar' ou 'Remover'");
        } else {
            removeProtonOrNeutron();
        }
    }

    private void removeProtonOrNeutron() {
        if (PARTICLE_TYPE_SELECTED == 0) {
            bohrInterface.showCommonMessage("Atenção", "Você deve selecionar uma partícula primeiro!");
        } else if (protons == 1 && PARTICLE_TYPE_SELECTED == Particle.PROTON &&
                ((neutrons > 0 && eletrons > 0) || (neutrons > 0 && eletrons >= 0) || (neutrons >= 0 && eletrons > 0))) {
            bohrInterface.showCommonMessage("Atenção", "Você não pode ter um elemento sem PRÓTON!");
        } else if (PARTICLE_TYPE_SELECTED == Particle.ELETRON) {
            bohrInterface.showCommonMessage("Atenção", "Você só pode remover PRÓTONS ou NÊUTRONS do núcleo!");
        } else if (PARTICLE_TYPE_SELECTED != Particle.ELETRON) {

            if (PARTICLE_TYPE_SELECTED == Particle.PROTON) {
                if (protons > 0) {
                    protons -= 1;
                    for (Particle p : particleList) {
                        if (p.getType() == Particle.PROTON) {
                            particleList.remove(p);
                            break;
                        }
                    }
                    reorderParticles();
                } else {
                    bohrInterface.showCommonMessage("Atenção", "Você não pode remover mais PRÓTONS!");
                }
            } else {
                if (neutrons > 0) {
                    neutrons -= 1;
                    for (Particle p : particleList) {
                        if (p.getType() == Particle.NEUTRON) {
                            particleList.remove(p);
                            break;
                        }
                    }
                    reorderParticles();
                } else {
                    bohrInterface.showCommonMessage("Atenção", "Você não pode remover mais NÊUTRONS!");
                }
            }

        }
    }

    private void reorderParticles() {
        List<Particle> tmp = new ArrayList<>();
        generator = new ParticleGenerator();
        for (Particle p : particleList) {
            tmp.add(generator.generate(p.getType()));
        }
        particleList = tmp;
        drawParticles();
    }

    public void addProtonOrNeutron() {
        if (PARTICLE_TYPE_SELECTED == 0) {
            bohrInterface.showCommonMessage("Atenção", "Você deve selecionar uma partícula primeiro!");
        } else if (protons == 0 && PARTICLE_TYPE_SELECTED != Particle.PROTON) {
            bohrInterface.showCommonMessage("Atenção", "Você deve começar por um PRÓTON!");
        } else if (PARTICLE_TYPE_SELECTED == Particle.ELETRON) {
            bohrInterface.showCommonMessage("Atenção", "O ELÉTRON só pode ser inserido na eletrosfera!");
        } else if (PARTICLE_TYPE_SELECTED != Particle.ELETRON) {
            Particle particle = generator.generate(PARTICLE_TYPE_SELECTED);
            particleList.add(particle);
            if (PARTICLE_TYPE_SELECTED == Particle.PROTON) {
                if (protons < MAXIMUM_PROTONS) {
                    protons += 1;
                    drawParticles();
                } else {
                    bohrInterface.showCommonMessage("Atenção", "O número máximo de PRÓTONS para esta versão é 36!");
                }
            } else {
                if (neutrons < MAXIMUM_NEUTRONS) {
                    neutrons += 1;
                    drawParticles();
                } else {
                    bohrInterface.showCommonMessage("Atenção", "O número máximo de NÊUTRONS para esta versão é 48!");
                }
            }

        }
    }

    public void eletronAction() {
        if (ACTION_SELECTED == ADD_ACTION) {
            addEletron();
        } else if (ACTION_SELECTED == NONE_ACTION) {
            bohrInterface.showCommonMessage("Atenção", "Você precisa selecionar 'Adicionar' ou 'Remover'");
        } else {
            removeEletron();
        }
    }

    private void removeEletron() {
        if (PARTICLE_TYPE_SELECTED == 0) {
            bohrInterface.showCommonMessage("Atenção", "Você deve selecionar uma partícula primeiro!");
        } else if (PARTICLE_TYPE_SELECTED == Particle.PROTON || PARTICLE_TYPE_SELECTED == Particle.NEUTRON) {
            bohrInterface.showCommonMessage("Atenção", "Você só pode remover ELÉTRONS da eletrosfera!");
        } else if (PARTICLE_TYPE_SELECTED == Particle.ELETRON) {
            if (eletrons > 0) {
                eletrons -= 1;
                refactorDistribution.removeEletron();
                Bitmap bg = BitmapFactory.decodeResource(context.getResources(), eletronsResourcesList[eletrons]);
                bohrInterface.setEletrosphereBitmap(bg);
                setElementData();
            } else {
                bohrInterface.showCommonMessage("Atenção", "Você não pode remover mais ELÉTRONS!");
            }
        }
    }

    public void addEletron() {
        if (PARTICLE_TYPE_SELECTED == 0) {
            bohrInterface.showCommonMessage("Atenção", "Você deve selecionar uma partícula primeiro!");
        } else if (protons == 0 && PARTICLE_TYPE_SELECTED != Particle.PROTON) {
            bohrInterface.showCommonMessage("Atenção", "Você deve começar adicionando um PRÓTON!");
        } else if (PARTICLE_TYPE_SELECTED == Particle.PROTON || PARTICLE_TYPE_SELECTED == Particle.NEUTRON) {
            bohrInterface.showCommonMessage("Atenção", "PRÓTONS e NEUTRONS só podem ser inseridos no núcleo!");
        } else if (PARTICLE_TYPE_SELECTED == Particle.ELETRON) {
            if (eletrons < MAXIMUM_ELETRONS) {
                eletrons += 1;
                refactorDistribution.addEletron();
                Bitmap bg = BitmapFactory.decodeResource(context.getResources(), eletronsResourcesList[eletrons]);
                bohrInterface.setEletrosphereBitmap(bg);
                setElementData();
            } else {
                bohrInterface.showCommonMessage("Atenção", "O número máximo de ELÉTRONS para esta versão é 36!");
            }
        }
    }

    private void drawParticles() {
        Bitmap bg = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bg);
        for (Particle newParticle : particleList) {
            canvas.drawCircle(newParticle.getX(), newParticle.getY(), PARTICLE_RADIUS, generator.getFillPaint(newParticle));
            canvas.drawCircle(newParticle.getX(), newParticle.getY(), PARTICLE_BORDER_RADIUS, generator.getBorderPaint());
        }
        bohrInterface.setCoreBitmap(bg);
        setElementData();
    }

    public void setElementData() {
        Element element = dao.byPN(protons, neutrons);
        if (element != null) {
            element.setEletron(eletrons);
            element.setNeutron(neutrons);
            if (ACTION_SELECTED != REMOVE_ACTION) {
                bohrInterface.setEletronicDistribution(refactorDistribution.getEletronicDistribution());
            } else if(ACTION_SELECTED == REMOVE_ACTION && PARTICLE_TYPE_SELECTED == Particle.ELETRON){
                bohrInterface.setEletronicDistribution(refactorDistribution.getEletronicDistribution());
            }
            bohrInterface.setElementData(element);

            if (element.getMensagem().contains("elemento")) {
                bohrInterface.showElementMessage();
            } else {
                bohrInterface.showIsotopoMessage();
            }
        } else if (PARTICLE_TYPE_SELECTED == Particle.PROTON && ACTION_SELECTED == REMOVE_ACTION) {
            bohrInterface.clearData();
        } else {
            bohrInterface.showCommonMessage("Atenção", "Você chegou ao nosso último elemento suportado!");
        }
    }


    public void setActionSelected(int actionSelected) {
        ACTION_SELECTED = actionSelected;
    }

    public int getFirstEletrosphere() {
        return eletronsResourcesList[0];
    }
}
