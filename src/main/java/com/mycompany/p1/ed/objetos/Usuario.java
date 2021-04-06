/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.objetos;

import com.mycompany.p1.ed.listas.ListSimple;

/**
 *
 * @author sergi
 */
public class Usuario {
    private String id;
    private ListSimple imagenes;
    
    public Usuario(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListSimple getImagenes() {
        return imagenes;
    }

    public void setImagenes(ListSimple imagenes) {
        this.imagenes = imagenes;
    }
    
}
