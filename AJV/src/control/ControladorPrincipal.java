/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import view.TelaInicial;

/**
 *
 * @author jcmartins81
 */
public class ControladorPrincipal {

    private static ControladorPrincipal instance;
    private final TelaInicial telaInicial;
    private int usuarioId;
    
    private ControladorPrincipal() {
        telaInicial = new TelaInicial();
    }
    
    public static synchronized ControladorPrincipal getInstance(){
         if(instance == null){
            return instance = new ControladorPrincipal();
        }
        return instance;
    }
    
    public void inicia(){
        ControladorLogin.getInstance().exibeTelaLogin();   
    }
    
    public void abreTelaInicial(String tipoDeAcesso, int id, String nome){
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setTitle("Usuario Logado: " + nome);
        telaInicial.setVisible(Boolean.TRUE);
        telaInicial.exibeItensNaTela(tipoDeAcesso);
        if(tipoDeAcesso.equalsIgnoreCase("aluno")){
            ControladorAluno.getInstance().logaAluno(id);
        }
        this.usuarioId = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
}
