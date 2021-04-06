/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.Matrices;

import com.mycompany.p1.ed.Nodos.NodeMatriz;

/**
 *
 * @author sergi
 */
public class MatrizDispersa {
    
    private NodeMatriz inicio;
    private int cantNodos;
    private int cantFilas;
    private int cantColumnas;
    
    
    public MatrizDispersa(){
        inicio = new NodeMatriz(0,0,"Matriz");
        cantNodos = cantFilas = cantColumnas = 0;
    }
    
    public NodeMatriz CrearNodo(int x, int y , String color){
        return new NodeMatriz(x,y,color);
    }
    
    private NodeMatriz insertarFila(int fila){
        NodeMatriz cabecera = inicio.getSigFila();
        NodeMatriz nuevo = new NodeMatriz(0,fila,String.valueOf(fila));
        if (cabecera == null) {
            inicio.setSigFila(nuevo);
            nuevo.setAntFila(inicio);
        }else{
            if (cabecera.getY() > fila) {
                nuevo.setSigFila(cabecera);
                cabecera.setAntFila(nuevo);
                inicio.setSigFila(nuevo);
                nuevo.setAntFila(inicio);
            }else{
                NodeMatriz aux = cabecera;
                while(aux.getSigFila() != null){
                    if (aux.getSigFila().getY() < fila) {
                        nuevo.setSigFila(aux.getSigFila());
                        aux.getSigFila().setAntFila(nuevo);
                        nuevo.setAntFila(aux);
                        aux.setSigFila(nuevo);
                        cantFilas++;
                        return nuevo;
                    }
                    aux = aux.getSigFila();
                }
                aux.setSigFila(nuevo);
                nuevo.setAntFila(aux);
            }
        }
        if (fila > cantFilas) {
            cantFilas = fila;
        }        
        return nuevo;
    }
    
    private NodeMatriz insertarColumna(int columna){
        NodeMatriz cabecera = inicio.getSigColumna();
        NodeMatriz nuevo = new NodeMatriz(columna,0,String.valueOf(columna));
        if (cabecera == null) {
            inicio.setSigColumna(nuevo);
            nuevo.setAntColumna(inicio);
        }else{
            if (cabecera.getX() > columna) {
                nuevo.setSigColumna(cabecera);
                cabecera.setAntColumna(nuevo);
                inicio.setSigColumna(nuevo);
                nuevo.setAntColumna(inicio);
            }else{
                NodeMatriz aux = cabecera;
                while(aux.getSigColumna() != null){
                    if (aux.getSigColumna().getX() < columna) {
                        nuevo.setSigColumna(aux.getSigColumna());
                        aux.getSigColumna().setAntColumna(nuevo);
                        nuevo.setAntColumna(aux);
                        aux.setSigColumna(nuevo);
                        cantColumnas++;
                        return nuevo;
                    }
                    aux = aux.getSigColumna();
                }
                aux.setSigColumna(nuevo);
                nuevo.setAntColumna(aux);
            }
        }
        if (columna > cantColumnas) {
            cantColumnas = columna;
        }        
        return nuevo;
    }
    
    public void insertar(int columna, int fila, String color){
        NodeMatriz nodo = new NodeMatriz(columna, fila, color);
        insertar(nodo);
    }
    private void insertar(NodeMatriz nodito){
        NodeMatriz nuevo = nodito;
        int fila = nuevo.getY();
        int columna = nuevo.getX();
        NodeMatriz inicioFila = obtenerFila(fila,true);
        NodeMatriz inicioColumna = obtenerColumna(columna,true);       
        /*insersion en columnas*/
        NodeMatriz cabecera = inicioColumna.getSigFila();
        if (cabecera == null) {
            inicioColumna.setSigFila(nuevo);
            nuevo.setAntFila(inicioColumna);
        }else{
            if (cabecera.getY() > fila) {
                nuevo.setSigFila(cabecera);
                cabecera.setAntFila(nuevo);
                inicioColumna.setSigFila(nuevo);
                nuevo.setAntFila(inicioColumna);
            }else
            {
                NodeMatriz aux = cabecera;
                boolean insertado = false;
                while(aux != null){
                    if (aux.getSigFila().getY() < fila) {
                        nuevo.setSigFila(aux.getSigFila());
                        aux.getSigFila().setAntFila(nuevo);
                        nuevo.setAntFila(aux);
                        aux.setSigFila(nuevo);
                        insertado = true;
                        break;
                    }
                    aux = aux.getSigFila();
                }
                if (!insertado) {
                    aux.setSigFila(nuevo);
                    nuevo.setAntFila(aux);
                }                
            }
        }
        
        /* insersion en fila*/
        cabecera = inicioFila.getSigColumna();
        if (cabecera == null) {
            inicioFila.setSigColumna(nuevo);
            nuevo.setAntColumna(inicioFila);
        }else{
            if (cabecera.getX() > columna) {
                nuevo.setSigColumna(cabecera);
                cabecera.setAntColumna(nuevo);
                inicioFila.setSigColumna(nuevo);
                nuevo.setAntColumna(inicioFila);
            }else
            {
                NodeMatriz aux = cabecera;
                boolean insertado = false;
                while(aux.getSigColumna() != null){
                    if (aux.getSigColumna().getX() < columna) {
                        nuevo.setSigColumna(aux.getSigColumna());
                        aux.getSigColumna().setAntColumna(nuevo);
                        nuevo.setAntColumna(aux);
                        aux.setSigColumna(nuevo);
                        insertado = true;
                        break;                        
                    }
                    aux = aux.getSigColumna();
                }
                if (!insertado) {
                    aux.setSigColumna(nuevo);
                    nuevo.setAntColumna(aux);
                }
            }
        }
        cantNodos++;        
    }

    private NodeMatriz obtenerFila(int fila, boolean flag) {
      NodeMatriz aux = inicio.getSigFila();
      while(aux != null){
          if (aux.getY() == fila) {
              return aux;
          }
          aux = aux.getSigFila();
      }
        if (flag) {
            return insertarFila(fila);
        }
        return null;
    }

    private NodeMatriz obtenerColumna(int columna, boolean flag) {
        NodeMatriz aux = inicio.getSigColumna();
      while(aux != null){
          if (aux.getX() == columna) {
              return aux;
          }
          aux = aux.getSigColumna();
      }
        if (flag) {
            return insertarColumna(columna);
        }
        return null;
    }
    
    public NodeMatriz getNode(int columna, int fila){
        NodeMatriz nodeColumna = obtenerColumna(columna, false);
        if (nodeColumna != null) {
            NodeMatriz aux = nodeColumna;
            while(aux != null){
                if (aux.getY() == fila) {
                    return aux;
                }
                aux = aux.getSigFila();
            }
        }
        return null;
    }
    
    //graficar
    public String getEstado(String capa){
        String estado = "subgraph cluster_"+capa+"{\nstyle=filled;\nstyle=filled;\ncolor=white;\nnode [shape=box,color=black];\n";
        
        return estado + "}";
    }
}
