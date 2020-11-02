package model;

import java.io.Serializable;



/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:44
 */
public class Avaliacao  implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String comentario;
    private int id;
    private double nota;
    private int idGrupo;

    public Avaliacao(){

    }
    
    public int getAvaliacaoId(){
        return this.id;
    }
    public void setAvaliacaoId(int avaliacaoId) {
        this.id = avaliacaoId;
    }
    public String getComentario(){
        return comentario;
    }
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    public void setNota(double nota){
        this.nota = nota;
    }
    public double getNota(){
        return this.nota;
    }
    public int getIdGrupo(){
        return this.idGrupo;
    }
    public void setIdGrupo(int idGrupo){
        this.idGrupo = idGrupo;
    }
}//end Avaliacao��o