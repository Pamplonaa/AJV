package model;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jcmartins81
 */
public class Aluno implements Serializable{

        private static final long serialVersionUID = 1L;
	private int equipeId;
	private int id;
	private String nome;
        private String senha;

    public Aluno() {
        
    }

    public int getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(int equipeId) {
        this.equipeId = equipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }        
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
