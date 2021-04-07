/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.objetos;

import com.mycompany.p1.ed.Nodos.NodeLS;
import com.mycompany.p1.ed.arboles.AVL;
import com.mycompany.p1.ed.listas.ListSimple;

/**
 *
 * @author sergi
 */
public class Imagen {
    
    private String id;
    private ListSimple listaCapas;
    
    public Imagen(){
        id = null;
        listaCapas = null;
    }

    public Imagen(String id) {
        this.id = id;
        listaCapas = null;
    }

    public Imagen(String id, ListSimple listaCapas) {
        this.id = id;
        this.listaCapas = listaCapas;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListSimple getListaCapas() {
        return listaCapas;
    }

    public void setListaCapas(ListSimple listaCapas) {
        this.listaCapas = listaCapas;
    }
    
    public String getEstado(String treeCapas,String prexTree, String img){
        String estado = "compound=true;\nsubgraph cluster_"+img+"{\nstyle=filled;\n"
                + "style=filled;\ncolor=white;\nnode [shape=box,color=black];\n";
        if (listaCapas == null) {
            return estado +"}";
        }
        String nodeInicio = img+id;
        String nodosLista = listaCapas.getEstado(img, nodeInicio);
        estado +=  nodeInicio + "[label=\""+ id+" \"];\n";
        estado += nodosLista;
        estado += "}\n";
        estado += treeCapas;
        NodeLS aux = listaCapas.getPrimero();
        while(aux != null){
            String nombreCapa = aux.getId();
            estado += img+nombreCapa+"->"+prexTree+nombreCapa+";\n";
            aux = aux.getSig();
        }   
        return estado;
    }
    
    
}
