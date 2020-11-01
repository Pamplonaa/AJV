package control;

import dao.AlunoDao;
import java.util.ArrayList;
import model.Aluno;
import view.ExibirListaAlunos;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collection;
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
    private Aluno alunoSelecionado;
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

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void logaAluno(int id) {
        this.setAlunoLogado(AlunoDao.getInstance().get(id));
        System.out.println(this.getAlunoLogado());
    }

    public void exibeListaAlunos(){
        exibirListaAlunos.setListaAlunos(getAlunos());
        exibirListaAlunos.setLocationRelativeTo(null);
        exibirListaAlunos.setVisible(Boolean.TRUE);
    }
    
    public ArrayList<Aluno> getAlunos() {
        Collection colecao = AlunoDao.getInstance().list();
        ArrayList<Aluno> alunos = new ArrayList<>();

        colecao.forEach(c -> {
            alunos.add((Aluno) c);
        });
        this.alunos = alunos;
        return alunos;
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

        for (Object c : AlunoDao.getInstance().list()) {
            alunos.add((Aluno) c);
        }
        this.alunoSelecionado = alunos.get(index);
        if (ControladorConvite.getInstance().temConvitePendente(alunoSelecionado)) {
            JOptionPane.showMessageDialog(null, "Aluno já foi convidado para o grupo! Aguarde a confirmação!");
        } else {
            if (this.alunoSelecionado.getEquipeId() == 0) {
                ControladorConvite.getInstance().editaConvite(alunoSelecionado);
                
            } else {
                JOptionPane.showMessageDialog(null, "Aluno já está em um grupo!");
            }
        }

    }
    
}