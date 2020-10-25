package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import model.Professor;

/**
 *
 * @author jcmartins81
 */
public class ProfessorDao {
    
    private static ProfessorDao instance;
    private String filename = "professores.cla";
    private HashMap<Integer, Professor> cacheProfessors;
    
    public static ProfessorDao getInstance() {
        
        if(instance == null) {
            return instance = new ProfessorDao();
        }
        return instance;
    }
    
     private ProfessorDao() {
        this.cacheProfessors = new HashMap<>();
        load();
    }
    
    public void put(Professor professor) {
        //falta testar se é nulo
        cacheProfessors.put(professor.getProfessorId(), professor);
        persist();
    }

    public void persist() {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheProfessors);

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
    
    public Boolean existeProfessor(String id, String senha){
        Professor achouProfessor = get(Integer.parseInt(id));
        return !(achouProfessor == null || achouProfessor.getSenha() == senha);
    }

    public Professor get(Integer id) {
        return cacheProfessors.get(id);
    }    

    public Collection list() {
        return cacheProfessors.values();
    }

    private void load() {
        try{
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheProfessors = (HashMap<Integer, Professor>) oi.readObject();
            
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
    
    public void remove(Professor professor){
        if(professor != null){
            cacheProfessors.remove(professor.getProfessorId());
        }
    }
}