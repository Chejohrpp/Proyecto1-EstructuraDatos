/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.Nodos;

import com.mycompany.p1.ed.objetos.Imagen;

/**
 *
 * @author sergi
 */
public class NodeListC {
    
    private Imagen img;
    private NodeListC sig;
    private NodeListC ant;
    
    public NodeListC(){        
    }

    public Imagen getImg() {
        return img;
    }

    public void setImg(Imagen img) {
        this.img = img;
    } 

    public NodeListC getSig() {
        return sig;
    }

    public void setSig(NodeListC sig) {
        this.sig = sig;
    }

    public NodeListC getAnt() {
        return ant;
    }

    public void setAnt(NodeListC ant) {
        this.ant = ant;
    }
    
    
    
}
