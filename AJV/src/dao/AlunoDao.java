/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import model.Aluno;

/**
 *
 * @author jcmartins81
 */
public class AlunoDao {
    
    private static AlunoDao instance;
    private String filename = "alunos.cla";
    private HashMap<Integer, Aluno> cacheAlunos;
    
    public static AlunoDao getInstance() {
        
        if(instance == null) {
            return instance = new AlunoDao();
        }
        return instance;
    }
    
     private AlunoDao() {
        this.cacheAlunos = new HashMap<>();
        load();
    }
    
    public void put(Aluno aluno) {
        //falta testar se é nulo
        cacheAlunos.put(aluno.getId(), aluno);
        persist();
    }

    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheAlunos);

            oo.flush();
            fout.flush();

            oo.close();
            fout.close();
            oo = null;
            fout = null;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public Boolean existeAluno(String id, String senha){
        Aluno achouAluno = get(Integer.parseInt(id));
        if(achouAluno == null || achouAluno.getSenha() == senha) {
            return false;
        } else {
            return true;                
        }
    }

    public Aluno get(Integer id) {
        return cacheAlunos.get(id);
    }    

    public Collection list() {
        return cacheAlunos.values();
    }

    private void load() {
        try{
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheAlunos = (HashMap<Integer, Aluno>) oi.readObject();
            
            oi.close();
            fin.close();
            oi = null;
            fin = null;
            
        }catch(ClassNotFoundException ex){
            System.out.println(ex);
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public void remove(Aluno aluno){
        if(aluno != null){
            cacheAlunos.remove(aluno.getId());
        }
    }
}
