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
    
    public void abreTelaInicial(String tipoDeAcesso){
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setVisible(Boolean.TRUE);
        telaInicial.exibeItensNaTela(tipoDeAcesso);
    }
    
}
