/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bilheteria.model;

import java.io.File;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Aluno
 */
public class pegaLetra 
{
    String pen = "";
    
    public String pegaLetra()
    {
        String pasta = "P1";

        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = File.listRoots();
        File fDrive;

        for(File f : roots)
        {
          String displayName = fsv.getSystemDisplayName(f);

          if(displayName.indexOf(pasta) != -1)
          {   
             int pos = displayName.indexOf("("); 
             pen =  displayName.substring(pos + 1, pos + 2);
             System.out.println(displayName.substring(pos + 1, pos + 2));

          }
        }
        return pen;

    }
}
