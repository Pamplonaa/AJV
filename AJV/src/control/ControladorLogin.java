/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AlunoDao;
import javax.swing.JOptionPane;
import view.TelaLogin;
/**
 *
 * @author jcmartins81
 */
class ControladorLogin {

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
        if(login[2] == "Aluno"){
           AlunoDao aluno = AlunoDao.getInstance();
           String mensagem = aluno.existeUsuario(login[0], login[1]);
            if(mensagem.equals("")){
            JOptionPane.showMessageDialog(telaLogin, Erros.LOGIN_INVALIDO.getValor());
            }else if(mensagem.equals(Cargos.ADMINISTRADOR.toString())){
                this.fechaTelaLogin();
                ControladorPrincipal.getInstance().carregaTelaPrincipalAdmin();
            }else{
                this.fechaTelaLogin();
                ControladorPrincipal.getInstance().carregaTelaPrincipalFuncionarios();
        }
        }
    }
    
    public void fechaTelaLogin() {
        telaLogin.setVisible(Boolean.FALSE);
    }
    
}
