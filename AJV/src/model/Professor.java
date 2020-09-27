package model;

/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:49
 */
public class Professor {

	private Agenda agenda;
	private Disciplina disciplina;
	private String nome;
	private int professorId;
        private String senha;

    /**
     *
     */
    public Professor(){

    }

    public int getAgenda() {
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
        
        

	public void finalize() throws Throwable {

	}
}//end Professor