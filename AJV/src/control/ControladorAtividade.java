/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JOptionPane;
import model.Atividade;
import view.TelaCriarAtividade;
import view.TelaListarAtividades;
import view.TelaEditarAtividade;
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
    private final TelaListarAtividades telaListarAtividades;
    private final TelaEditarAtividade telaEditarAtividade;
    private Atividade atividade;

    private ControladorAtividade() {
        telaCriarAtividade = new TelaCriarAtividade();
        telaListarAtividades = new TelaListarAtividades();
        telaEditarAtividade = new TelaEditarAtividade();
        atividade = new Atividade();
    }

    public static synchronized ControladorAtividade getInstance() {
        if (instance == null) {
            instance = new ControladorAtividade();
        }
        return instance;
    }

    public void criarAtividade() throws NoSuchAlgorithmException {
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        String randomId = Integer.toString(prng.nextInt());
        String titulo = telaCriarAtividade.tituloAtividade();
        String descricao = telaCriarAtividade.descricao();
        String prazo = telaCriarAtividade.prazoEntrega();
        int participantes = telaCriarAtividade.numParticipantes();
        
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(telaCriarAtividade, "Informe um título válido");
        } else {
            Boolean encontrou = false;

            AtividadeDao atividadeDao = AtividadeDao.getInstance();
            encontrou = atividadeDao.existeAtividade(randomId, titulo);

            Atividade newAtividade = new Atividade();
            newAtividade.setTitulo(titulo);
            newAtividade.setDescricao(descricao);
            newAtividade.setPrazoEntrega(prazo);
            newAtividade.setNumeroParticipantesGrupo(participantes);
            newAtividade.setAtividadeId(Integer.parseInt(randomId));
            atividadeDao.put(newAtividade);
            JOptionPane.showMessageDialog(telaCriarAtividade, "Atividade criada com sucesso");
            telaCriarAtividade.setVisible(Boolean.FALSE);
        }
    }

    public void editarAtividade() throws NoSuchAlgorithmException {
        Integer idAtividade = telaListarAtividades.idAtividade();

        String titulo = telaEditarAtividade.tituloAtividade();
        String descricao = telaEditarAtividade.descricao();
        String prazo = telaEditarAtividade.prazoEntrega();
        int num = telaEditarAtividade.numParticipantes();
        int participantes = telaEditarAtividade.numParticipantes();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(telaEditarAtividade, "Informe um título válido");
        } else {
            AtividadeDao atividadeDao = AtividadeDao.getInstance();
            Atividade newAtividade = new Atividade();

            newAtividade.setTitulo(titulo);
            newAtividade.setDescricao(descricao);
            newAtividade.setPrazoEntrega(prazo);
            newAtividade.setNumeroParticipantesGrupo(participantes);
            newAtividade.setAtividadeId(idAtividade);

            atividadeDao.update(newAtividade);

            JOptionPane.showMessageDialog(telaEditarAtividade, "Atividade atualizada com sucesso");
            telaEditarAtividade.setVisible(Boolean.FALSE);
        }
    }

    public void removerAtividade() throws NoSuchAlgorithmException {
        Integer idAtividade = telaListarAtividades.idAtividade();
        if (idAtividade == 0) {
            JOptionPane.showMessageDialog(telaListarAtividades, "Informe um id válido");
        } else {
            AtividadeDao atividadeDao = AtividadeDao.getInstance();
            String mensagem = atividadeDao.removeById(idAtividade);

            JOptionPane.showMessageDialog(telaCriarAtividade, mensagem);
            telaCriarAtividade.setVisible(Boolean.FALSE);
        }
    }

    public void exibeTelaCriarAtividade() {
        telaCriarAtividade.setLocationRelativeTo(null);
        telaCriarAtividade.setVisible(Boolean.TRUE);
    }

    public void fechaTelaCriarAtividade() {
        telaCriarAtividade.setVisible(Boolean.FALSE);
    }

    public void exibeTelaListarAtividades() {
        telaListarAtividades.setListaAtividades();
        telaListarAtividades.setLocationRelativeTo(null);
        telaListarAtividades.setVisible(Boolean.TRUE);
    }

    public void fechaTelaListarAtividades() {
        telaListarAtividades.setVisible(Boolean.FALSE);
    }

    public Collection<Atividade> getAtividades() {
        return AtividadeDao.getInstance().list();
    }

    public void exibeTelaEditarAtividade() {
        Integer idAtividade = telaListarAtividades.idAtividade();

        Atividade newAtividade;

        AtividadeDao atividadeDao = AtividadeDao.getInstance();
        newAtividade = atividadeDao.get(idAtividade);

        boolean populou = telaEditarAtividade.popularAtividade(newAtividade);

        if (populou == true) {
            telaEditarAtividade.setLocationRelativeTo(null);
            telaEditarAtividade.setVisible(Boolean.TRUE);
        }
    }

    public void fechaTelaEditarAtividade() {
        telaEditarAtividade.setVisible(Boolean.FALSE);
    }

}
