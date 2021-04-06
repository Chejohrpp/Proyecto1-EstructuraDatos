/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed;

import com.mycompany.p1.ed.Informacion.Almacenamiento;
import com.mycompany.p1.ed.Matrices.MatrizDispersa;
import com.mycompany.p1.ed.arboles.AVLTree;
import com.mycompany.p1.ed.listas.ListCircular;
import com.mycompany.p1.ed.listas.ListSimple;
import com.mycompany.p1.ed.objetos.Capa;
import com.mycompany.p1.ed.objetos.Imagen;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author sergi
 */
public class Principal {
    
    private static Almacenamiento almacenamiento = new Almacenamiento();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {        
        while(true){
            System.out.println("Presione 1 para cargar los datos");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) {
                CargarDatos cargaDatos = new CargarDatos();
                File[] files  = cargaDatos.getFile();
                for (File file : files) {
                    String ext = FilenameUtils.getExtension(file.getAbsolutePath());
                    if (ext.equals("cap")) {                        
                        almacenamiento.cargarCapas(converterString(file));     
                        break;
                    }                   
                }
                for (File file : files) {
                    String ext = FilenameUtils.getExtension(file.getAbsolutePath());
                    if (ext.equals("im")) {                        
                        almacenamiento.cargarImagenes(converterString(file));
                        break;
                    }
                }
                for (File file : files) {
                    String ext = FilenameUtils.getExtension(file.getAbsolutePath());
                    if (!ext.equals("cap") && !ext.equals("im") ) {                        
                         almacenamiento.cargarUsuarios(converterString(file)); 
                         break;
                    }
                }
                break;
            }
        }
        boolean flag = true;
        Dibujar dibujar = new Dibujar();
        while(flag){
            System.out.println("\nMenu Principal");
            System.out.println("Escoja el numero de opcion que desea: ");
            System.out.println("1.Graficar Estado de memoria");
            System.out.println("2.Generacion de imagenes");
            System.out.println("3.Crear usuario");
            System.out.println("4.Elimianr Usuario");
            System.out.println("5.Modificar Usuario");
            System.out.println("6.salir");
            String opcion = scanner.nextLine();
            switch (opcion){
                case "1":{
                    opcionDibujar(dibujar);
                    break;
                }
                case "2":{
                    
                }
                case "6":{
                    flag = false;
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
    private static void opcionDibujar(Dibujar dibujar){
        OUTER:            
        while (true) {
            System.out.println("\nGraficar Estado");
            System.out.println("Escoja el numero de opcion");
            System.out.println("1.Ver lista de imagenes");
            System.out.println("2.Ver arbol de capas");
            System.out.println("3.Ver capa");
            System.out.println("4.Ver imagen y arbol de capas");
            System.out.println("5.Ver arbol de usuarios");
            System.out.println("6.Regresar");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    dibujar.listaImagenes(almacenamiento.getImagenes().getEstado());
                    break OUTER;
                case "2":
                    dibujar.verTreeCapas(almacenamiento.getCapas().getEstado("capas"));
                    break OUTER;
                case "3":
                    break OUTER;
                case "4":
                    break OUTER;
                case "5":
                    dibujar.verTreeUser(almacenamiento.getUsuarios().getEstado("users"));
                    break OUTER;
                case "6":
                    break OUTER;   
                default:
                    break;
            }
        }
    }
    
    private static String converterString(File file){
        try{
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuffer text = new StringBuffer();
            String read;
            while((read=br.readLine())!=null){
               text.append(read).append("\n");
            }
            return text.toString();
        }catch(Exception e){            
        }
        return null;
    }
    
}
