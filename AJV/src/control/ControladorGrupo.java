/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AlunoDao;
import javax.swing.JOptionPane;
import model.Grupo;
import view.TelaCriarGrupo;
import dao.GrupoDao;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import view.TelaListarGrupos;

/**
 *
 * @author Augusto Pamplona
 */
public class ControladorGrupo {
    
    private static ControladorGrupo instance;
    private final TelaCriarGrupo telaCriarGrupo;
    private final TelaListarGrupos telaListarGrupos;
    
    private ControladorGrupo() {
        telaCriarGrupo = new TelaCriarGrupo();
        telaListarGrupos = new TelaListarGrupos();
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
//            encontrou = grupoDao.existeGrupo(titulo);
//            System.out.println(encontrou);
            
            Grupo grupo = new Grupo();
            
            grupo.setTitulo(titulo);
            grupo.setGrupoId(Integer.parseInt(randomId));
            grupo.setAlunoLider(AlunoDao.getInstance().get(ControladorPrincipal.getInstance().getUsuarioId()));

            grupoDao.put(grupo);
            JOptionPane.showMessageDialog(telaCriarGrupo, "Grupo criado com sucesso");
            telaCriarGrupo.setVisible(Boolean.FALSE);
        }
    } 
    
    public void fechaTelaCriarGrupo() {
        telaCriarGrupo.setListaGrupos();
        telaCriarGrupo.setVisible(Boolean.FALSE);
    }

    public void exibeTelaListarGrupos(){
        telaListarGrupos.setListaGrupos();
        telaListarGrupos.setLocationRelativeTo(null);
        telaListarGrupos.setVisible(Boolean.TRUE);
    }
    
    public void fechaTelaListarGrupos() {
       telaListarGrupos.setVisible(Boolean.FALSE);
    }
    
    public ArrayList<Grupo> collectionToArrayList(Collection colecao){
        ArrayList<Grupo> grupos = new ArrayList<>();
        
        for(Object c : colecao){
            grupos.add((Grupo) c);
        }
        
        return grupos;
    }

    public ArrayList<Grupo> retornaGrupos(){
        return this.collectionToArrayList(GrupoDao.getInstance().list());
    }
    
}
