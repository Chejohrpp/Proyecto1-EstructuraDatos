/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.objetos;

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
    
    
}
