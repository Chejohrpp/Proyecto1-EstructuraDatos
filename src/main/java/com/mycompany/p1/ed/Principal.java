/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed;

import com.mycompany.p1.ed.Informacion.Almacenamiento;
import com.mycompany.p1.ed.Nodos.NodeAVL;
import com.mycompany.p1.ed.listas.ListSimple;
import com.mycompany.p1.ed.objetos.Capa;
import com.mycompany.p1.ed.objetos.Imagen;
import com.mycompany.p1.ed.objetos.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
                    if (ext.equals("usr")) {                        
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
            System.out.println("2.Generar una imagen");
            System.out.println("3.Crear usuario");
            System.out.println("4.Eliminar Usuario");
            System.out.println("5.Modificar Usuario");
            System.out.println("6.Agregar una nueva imagen");
            System.out.println("7.Eliminar una imagen");
            System.out.println("7.salir");
            String opcion = scanner.nextLine();
            switch (opcion){
                case "1":{
                    opcionDibujar(dibujar);
                    break;
                }
                case "2":{
                    System.out.println("Escriba el nombre de la imagen");
                    String img = scanner.nextLine();
                    Imagen im = almacenamiento.getImagenes().buscar(img);
                    if (im != null) {
                        dibujar.generarImg(im.getImgen(almacenamiento.getCapas()),im.getId());
                    }else{
                        System.out.println("No existe la imagen");
                    }
                    break;
                }
                case "3":{
                    System.out.println("Ingrese el nombre del usuario: ");                    
                    String userName = scanner.nextLine();
                    NodeAVL node = almacenamiento.getUsuarios().find(opcion);
                    if (node != null) {
                        System.out.println("El usuario " + userName + " ya existe");
                        break;
                    }
                    Usuario nuevo = new Usuario();
                    nuevo.setId(userName);
                    ListSimple lista = new ListSimple();
                    while(true){
                        System.out.println("Ingrese el codigo de una imagen para agregarlo con el usuario\n O escirba null para salir");
                        String op = scanner.nextLine();
                        if (!op.equalsIgnoreCase("null")) {
                            Imagen img = almacenamiento.getImagenes().buscar(op);
                            if (img != null) {
                                lista.add(op);
                            }else{
                                System.out.println("No existe la imagen escrita");
                            }
                        }else{
                            break;
                        }                        
                    }
                    nuevo.setImagenes(lista);
                    almacenamiento.getUsuarios().add(userName, nuevo);
                    break;
                }
                case "4":{
                    System.out.println("Escriba el codigo a eliminar: ");
                    String idUser = scanner.nextLine();
                    NodeAVL nodeUser = almacenamiento.getUsuarios().find(idUser);
                    if (nodeUser != null) {
                        almacenamiento.getUsuarios().remove(idUser);
                        System.out.println("Codigo Eliminado");
                    }else{
                        System.out.println("No existe el usuario " + idUser);
                    }
                    break;
                }
                case "5":{
                    System.out.println("Escriba el codigo de usuario a modificar: ");
                    String userName = scanner.nextLine();
                    NodeAVL node = almacenamiento.getUsuarios().find(userName);
                    if (node == null) {
                        System.out.println("El usuario " + userName + " no existe");
                        break;
                    }                    
                    modUser(node);    
                    break;
                }
                case "7":{
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
                    System.out.println("Ingrese el codigo de la capa: ");
                    String idCapa = scanner.nextLine();
                    NodeAVL nodeCapa = almacenamiento.getCapas().find(idCapa);
                    if (nodeCapa != null) {
                        Capa capa = (Capa) nodeCapa.getObject();
                        dibujar.verMatrizCapa(capa.getMatriz().getEstado("id_capa_"+idCapa), idCapa);
                    }else{
                        System.out.println("No existe la capa: " + idCapa);
                    }
                    break OUTER;
                case "4":
                    System.out.println("Ingrese el codigo de la imagen: ");
                    String idImg = scanner.nextLine();
                    Imagen img = almacenamiento.getImagenes().buscar(idImg);
                    if (img != null) {
                        String treeCapas = almacenamiento.getCapas().getEstado("capas");
                        dibujar.VerImagenTreeCapas(img.getEstado(treeCapas,"capas", "img_"+idImg), idImg);
                    }else{
                        System.out.println("No existe la imagen: " + idImg);
                    }
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
    private static void modUser(NodeAVL nodeUser){
        boolean cambios = false;
        Usuario user = (Usuario) nodeUser.getObject();
        System.out.println("Elija la opcion que desea hacer con el usuario: ");
        OUTER:
        while(true){
            System.out.println("1.Modificar el userName");
            System.out.println("2.Modificar Lista de imagenes");
            System.out.println("3.Regresar");
            String opcion = scanner.nextLine();
            switch(opcion){
                case"1":{
                    System.out.println("Escriba el nuevo nombre de usuario: ");
                    String userName = scanner.nextLine();
                    if (!verificarUser(userName)) {
                        user.setId(userName);
                        cambios = true;
                    }else{
                        System.out.println("el nombre de usuario no esta disponible");
                        break;
                    }
                    break;
                }
                case"2":{
                    System.out.println("\n1.Insertar imagen");
                    System.out.println("2.Eliminar imagen");
                    String op = scanner.nextLine();
                    System.out.println("Esrciba el codigo de la imagen");
                    String im = scanner.nextLine();
                        switch(op){
                            case "1":{                                
                                Imagen img = almacenamiento.getImagenes().buscar(im);
                                if (img != null) {
                                    if (!user.getImagenes().verificarId(im)) {
                                        user.getImagenes().add(im);
                                        System.out.println("Se agrego la imagen");
                                    }else{
                                        System.out.println("Esta imagen ya esta agregada a la lista del usuario");
                                    }                                    
                                }else{
                                    System.out.println("No existe la imagen escrita");
                                }
                                break;
                            }case "2":{
                                if (user.getImagenes().eliminar(im)) {
                                    System.out.println("se elimino la imagen");
                                }else{
                                    System.out.println("No se encontro la imagen");
                                }
                                break;
                            } default:{
                                break;
                            }
                        }
                        break;
                }
                case "3":{
                    if (cambios) {
                        almacenamiento.getUsuarios().Modificar(nodeUser.getId(),user.getId(),user);
                    }
                    break OUTER;
                }
            }
        }      
        
    }
    private static boolean verificarUser(String id){
        NodeAVL node = almacenamiento.getUsuarios().find(id);
        if (node != null) {
            return true;
        }
        return false;
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
