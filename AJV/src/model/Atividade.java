package model;

import java.io.Serializable;

/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:43
 */
public class Atividade  implements Serializable{

    private int atividadeId;
    private Avaliacao avaliacao;
    private int numeroParticipantesGrupo;
    public Professor professorId;
    private String titulo;

    public Atividade() {

    }

    public void finalize() throws Throwable {

    }

    public int getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(int atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getNumeroParticipantesGrupo() {
        return numeroParticipantesGrupo;
    }

    public void setNumeroParticipantesGrupo(int numeroParticipantesGrupo) {
        this.numeroParticipantesGrupo = numeroParticipantesGrupo;
    }

    public Professor getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Professor professorId) {
        this.professorId = professorId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}//end Atividade
