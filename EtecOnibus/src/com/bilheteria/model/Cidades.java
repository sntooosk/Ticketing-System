/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bilheteria.model;

/**
 *
 * @author Juliano
 */
public class Cidades {
    
    public int id;
    public String nome;
    
    public Cidades(int ide, String nomeCidade)
    {
        id = ide;
        nome = nomeCidade;
    }
    
     @Override  
     public String toString(){  
        return nome; 
    }
}
