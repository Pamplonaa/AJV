/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

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
    
}
