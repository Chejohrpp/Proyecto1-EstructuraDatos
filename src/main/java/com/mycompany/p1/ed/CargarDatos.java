/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1.ed;

import com.mycompany.p1.ed.Informacion.Almacenamiento;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author sergi
 */
public class CargarDatos {
    
    private JFrame frame;
    public CargarDatos() {
        frame = new JFrame();
        frame.setVisible(true);
        BringToFront();
    }
    public File[] getFile() {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)){
            //frame.setVisible(false);
            frame.dispose();
            return fc.getSelectedFiles();                        
        }else {
            frame.dispose();
        }
        return null;
    }

    private void BringToFront() {                  
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);

    }
    
}
