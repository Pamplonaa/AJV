package model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:48
 */
public class Grupo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int grupoId;
    private String titulo;
    public Convite equipeId;
    private ArrayList<Aluno> alunos;
    private Aluno alunoLider;
    public Atividade m_Atividade;

    public Grupo(){

    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Convite getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Convite equipeId) {
        this.equipeId = equipeId;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public Aluno getAlunoLider() {
        return alunoLider;
    }

    public void setAlunoLider(Aluno alunoLider) {
        this.alunoLider = alunoLider;
    }

    public Atividade getM_Atividade() {
        return m_Atividade;
    }

    public void setM_Atividade(Atividade m_Atividade) {
        this.m_Atividade = m_Atividade;
    }
}//end Grupo