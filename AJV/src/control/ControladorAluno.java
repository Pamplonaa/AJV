package control;

import dao.AlunoDao;
import java.util.ArrayList;
import model.Aluno;
import view.ExibirListaAlunos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zemartins81
 */
public class ControladorAluno {
    
    private static ControladorAluno instance;
    private Aluno alunoLogado;
    private ArrayList<Aluno> alunos;
    private ExibirListaAlunos exibirListaAlunos;

    public ControladorAluno() {
        this.alunos = new ArrayList<>();
        this.exibirListaAlunos = new ExibirListaAlunos();
    }
    
    
    
    public static synchronized ControladorAluno getInstance() {
        if(instance == null) {
            return instance = new ControladorAluno();
        }
        return instance;
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
    }

    public void setAlunoLogado(Aluno alunoLogado) {
        this.alunoLogado = alunoLogado;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void logaAluno(int id) {
        this.setAlunoLogado(AlunoDao.getInstance().get(id));
        System.out.println(this.getAlunoLogado());
    }

    public void exibeListaAlunos(){
        exibirListaAlunos.setListaAlunos();
        exibirListaAlunos.setLocationRelativeTo(null);
        exibirListaAlunos.setVisible(Boolean.TRUE);
    }
    
    public void fechaTelaListarGrupos() {
       exibirListaAlunos.setVisible(Boolean.FALSE);
    }

    public void setAlunoLider(Aluno aluno) {
        AlunoDao.getInstance().remove(aluno);
        AlunoDao.getInstance().put(aluno);
        AlunoDao.getInstance().persist();
    }
    
    public void alunoSelecionadoConvite(int index){
        ArrayList<Aluno> alunos = new ArrayList<>();
        
        for(Object c : AlunoDao.getInstance().list()){
            alunos.add((Aluno) c);
        }
        Aluno alunoSelecionado = alunos.get(index);
        ControladorConvite.getInstance().editaConvite(alunoSelecionado);
    }
    
}
