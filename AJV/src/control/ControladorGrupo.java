/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JOptionPane;
import model.Grupo;
import view.TelaCriarGrupo;
import dao.GrupoDao;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Augusto Pamplona
 */
public class ControladorGrupo {
    
    private static ControladorGrupo instance;
    private final TelaCriarGrupo telaCriarGrupo;
    
    private ControladorGrupo() {
        telaCriarGrupo = new TelaCriarGrupo();
    }
    
    public static synchronized ControladorGrupo getInstance() {
        if(instance == null) {
            return instance = new ControladorGrupo();
        }
        return instance;
    }
    
    public void exibeTelaCriarGrupo() {
        telaCriarGrupo.setLocationRelativeTo(null);
        telaCriarGrupo.setVisible(Boolean.TRUE);
    }
    
    public void criarGrupo() throws NoSuchAlgorithmException {
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        String randomId = Integer.valueOf(prng.nextInt()).toString();
        String titulo = telaCriarGrupo.dadosGrupo();
        if(titulo.isEmpty()){
            JOptionPane.showMessageDialog(telaCriarGrupo, "Informe um título válido");
        } else {
            Boolean encontrou = false;
            
            GrupoDao grupoDao = GrupoDao.getInstance();
            encontrou = grupoDao.existeGrupo(randomId, titulo);
            
            Grupo grupo = new Grupo();
            grupo.setTitulo(titulo);
            grupo.setGrupoId(Integer.parseInt(randomId));
            grupoDao.put(grupo);
            JOptionPane.showMessageDialog(telaCriarGrupo, "Grupo criado com sucesso");
            telaCriarGrupo.setVisible(Boolean.FALSE);
        }
    } 
    
    public void fechaTelaCriarGrupo() {
        telaCriarGrupo.setVisible(Boolean.FALSE);
    }
    
}
