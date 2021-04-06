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
public class NodeAVL {
    
    private String id;
    private Object object;
    private int altura;
    private NodeAVL derc;
    private NodeAVL izq;
    
    public NodeAVL(){
        
    }

    public NodeAVL(String id, Object object) {
        this.id = id;
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodeAVL getDerc() {
        return derc;
    }

    public NodeAVL getIzq() {
        return izq;
    }

    public void setDerc(NodeAVL derc) {
        this.derc = derc;
    }

    public void setIzq(NodeAVL izq) {
        this.izq = izq;
    } 
}
