/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.AvaliarGrupo;

/**
 *
 * @author Augusto Pamplona
 */
public class ControladorAvaliacao {
    
    private static ControladorAvaliacao instance;
    private final AvaliarGrupo avaliarGrupo;
    
    public ControladorAvaliacao(){
        avaliarGrupo = new AvaliarGrupo();
    }
    
    public static synchronized ControladorAvaliacao getInstance(){
         if(instance == null){
            return instance = new ControladorAvaliacao();
        }
        return instance;
    }
    
    public void exibeAvaliarGrupo() {
        avaliarGrupo.setListaGrupos();
        avaliarGrupo.setLocationRelativeTo(null);
        avaliarGrupo.setVisible(Boolean.TRUE);
    }
    
    public void fechaTelaAvaliarGrupo() {
        avaliarGrupo.setVisible(Boolean.FALSE);
    }
    
}
