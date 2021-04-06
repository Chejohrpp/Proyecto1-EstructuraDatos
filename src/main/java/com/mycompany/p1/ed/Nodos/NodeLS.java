/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.Nodos;

/**
 *
 * @author sergi
 */
public class NodeLS {
    
    private String id;
    private NodeLS sig;
    private NodeLS ant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    } 

    public NodeLS getSig() {
        return sig;
    }

    public void setSig(NodeLS sig) {
        this.sig = sig;
    }

    public NodeLS getAnt() {
        return ant;
    }

    public void setAnt(NodeLS ant) {
        this.ant = ant;
    }
    
    
    
    
}
