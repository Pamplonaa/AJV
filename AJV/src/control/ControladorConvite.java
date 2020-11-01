/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.ConvidarAlunoGrupo;
import model.Aluno;
import model.Convite;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import dao.ConviteDao;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
/**
 *
 * @author zemartins81
 */
public class ControladorConvite {
    
    private static ControladorConvite instance;
    private ConvidarAlunoGrupo telaEditaConvite; 
    private Aluno alunoConvidado;

    public ControladorConvite() {
        this.telaEditaConvite = new ConvidarAlunoGrupo();
    }
    
    

    public static synchronized ControladorConvite getInstance() {
        if(instance == null) {
            return instance = new ControladorConvite();
        }
        return instance;
    }  
    
    public void editaConvite(Aluno aluno) {
        this.alunoConvidado = aluno;
        
        telaEditaConvite.jtfNomeConvidadoSetText(alunoConvidado.getNome());
        telaEditaConvite.setVisible(true);
    }
    
    public ArrayList<Convite> collectionToArrayList(Collection colecao){
        ArrayList<Convite> convites = new ArrayList<>();
        
        for(Object c : colecao){
            convites.add((Convite) c);
        }
        
        return convites;
    }
    
    public ArrayList<Convite> retornaConvites(){
       return this.collectionToArrayList(ConviteDao.getInstance().list());
    }
    
    public boolean temConvitePendente(Aluno aluno) {
        this.alunoConvidado = aluno;
        Integer idDoGrupo = ControladorAluno.getInstance().getAlunoLogado().getEquipeId();
        System.out.println(idDoGrupo);
        
        for(Convite convite : this.retornaConvites()){
            if(convite.getConvidado().getId() == this.alunoConvidado.getId() && convite.getEquipeId() == idDoGrupo){
                return true;
            }
        }
       return false;
    }
    
    public void encaminhaConvite(String textoDaMensagem ) throws NoSuchAlgorithmException{
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        String randomId = Integer.valueOf(prng.nextInt()).toString();
        
        Convite convite = new Convite();
        convite.setConviteId(Integer.parseInt(randomId));
        convite.setMensagem(textoDaMensagem);
        convite.setConvidado(alunoConvidado);
        convite.setEquipeId(ControladorAluno.getInstance().getAlunoLogado().getEquipeId());
        
        ConviteDao.getInstance().put(convite);
    }
}