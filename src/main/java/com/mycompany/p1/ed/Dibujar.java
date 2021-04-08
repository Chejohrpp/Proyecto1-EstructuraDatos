/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class Dibujar {
    
    
    public Dibujar(){
       
    }
    
    private void dibujarSistema(String estado, String nombreImg){
        String dibujar = "digraph g{ "+estado+" }";        
         crearPng(dibujar,nombreImg);
     }
     private void DibujarImg(String estado, String nombreImg){
         String dibujar = "digraph structs{node [shape=plaintext];\n "+estado+" }";
         crearPng(dibujar,nombreImg);
     }
     private void crearPng(String dibujar, String nombreImg){
         File fileDibujable = new File("img/dibujable.dot");
        if (!fileDibujable.exists()) {
            try {
                fileDibujable.createNewFile();
            } catch (IOException ex) {
                System.out.println("error al crear el archivo dibujable: " + ex.getMessage());
            }
        }
        escribirFile(fileDibujable, dibujar);
        File fileImg = new File("img/"+nombreImg+".png");
        try{
            String command = "dot -Tpng "+ fileDibujable.getAbsolutePath()+ " -o " + fileImg.getAbsolutePath();
            Process process = Runtime.getRuntime().exec(command); 
        }catch(Exception e){
            System.out.println("error en el proceso de dibujar: "+ e.getMessage());
        }               
    }
     private void escribirFile(File file, String contenido){
        try(FileWriter fw=new FileWriter(file)){
            fw.write(contenido);
            fw.close();
        } catch (IOException e1) {
             //e1.printStackTrace();
        }         
     }
     
     public void listaImagenes(String estado){
         dibujarSistema(estado,"listaImagenes");
     }
     public void verTreeCapas(String estado){
         dibujarSistema(estado,"ArbolCapas");
     }
     public void verTreeUser(String estado){
         dibujarSistema(estado,"ArbolUsuarios");
     }
     public void verMatrizCapa(String estado, String nombreCapa){
         dibujarSistema(estado,"Matriz_Capa_"+nombreCapa);
     }
     public void VerImagenTreeCapas(String estado, String nombreImagen){
         dibujarSistema(estado,"Imagen_"+nombreImagen);
     }
     public void generarImg(String estado, String img){
         DibujarImg(estado,"Img_"+img);
     }
     
}
