/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.objetos;

import com.mycompany.p1.ed.Matrices.MatrizDispersa;
import com.mycompany.p1.ed.Nodos.NodeAVL;
import com.mycompany.p1.ed.Nodos.NodeLS;
import com.mycompany.p1.ed.Nodos.NodeMatriz;
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
        String nodosLista = listaCapas.getEstado(img+"_capas", nodeInicio);
        estado +=  nodeInicio + "[label=\""+ id+" \"];\n";
        estado += nodosLista;
        estado += "}\n";
        estado += treeCapas;
        NodeLS aux = listaCapas.getPrimero();
        while(aux != null){
            String nombreCapa = aux.getId();
            estado += img+"_capas"+nombreCapa+"->"+prexTree+nombreCapa+";\n";
            aux = aux.getSig();
        }   
        return estado;
    }
    
    public String getImgen(AVL capas){
        String estado = "";
        int tamX = 0;
        int tamY = 0;
        MatrizDispersa matriz = new MatrizDispersa();
        NodeLS aux = listaCapas.getPrimero();
        if (aux == null) {
            estado = "struct1 [label=<\n"
                + "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"4\">\n";
             estado += "<TR>\n";
             estado += "<TD width=\"50px\" height=\"50px\" BGCOLOR=\"black\" ></TD>\n";
             estado += "</TR>\n";
            estado += "</TABLE>>];\n";
            return estado;
        }
        NodeAVL nodoCapa = capas.find(aux.getId());
        while(aux != null){
            nodoCapa = capas.find(aux.getId());
            if (nodoCapa !=  null) {
                Capa capa = (Capa) nodoCapa.getObject();
                int x = capa.getMatriz().getColmunaMayor();
                int y = capa.getMatriz().getFilaMayor();
                if (x > tamX) {
                    tamX = x;
                }
                if (y > tamY) {
                    tamY = y;
                }
                for (int i = 1; i <= tamX; i++) {
                    for (int j = 1; j <= tamY; j++) {
                        NodeMatriz nodo = capa.getMatriz().getNode(i,j);
                        if (nodo != null) {
                            NodeMatriz nodoMatriz = matriz.getNode(i, j);
                            if (nodoMatriz != null) {
                                matriz.modNode(i, j, nodo.getColor());
                            }else{
                                matriz.insertar(i, j, nodo.getColor());
                            }
                        }
                    }
                }
            }
            aux = aux.getSig();
        }    
        Capa capa = (Capa) nodoCapa.getObject();
        estado = capa.getMatriz().getDibujo(tamX, tamY, matriz);
        return estado;
    }
    
}
