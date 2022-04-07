package br.com.danieljunior.bohrapp.models;


/**
 * Created by danieljr on 07/02/17.
 */
public class Element {
    private Long id;
    private String nome;
    private String simbolo;
    private int Z;
    private int A;
    private int proton;
    private int neutron;
    private int eletron;
    private String mensagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getZ() {
        return Z;
    }

    public void setZ(int z) {
        Z = z;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getProton() {
        return proton;
    }

    public void setProton(int proton) {
        this.proton = proton;
    }

    public int getNeutron() {
        return neutron;
    }

    public void setNeutron(int neutron) {
        this.neutron = neutron;
    }

    public int getEletron() {
        return eletron;
    }

    public void setEletron(int eletron) {
        this.eletron = eletron;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCharge() {
        int value = proton - eletron;
        if (value > 0) {
            return "+" + Math.abs(value);
        } else if (value < 0) {
            return "-" + Math.abs(value);
        } else {
            return value + "";
        }
    }
}
