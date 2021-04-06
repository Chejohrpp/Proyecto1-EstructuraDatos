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
public class NodeMatriz {
    
    private int x;
    private int y;
    private String color;
    private NodeMatriz antFila,antColumna,sigFila, sigColumna;

    public NodeMatriz(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        antFila = null;
        antColumna = null;
        sigFila = null;
        sigColumna = null;
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public NodeMatriz getAntFila() {
        return antFila;
    }

    public void setAntFila(NodeMatriz antFila) {
        this.antFila = antFila;
    }

    public NodeMatriz getAntColumna() {
        return antColumna;
    }

    public void setAntColumna(NodeMatriz antColumna) {
        this.antColumna = antColumna;
    }

    public NodeMatriz getSigFila()  {
        return sigFila;
    }

    public void setSigFila(NodeMatriz sigFila) {
        this.sigFila = sigFila;
    }

    public NodeMatriz getSigColumna() {
        return sigColumna;
    }

    public void setSigColumna(NodeMatriz sigColumna) {
        this.sigColumna = sigColumna;
    }
    
    
}
