/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.listas;

import com.mycompany.p1.ed.Nodos.NodeListC;
import com.mycompany.p1.ed.objetos.Imagen;

/**
 *
 * @author sergi
 */
public class ListCircular {
    
    NodeListC primero;
    NodeListC ult;
    
    
    public ListCircular(){
        primero = null;
        ult = null;
    }
    
    public boolean add(Imagen imagen){
        Imagen img = buscar(imagen.getId());
        if (img != null) {
            return false;
        }
        NodeListC newNode = new NodeListC();
        newNode.setImg(imagen);
        if (primero == null) {
            primero = newNode;
            primero.setAnt(ult);
            primero.setSig(primero);
            ult = primero;
        }else {
            ult.setSig(newNode);
            newNode.setAnt(ult);
            ult = newNode;
            ult.setSig(primero);
            primero.setAnt(ult);
        }
        return true;
    }
    
    private void ordenarLista(){
        NodeListC aux = primero;
        if (primero != null) {
            do {                
                NodeListC j = aux.getSig();
                while(j != aux){
                    if (aux.getImg().getId().compareTo(j.getImg().getId()) > 0 ) {
                        Imagen img = aux.getImg();
                        aux.setImg(j.getImg());
                        j.setImg(img);
                    }
                    j = j.getSig();
                }                
                aux = aux.getSig();
            } while (aux != primero);            
        }
    }
    
    public boolean eliminar(String id){
        NodeListC aux = primero;   
        if (primero == null) {
            return false;
        }
        do {
            if (aux.getImg().getId().equals(id)) {            
                if (aux == primero) {
                    if (ult == primero) {
                        primero = null;
                        ult = null;
                    }else{
                        primero.getSig().setAnt(ult);                        
                        primero = primero.getSig();
                        ult.setSig(primero);
                    }               
                }else if(aux == ult){
                    ult.getAnt().setSig(primero);
                    ult = ult.getAnt();
                    primero.setAnt(ult);
                }else{
                    aux.getSig().setAnt(aux.getAnt());
                    aux.getAnt().setSig(aux.getSig());
                }
                return true;
            }            
            aux = aux.getSig();
        } while (aux != primero);
        
        return false;
    }
    
    public void modDato(String id){       
    }
    
    public Imagen buscar(String id){
        NodeListC aux = primero;
        if (primero == null) {
            return null;
        }
        do {
            if (aux.getImg().getId().equals(id)) {
                return aux.getImg();
            }
            aux = aux.getSig();
        } while (aux != primero);       
        return null;
    }
    
    //graficar
    public String getEstado(){
        ordenarLista();
        String estado = "subgraph cluster_imagenes{\nstyle=filled;\nstyle=filled;\ncolor=white;\nnode [shape=box,color=black];\n";
        NodeListC aux = primero;
        if (primero == null) {
            return estado +"}";
        }
        do {
            String actual = "img_"+aux.getImg().getId();
            String sig = "img_"+aux.getSig().getImg().getId();
            estado += actual +"->"+ sig +"[constraint=false];\n";                                             
            estado +=  actual + "[label=\""+aux.getImg().getId()+"\"];\n";                         
            aux = aux.getSig();
        } while (aux != primero);
        aux = primero;
        do {
            String actual = "img_"+aux.getImg().getId();
            String ant = "img_"+aux.getAnt().getImg().getId();
            estado += actual +"->"+ ant +"[constraint=false];\n";
            if (aux.getImg().getListaCapas() !=  null) {
                estado += aux.getImg().getListaCapas().getEstado("capa_"+actual, actual);
            }            
            aux = aux.getSig();
        } while (aux != primero);        
        return estado + "}";
    }
    
    
    
    
}
