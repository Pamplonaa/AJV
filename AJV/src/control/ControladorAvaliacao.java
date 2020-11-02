/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AvaliacaoDao;
import dao.GrupoDao;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import model.Avaliacao;
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
    
    public void criarAvaliacao() throws NoSuchAlgorithmException {
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        String randomId = Integer.toString(prng.nextInt());
        String comentario = avaliarGrupo.comentarioAvaliacao();
        Integer grupoId = avaliarGrupo.idGrupo();
        Double nota = avaliarGrupo.nota();
        
        GrupoDao grupoDao = GrupoDao.getInstance();
        
        if(grupoId == null || grupoDao.get(grupoId) == null) {
            JOptionPane.showMessageDialog(avaliarGrupo, "Informe um ID de grupo válido");
        } else {
            if(this.verificaSeGrupoFoiAvaliado(grupoId) == true) {
                JOptionPane.showMessageDialog(avaliarGrupo, "Grupo selecionado já foi avaliado");
                avaliarGrupo.setVisible(Boolean.FALSE);
            } else{
            
                AvaliacaoDao avaliacaoDao = AvaliacaoDao.getInstance();

                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setAvaliacaoId(Integer.parseInt(randomId));
                avaliacao.setComentario(comentario);
                avaliacao.setNota(nota);
                avaliacao.setIdGrupo(grupoId);

                avaliacaoDao.put(avaliacao);
                JOptionPane.showMessageDialog(avaliarGrupo, "Grupo avaliado com sucesso");
                avaliarGrupo.setVisible(Boolean.FALSE);
            }
        }
        
        
    }
    
    public boolean verificaSeGrupoFoiAvaliado(int grupoId){
        Collection colecao = AvaliacaoDao.getInstance().list();
        ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
        
        for(Object c : colecao){
            avaliacoes.add((Avaliacao) c);
        }
        int i;
        int n = avaliacoes.size();
        for(i = 0; i < n; i++){
            if(avaliacoes.get(i).getIdGrupo() == grupoId){
                return true;
            }
        }
        return false;
    }
}
