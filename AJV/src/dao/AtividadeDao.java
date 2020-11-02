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
import javax.swing.JOptionPane;
import model.Atividade;

/**
 *
 * @author Pizetta
 */
public class AtividadeDao {

    private static AtividadeDao instance;
    private final String filename = "atividades.cla";
    private HashMap<Integer, Atividade> cacheAtividades;

    public static AtividadeDao getInstance() {
        if (instance == null) {
            return instance = new AtividadeDao();
        }
        return instance;
    }

    private AtividadeDao() {
        this.cacheAtividades = new HashMap<>();
        load();
    }

    public void put(Atividade atividade) {
        //falta testar se é nulo
        cacheAtividades.put(atividade.getAtividadeId(), atividade);
        persist();
    }

    public void update(Atividade atividade) {
        cacheAtividades.get(atividade.getAtividadeId()).setTitulo(atividade.getTitulo());
        cacheAtividades.get(atividade.getAtividadeId()).setDescricao(atividade.getDescricao());
        cacheAtividades.get(atividade.getAtividadeId()).setPrazoEntrega(atividade.getPrazoEntrega());
        cacheAtividades.get(atividade.getAtividadeId()).setNumeroParticipantesGrupo(atividade.getNumeroParticipantesGrupo());
        persist();
    }

    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheAtividades);

            oo.flush();
            fout.flush();

            oo.close();
            fout.close();
            oo = null;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Boolean existeAtividade(String atividadeId, String titulo) {
        Atividade atividade = get(Integer.parseInt(atividadeId));
        return !(atividade == null);
    }

    public Atividade get(Integer id) {
        return cacheAtividades.get(id);
    }

    public Collection list() {
        return cacheAtividades.values();
    }

    private void load() {
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);

            this.cacheAtividades = (HashMap<Integer, Atividade>) oi.readObject();

            oi.close();
            fin.close();
            oi = null;

        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex);
        }
    }

    public void remove(Atividade atividade) {
        if (atividade != null) {
            cacheAtividades.remove(atividade.getAtividadeId());
        }
    }

    public String removeById(Integer id) {
        cacheAtividades.remove(id);
        persist();
        return "Removido com sucesso";
    }
}
