/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.listas;

import com.mycompany.p1.ed.Nodos.NodeLS;

/**
 *
 * @author sergi
 */
public class ListSimple {
    
    private NodeLS primero;
    private NodeLS ult;
    
    public ListSimple(){
        primero = null;
        ult = null;
    }
    
    public boolean add(String id){
        if (id == null) {
            return false;
        }
        NodeLS nuevo = new NodeLS();
        nuevo.setId(id);
        if (primero == null) {
            primero = nuevo;
            primero.setAnt(null);
            primero.setSig(null);
            ult = primero;
        }else
        {
            ult.setSig(nuevo);
            nuevo.setAnt(ult);
            nuevo.setSig(null);
            ult = nuevo;
        }
        return true;
    }
    
    public boolean eliminar(String id){
        NodeLS aux = primero;
        if (primero == null) {
            return false;
        }
        do {
            if (aux.getId().equals(id)) {
                if (aux == primero) {
                    primero = primero.getSig();                    
                }else if(aux == ult){
                    ult = ult.getAnt();
                    ult.setSig(null);
                }else{
                    aux.getAnt().setSig(aux.getSig());
                    aux.getSig().setAnt(aux.getAnt());                              
                }
                return true;
            }
            aux = aux.getSig();
        } while (aux != null);
        return false;
    }
    
    public boolean verificarId(String id){
        NodeLS aux = primero;
        if (primero == null) {
            return false;
        }
        do {
            if (aux.getId().equals(id)) {
                return true;
            }          
        } while (aux != null);
        
        return false;
    }
    
    public String getEstado(String nombre, String idImg){
        String estado = "";
        NodeLS aux = primero;
        if (primero == null) {
            return estado;
        }
        do {
            String actual = nombre+aux.getId();
            if (aux == primero)
            {
                    estado += idImg + "->"+ actual + ";\n";
                    if (ult!=primero)
                    {
                            String sig = nombre+ aux.getSig().getId();
                            estado += actual +"->"+sig + ";\n";
                    }
            }else if (aux != ult){
                    String sig = nombre+ aux.getSig().getId();
                    estado += actual +"->"+sig + ";\n";
            }
            estado +=  actual + "[label=\""+ aux.getId()+" \"];\n";                       
            aux = aux.getSig();
        } while (aux != null);        
        return estado;
    }
    
}
