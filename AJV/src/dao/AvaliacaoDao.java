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
import model.Avaliacao;

/**
 *
 * @author Augusto Pamplona
 */
public class AvaliacaoDao {
    private static AvaliacaoDao instance;
    private String filename = "avaliacao.cla";
    private HashMap<Integer, Avaliacao> cacheAvaliacao;
    
    public static AvaliacaoDao getInstance() {
        
        if(instance == null) {
            return instance = new AvaliacaoDao();
        }
        return instance;
    }
    
     public AvaliacaoDao() {
        this.cacheAvaliacao = new HashMap<>();
        load();
    }
    
    public void put(Avaliacao avaliacao) {
        //falta testar se é nulo
        cacheAvaliacao.put(avaliacao.getAvaliacaoId(), avaliacao);
        persist();
    }

    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheAvaliacao);

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
    
    public Avaliacao get(Integer id) {
        return cacheAvaliacao.get(id);
    }    

    public Collection list() {
        return cacheAvaliacao.values();
    }

    public void load() {
        try{
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheAvaliacao = (HashMap<Integer, Avaliacao>) oi.readObject();
            
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
    
    public void remove(Avaliacao avaliacao){
        if(avaliacao != null){
            cacheAvaliacao.remove(avaliacao.getAvaliacaoId());
        }
    }
}
