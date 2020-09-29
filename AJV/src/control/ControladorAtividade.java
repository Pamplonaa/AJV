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

/**
 *
 * @author Pizetta
 */
public class ControladorAtividade {

    private static ControladorAtividade instance;
    private final TelaCriarAtividade telaCriarAtividade;

    private ControladorAtividade() {
        telaCriarAtividade = new TelaCriarAtividade();
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
        String randomId = Integer.valueOf(prng.nextInt()).toString();
        String titulo = telaCriarAtividade.dadosAtividade();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(telaCriarAtividade, "Informe um título válido");
        } else {
            Boolean encontrou = false;

            AtividadeDao atividadeDao = AtividadeDao.getInstance();
            encontrou = atividadeDao.existeAtividade(randomId, titulo);

            Atividade atividade = new Atividade();
            atividade.setTitulo(titulo);
            atividade.setAtividadeId(Integer.parseInt(randomId));
            atividadeDao.put(atividade);
            JOptionPane.showMessageDialog(telaCriarAtividade, "Atividade criada com sucesso");
            telaCriarAtividade.setVisible(Boolean.FALSE);
        }
    }

    public void fechaTelaCriarAtividade() {
        telaCriarAtividade.setVisible(Boolean.FALSE);
    }
}
