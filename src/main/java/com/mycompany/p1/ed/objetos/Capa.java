/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.objetos;

import com.mycompany.p1.ed.Matrices.MatrizDispersa;

/**
 *
 * @author sergi
 */
public class Capa {
    String id;
    MatrizDispersa matriz;
    
    public Capa(){
        id=null;
        matriz = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MatrizDispersa getMatriz() {
        return matriz;
    }

    public void setMatriz(MatrizDispersa matriz) {
        this.matriz = matriz;
    }
    
}
