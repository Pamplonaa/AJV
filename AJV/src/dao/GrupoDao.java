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
import model.Grupo;

import java.util.UUID;

/**
 *
 * @author Augusto Pamplona
 */
public class GrupoDao {

    private static GrupoDao instance;
    private String filename = "grupos.cla";
    private HashMap<Integer, Grupo> cacheGrupos;

    public static GrupoDao getInstance() {

        if (instance == null) {
            return instance = new GrupoDao();
        }
        return instance;
    }

    public GrupoDao() {
        this.cacheGrupos = new HashMap<>();
        load();
    }

    public void put(Grupo grupo) {
        //falta testar se é nulo
        cacheGrupos.put(grupo.getGrupoId(), grupo);
        persist();
    }

    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheGrupos);

            oo.flush();
            fout.flush();

            oo.close();
            fout.close();
            oo = null;
            fout = null;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Boolean existeGrupo(String titulo) {
        return this.cacheGrupos.containsValue(titulo);
    }

    public Grupo get(Integer id) {
        return cacheGrupos.get(id);
    }

    public Collection list() {
        return cacheGrupos.values();
    }

    public void load() {
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);

            this.cacheGrupos = (HashMap<Integer, Grupo>) oi.readObject();

            oi.close();
            fin.close();
            oi = null;
            fin = null;

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void remove(Grupo grupo) {
        if (grupo != null) {
            cacheGrupos.remove(grupo.getGrupoId());
        }
    }

    public void update(Grupo grupo) {
        cacheGrupos.get(grupo.getGrupoId()).setTitulo(grupo.getTitulo());
        persist();
    }
}
