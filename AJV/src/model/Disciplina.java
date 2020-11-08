package model;

import java.io.Serializable;
import java.util.ArrayList;



/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:47
 */
public class Disciplina  implements Serializable{

	private Agenda agenda;
	private int disciplinaId;
	private ArrayList<Aluno> alunos;
	public Professor m_Professor;

	public Disciplina(){
           this.alunos = new ArrayList<>(); 
	}

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getM_Professor() {
        return m_Professor;
    }

    public void setM_Professor(Professor m_Professor) {
        this.m_Professor = m_Professor;
    }
        
}//end Disciplina