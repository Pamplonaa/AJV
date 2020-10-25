/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JOptionPane;
import model.Atividade;
import view.TelaCriarAtividade;
import dao.AtividadeDao;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Pizetta
 */
public class ControladorAtividade {

    private static ControladorAtividade instance;
    private final TelaCriarAtividade telaCriarAtividade;
    private Atividade atividade;

    private ControladorAtividade() {
        telaCriarAtividade = new TelaCriarAtividade();
        atividade = new Atividade();
    }

    public static synchronized ControladorAtividade getInstance() {
        if (instance == null) {
            instance = new ControladorAtividade();
        }
        return instance;
    }

    public void exibeTelaCriarAtividade() {
        telaCriarAtividade.setLocationRelativeTo(null);
        telaCriarAtividade.setVisible(Boolean.TRUE);
    }

    public void criarAtividade() throws NoSuchAlgorithmException {
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        String randomId = Integer.toString(prng.nextInt());
        String titulo = telaCriarAtividade.tituloAtividade();
        int participantes = telaCriarAtividade.numParticipantes();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(telaCriarAtividade, "Informe um título válido");
        } else {
            Boolean encontrou = false;

            AtividadeDao atividadeDao = AtividadeDao.getInstance();
            encontrou = atividadeDao.existeAtividade(randomId, titulo);

            Atividade newAtividade = new Atividade();
            newAtividade.setTitulo(titulo);
            newAtividade.setNumeroParticipantesGrupo(participantes);
            newAtividade.setAtividadeId(Integer.parseInt(randomId));
            atividadeDao.put(newAtividade);
            JOptionPane.showMessageDialog(telaCriarAtividade, "Atividade criada com sucesso");
            telaCriarAtividade.setVisible(Boolean.FALSE);
        }
    }

    public void fechaTelaCriarAtividade() {
        telaCriarAtividade.setVisible(Boolean.FALSE);
    }
    
    public Collection<Atividade> getAtividades(){
        return AtividadeDao.getInstance().list();
    }
}
