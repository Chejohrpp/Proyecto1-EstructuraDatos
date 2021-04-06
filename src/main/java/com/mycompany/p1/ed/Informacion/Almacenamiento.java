/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed.Informacion;

import com.mycompany.p1.ed.Nodos.NodeAVL;
import com.mycompany.p1.ed.ReglasGram.Img.*;
import com.mycompany.p1.ed.ReglasGram.Users.LexerUser;
import com.mycompany.p1.ed.ReglasGram.Users.ParserUser;
import com.mycompany.p1.ed.ReglasGram.capas.LexerCapas;
import com.mycompany.p1.ed.ReglasGram.capas.ParserCapas;
import com.mycompany.p1.ed.arboles.AVL;
import com.mycompany.p1.ed.listas.ListCircular;
import com.mycompany.p1.ed.objetos.Capa;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author sergi
 */
public class Almacenamiento {
 
    //orden de carga de datos
    private AVL capas;    
    private ListCircular imagenes;
    private AVL usuarios;
    
    public Almacenamiento(){
        capas = null;
        imagenes = null;
        usuarios = null;
    }
    
    public void cargarCapas(String texto){
        Reader inputString = new StringReader(texto);
        BufferedReader reader = new BufferedReader(inputString);
        try{            
            LexerCapas lexerCapas = new LexerCapas(reader);
            ParserCapas parserCapas = new ParserCapas(lexerCapas);
            try{
                parserCapas.parse();
            }catch(Exception e){
                System.out.println("error al parser: " + e.getLocalizedMessage());
            }           
            List<String> erroresLexicos = lexerCapas.getErroresLexicos();
            List<String> erroresSintacticos = parserCapas.getListaErrores(); 
            if (erroresLexicos.isEmpty() && erroresSintacticos.isEmpty()) {
                capas = parserCapas.getTreeCapas();
                System.out.println("se cargaron las capas");    
            }else{
                System.out.println("Errores en cargar las capas"); 
                for (String erroresLexico : erroresLexicos) {                    
                    System.out.println(erroresLexico);
                }
                for (String erroresSintactico : erroresSintacticos) {
                    System.out.println(erroresSintactico);
                }
            }            
        }catch(Exception e){
            System.out.println("error al lexer: " + e.getMessage());
        }
    }
    
    public void cargarImagenes(String texto){
        Reader inputString = new StringReader(texto);
        BufferedReader reader = new BufferedReader(inputString);
        try{            
            LexerImg lexerImg = new LexerImg(reader);
            ParserImg parserImg = new ParserImg(lexerImg,capas);
            try{
                parserImg.parse();
            }catch(Exception e){
                System.out.println("error al parser: " + e.getLocalizedMessage());
            }           
            List<String> erroresLexicos = lexerImg.getErroresLexicos();
            List<String> erroresSintacticos = parserImg.getListaErrores(); 
            if (erroresLexicos.isEmpty() && erroresSintacticos.isEmpty()) {
                imagenes = parserImg.getListImgs();
                System.out.println("se cargaron las Imagenes");    
            }else{
                System.out.println("Errores en cargar las imagenes");
                for (String erroresLexico : erroresLexicos) {                    
                    System.out.println(erroresLexico);
                }
                for (String erroresSintactico : erroresSintacticos) {
                    System.out.println(erroresSintactico);
                }
            }            
        }catch(Exception e){
            System.out.println("error al lexer: " + e.getMessage());
        }
    }
    public void cargarUsuarios(String texto){
        Reader inputString = new StringReader(texto);
        BufferedReader reader = new BufferedReader(inputString);
        try{            
            LexerUser lexerUser = new LexerUser(reader);
            ParserUser parserUser = new ParserUser(lexerUser,imagenes);
            try{
                parserUser.parse();
            }catch(Exception e){
                System.out.println("error al parser: " + e.getLocalizedMessage());
            }           
            List<String> erroresLexicos = lexerUser.getErroresLexicos();
            List<String> erroresSintacticos = parserUser.getListaErrores(); 
            if (erroresLexicos.isEmpty() && erroresSintacticos.isEmpty()) {
                usuarios = parserUser.getListUsers();
                System.out.println("se cargaron los usuarios");    
            }else{
                System.out.println("Errores en cargar los usuarios");
                for (String erroresLexico : erroresLexicos) {                    
                    System.out.println(erroresLexico);
                }
                for (String erroresSintactico : erroresSintacticos) {
                    System.out.println(erroresSintactico);
                }
            }            
        }catch(Exception e){
            System.out.println("error al lexer: " + e.getMessage());
        }
    }

    public AVL getCapas() {
        return capas;
    }

    public void setCapas(AVL capas) {
        this.capas = capas;
    }

    public ListCircular getImagenes() {
        return imagenes;
    }

    public void setImagenes(ListCircular imagenes) {
        this.imagenes = imagenes;
    }

    public AVL getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(AVL usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    
}
