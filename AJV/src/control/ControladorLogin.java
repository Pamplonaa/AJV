/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AlunoDao;
import dao.ProfessorDao;
import javax.swing.JOptionPane;
import model.Disciplina;
import model.Professor;
import model.Agenda;
import view.TelaLogin;
/**
 *
 * @author jcmartins81
 */
public class ControladorLogin {

   private static ControladorLogin instance;
    private final TelaLogin telaLogin;

    private ControladorLogin(){
        telaLogin = new TelaLogin();
    }

    public static synchronized ControladorLogin getInstance(){
        if(instance == null){
            return instance = new ControladorLogin();
        }
        return instance;
    }

    public void exibeTelaLogin(){
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(Boolean.TRUE);
    }

    public void login() {
        String login[] = telaLogin.dadosLogin();
        if("Aluno".equals(login[2])){
            Boolean encontrou = false;
           AlunoDao aluno = AlunoDao.getInstance();
           encontrou = aluno.existeAluno(login[0], login[1]);
            if(!encontrou){
            JOptionPane.showMessageDialog(telaLogin, "Login inválido");
            }else {
                ControladorPrincipal.getInstance().abreTelaInicial("aluno");
            }
        }else{
            Boolean encontrou = false;
           ProfessorDao professor = ProfessorDao.getInstance();
           encontrou = professor.existeProfessor(login[0], login[1]);
            if(!encontrou){
                JOptionPane.showMessageDialog(telaLogin, "Login inválido");            
            }else {
                ControladorPrincipal.getInstance().abreTelaInicial("professor");
            }
        }
    }

    public void fechaTelaLogin() {
        telaLogin.setVisible(Boolean.FALSE);
    }
    
}
