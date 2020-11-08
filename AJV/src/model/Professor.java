package model;

import java.io.Serializable;

/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:49
 */
public class Professor  implements Serializable{

        private static final long serialVersionUID = 1L;
	private Agenda agenda;
	private Disciplina disciplina;
	private String nome;
	private int professorId;
        private String senha;

    /**
     *
     */
    public Professor(){
        this.agenda = new Agenda();
        this.disciplina = new Disciplina();
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}//end Professor