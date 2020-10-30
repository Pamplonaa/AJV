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
import model.Convite;

/**
 *
 * @author jcmartins81
 */
public class ConviteDao {
    
    private static ConviteDao instance;
    private String filename = "convites.cla";
    private HashMap<Integer, Convite> cacheConvites;
    
    public static ConviteDao getInstance() {
        
        if(instance == null) {
            return instance = new ConviteDao();
        }
        return instance;
    }
    
     private ConviteDao() {
        this.cacheConvites = new HashMap<>();
        load();
    }
    
    public void put(Convite convite) {
        //falta testar se é nulo
        cacheConvites.put(convite.getConviteId(), convite);
        persist();
    }

    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheConvites);

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
    
    public Boolean existeConvite(String id){
        Convite achouConvite = get(Integer.parseInt(id));
        return !(achouConvite == null);
    }

    public Convite get(Integer id) {
        return cacheConvites.get(id);
    }    

    public Collection list() {
        return cacheConvites.values();
    }

    private void load() {
        try{
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheConvites = (HashMap<Integer, Convite>) oi.readObject();
            
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
    
    public void remove(Convite convite){
        if(convite != null){
            cacheConvites.remove(convite.getConviteId());
        }
    }
}

