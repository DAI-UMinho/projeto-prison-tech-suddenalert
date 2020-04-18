/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author jorge
 */
public class Recluso {
    private int id, numero_recluso;
    private String nome, ala, piso, doenças;
    
    public Recluso (int id, int numero_recluso, String nome, String ala, String piso, String doenças)
    {
        this.id=id;
        this.numero_recluso=numero_recluso;
        this.nome=nome;
        this.ala=ala;
        this.piso=piso;
        this.doenças=doenças;
    }   

    public int getid() {
        return id;
    }
    
    public int getnumero_recluso() {
        return numero_recluso;
    }
    
    public String getnome() {
        return nome;
    }
    
    public String getala() {
        return ala;
    }
    
    public String getpiso() {
        return piso;
    }
    
    public String getdoenças() {
        return doenças;
    }
}
