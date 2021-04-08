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
    private int colmunaMayor;
    private int filaMayor;
    
    
    public MatrizDispersa(){
        inicio = new NodeMatriz(0,0,"Matriz");
        cantNodos = cantFilas = cantColumnas = colmunaMayor = filaMayor = 0;
    }
    
    public NodeMatriz CrearNodo(int x, int y , String color){
        return new NodeMatriz(x,y,color);
    }

    public int getCantFilas() {
        return cantFilas;
    }

    public int getCantColumnas() {
        return cantColumnas;
    }

    public int getColmunaMayor() {
        return colmunaMayor;
    }

    public int getFilaMayor() {
        return filaMayor;
    }
    
    
    private NodeMatriz insertarFila(int fila){
        NodeMatriz cabecera = inicio.getSigFila();
        NodeMatriz nuevo = new NodeMatriz(0,fila,String.valueOf(fila));
        if (fila > filaMayor) {
            filaMayor = fila;
        }
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
                    if (aux.getSigFila().getY() > fila) {
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
                cantFilas++;
            }
        }                
        return nuevo;
    }
    
    private NodeMatriz insertarColumna(int columna){
        NodeMatriz cabecera = inicio.getSigColumna();
        NodeMatriz nuevo = new NodeMatriz(columna,0,String.valueOf(columna));
        if (columna > colmunaMayor) {
            colmunaMayor = columna;
        }
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
                    if (aux.getSigColumna().getX() > columna) {
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
                cantColumnas++;
            }
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
        NodeMatriz  inicioFila = obtenerFila(fila,true);       
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
                while(aux.getSigFila() != null){
                    if (aux.getSigFila().getY() > fila) {
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
                    if (aux.getSigColumna().getX() > columna) {
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
    public boolean modNode(int columna, int fila, String color){
        NodeMatriz nodeColumna = obtenerColumna(columna, false);
        if (nodeColumna != null) {
            NodeMatriz aux = nodeColumna;
            while(aux != null){
                if (aux.getY() == fila) {
                    aux.setColor(color);
                    return true;
                }
                aux = aux.getSigFila();
            }
        }
        return false;
    }
    
    //graficar
    public String getEstado(String capa){
        String estado = "subgraph cluster_"+capa+"{\nstyle=filled;\nstyle=filled;\ncolor=white;\nnode [shape=box,color=black];\n";
        NodeMatriz aux = inicio;
        if (inicio == null) {
            return estado + "}";
        }
        if (aux.getSigFila() == null || aux.getSigColumna() == null) {
            return estado +"}";
        }
        String inicioMat = capa+aux.getColor();
        //columnas
        NodeMatriz auxCol = aux.getSigColumna();
        String sigCol = capa+auxCol.getColor();        
        estado += inicioMat +"->"+sigCol+"[constraint=false];\n";
        estado +=  inicioMat + "[label=\""+aux.getColor()+"\"];\n";
        while(auxCol != null){
            String actual = capa+auxCol.getColor();
            if (auxCol.getSigColumna() != null) {
                String sig = capa+auxCol.getSigColumna().getColor();
                estado += actual +"->"+sig+"[constraint=false];\n";
            }
            if (auxCol.getAntColumna() != null) {
                String ant = capa+auxCol.getAntColumna().getColor();
                estado += actual +"->"+ant+"[constraint=false];\n";
            }
            estado +=  actual + "[label=\""+auxCol.getColor()+"\"];\n"; 
            auxCol = auxCol.getSigColumna();
        }
        //filas
        NodeMatriz auxFila = aux.getSigFila();
        String sigFila = capa+"_fila_"+auxFila.getColor();        
        estado += inicioMat +"->"+sigFila+";\n";
        while(auxFila != null){
            String actual = capa+"_fila_"+auxFila.getColor();
            if (auxFila.getSigFila() != null) {
                String sig = capa+"_fila_"+auxFila.getSigFila().getColor();
                estado += actual +"->"+sig+";\n";
            }
            if (auxFila.getAntFila() != null) {
                String ant;
                if (auxFila.getAntFila().getColor().equals("Matriz")) {
                    ant = capa+auxFila.getAntFila().getColor();
                }else{
                    ant = capa+"_fila_"+auxFila.getAntFila().getColor();
                }                
                estado += actual +"->"+ant+";\n";
            }
            estado +=  actual + "[label=\""+auxFila.getColor()+"\"];\n";            
            auxFila = auxFila.getSigFila();                    
        }
        
        //contenido
        auxCol = aux.getSigColumna();
        auxFila = aux.getSigFila();        
        while(auxCol != null){            
            NodeMatriz auxCont = auxCol.getSigFila();
            while(auxCont != null){
                String actual = "contenido_"+ (corXY(auxCont));
                if (auxCont.getAntFila() != null) {
                    String sig;
                    if (auxCont.getAntFila().getY()==0) {
                        sig = capa+auxCont.getAntFila().getColor();
                        estado += sig +"->"+actual+";\n";
                    }else{
                        sig = "contenido_"+(corXY(auxCont.getAntFila()));
                    }
                    
                    estado += actual +"->"+sig+";\n";/////////
                }
                if (auxCont.getSigFila() != null) {
                    String sig = "contenido_"+(corXY(auxCont.getSigFila()));
                    estado += actual +"->"+sig+";\n";///////
                }
                if (auxCont.getAntColumna() != null) {
                    String sig;
                    if (auxCont.getAntColumna().getX()==0) {
                        sig = capa+"_fila_"+auxCont.getAntColumna().getColor();
                        estado += sig +"->"+actual+"[constraint=false];\n";
                    }else{
                        sig = "contenido_"+(corXY(auxCont.getAntColumna()));
                    }                   
                    
                    estado += actual +"->"+sig+"[constraint=false];\n";//
                }
                if (auxCont.getSigColumna() != null) {
                    String sig = "contenido_"+(corXY(auxCont.getSigColumna()));
                    estado += actual +"->"+sig+"[constraint=false];\n";
                }                 
                estado +=  actual + "[label=\""+auxCont.getColor()+"\"];\n"; 
                auxCont = auxCont.getSigFila();
            }
            auxCol = auxCol.getSigColumna();
        }
        return estado + "}";
    }
    
    private String corXY(NodeMatriz node){
        return ""+node.getX()+"_"+ node.getY()+"";
    }
    
    public String getDibujo(int tamX, int tamY, MatrizDispersa colores){
        String estado = "struct1 [label=<\n"
                + "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"4\">\n";
        for (int i = 0; i <= tamY; i++) {
            estado += "<TR>\n";
            for (int j = 0; j <= tamX; j++) {                
                if (i == 0) {
                    estado += "<TD width=\"50px\" height=\"50px\">"+j+"</TD>\n";
                }else if (j == 0) {
                    estado += "<TD width=\"50px\" height=\"50px\">"+i+"</TD>\n";
                }else{
                    String colorHex ="white";
                    NodeMatriz node = getNode(j,i);
                    if (node != null) {
                        colorHex = node.getColor();
                    }else{
                        node = colores.getNode(j,i);
                        if (node != null) {
                            colorHex = node.getColor();
                        }
                    }                    
                    estado += "<TD width=\"50px\" height=\"50px\" BGCOLOR=\""+colorHex+"\" ></TD>\n";
                }                
            }
            estado += "</TR>\n";
        }       
        estado += "</TABLE>>];\n";
        return estado;
    }
}
