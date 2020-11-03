/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JOptionPane;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import dao.AlunoDao;
import dao.GrupoDao;
import model.Grupo;
import model.Aluno;
import model.Atividade;
import view.TelaCriarGrupo;
import view.TelaEditarGrupo;
import view.TelaSelecionarGrupoParaEditar;
import view.TelaListarGrupos;

/**
 *
 * @author Augusto Pamplona
 */
public class ControladorGrupo {

    private static ControladorGrupo instance;
    private final TelaCriarGrupo telaCriarGrupo;
    private final TelaListarGrupos telaListarGrupos;
    private final TelaSelecionarGrupoParaEditar telaSelecionarGrupoParaEditar;
    private final TelaEditarGrupo telaEditarGrupo;
    private Grupo grupoSelecionado;
    private int qtdMembrosGrupo;

    private ControladorGrupo() {
        telaCriarGrupo = new TelaCriarGrupo();
        telaListarGrupos = new TelaListarGrupos();
        telaSelecionarGrupoParaEditar = new TelaSelecionarGrupoParaEditar();
        telaEditarGrupo = new TelaEditarGrupo();
        qtdMembrosGrupo = 0;
    }

    public static synchronized ControladorGrupo getInstance() {
        if (instance == null) {
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
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(telaCriarGrupo, "Informe um título válido");
        } else {
            Boolean encontrou = false;

            GrupoDao grupoDao = GrupoDao.getInstance();
//            encontrou = grupoDao.existeGrupo(titulo);
//            System.out.println(encontrou);

            Grupo grupo = new Grupo();

            grupo.setTitulo(titulo);
            grupo.setGrupoId(Integer.parseInt(randomId));
            Aluno lider = AlunoDao.getInstance().get(ControladorPrincipal.getInstance().getUsuarioId());
            lider.setEquipeId(grupo.getGrupoId());
            grupo.setAlunoLider(lider);
            grupo.setAlunos(grupo.getAlunoLider());
            grupoDao.put(grupo);
            ControladorAluno.getInstance().setAlunoLider(lider);
            JOptionPane.showMessageDialog(telaCriarGrupo, "Grupo criado com sucesso");
            telaCriarGrupo.setVisible(Boolean.FALSE);
        }
    }

    public void editarGrupo() throws NoSuchAlgorithmException {
        Integer idAtividade = telaSelecionarGrupoParaEditar.idGrupo();

        String titulo = telaEditarGrupo.tituloAtividade();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(telaEditarGrupo, "Informe um título válido");
        } else {
            GrupoDao grupoDao = GrupoDao.getInstance();
            Grupo newGrupo = new Grupo();

            newGrupo.setTitulo(titulo);
            newGrupo.setGrupoId(idAtividade);

            grupoDao.update(newGrupo);

            JOptionPane.showMessageDialog(telaEditarGrupo, "Grupo atualizado com sucesso");
            telaEditarGrupo.setVisible(Boolean.FALSE);
        }
    }

    public void fechaTelaCriarGrupo() {
        telaCriarGrupo.setListaGrupos();
        telaCriarGrupo.setVisible(Boolean.FALSE);
    }

    public void exibeTelaListarGrupos() {
        telaListarGrupos.setListaGrupos();
        telaListarGrupos.setLocationRelativeTo(null);
        telaListarGrupos.setVisible(Boolean.TRUE);
    }

    public void fechaTelaListarGrupos() {
        telaListarGrupos.setVisible(Boolean.FALSE);
    }

    public void exibeTelaSelecionarGrupoParaEditar() {
        telaSelecionarGrupoParaEditar.setLocationRelativeTo(null);
        telaSelecionarGrupoParaEditar.setVisible(Boolean.TRUE);
    }

    public void fechaTelaSelecionarGrupoParaEditar() {
        telaSelecionarGrupoParaEditar.setListaGrupos();
        telaSelecionarGrupoParaEditar.setVisible(Boolean.FALSE);
    }

    public void exibeTelaEditarGrupo() {
        Integer idGrupo = telaSelecionarGrupoParaEditar.idGrupo();

        Grupo newGrupo;

        GrupoDao grupoDao = GrupoDao.getInstance();
        newGrupo = grupoDao.get(idGrupo);

        boolean populou = telaEditarGrupo.popularGrupo(newGrupo);

        if (populou == true) {
            telaEditarGrupo.setLocationRelativeTo(null);
            telaEditarGrupo.setVisible(Boolean.TRUE);
        }
    }

    public void fechaTelaEditarGrupo() {
        telaEditarGrupo.setVisible(Boolean.FALSE);
    }

    public ArrayList<Grupo> collectionToArrayList(Collection colecao) {
        ArrayList<Grupo> grupos = new ArrayList<>();

        for (Object c : colecao) {
            grupos.add((Grupo) c);
        }

        return grupos;
    }

    public ArrayList<Grupo> retornaGrupos() {
        return this.collectionToArrayList(GrupoDao.getInstance().list());
    }

    public Atividade getAtividade() {
        ArrayList<Atividade> atividades = new ArrayList<>();
        ControladorAtividade.getInstance().getAtividades().forEach(c -> {
            atividades.add((Atividade) c);
        });
        return atividades.get(1);
    }

    public void convidarAluno() {
        Grupo grupoDoAluno = this.getGrupoAluno();

        if (grupoDoAluno.getGrupoId() == 0) {
            JOptionPane.showMessageDialog(null, "Convites permitidos apenas para líderes de Grupos!");
        } else {
            this.qtdMembrosGrupo = getAtividade().getNumeroParticipantesGrupo();
            if (qtdMembrosGrupo > grupoDoAluno.getAlunos().size()) {
                ControladorAluno.getInstance().exibeListaAlunos();
            } else {
                JOptionPane.showMessageDialog(null, "Grupo Atingiu o limite de Alunos permitido!");
            }
        }
    }

    public Grupo getGrupoAluno() {
        for (Grupo grupo : this.retornaGrupos()) {
            if (grupo.getAlunoLider().getId() == ControladorPrincipal.getInstance().getUsuarioId()) {
                return grupo;
            }
        }
        return new Grupo();
    }

    public Grupo grupoSelecionado(int index) {
        ArrayList<Grupo> grupos = new ArrayList<>();

        for (Object c : GrupoDao.getInstance().list()) {
            grupos.add((Grupo) c);
        }
        this.grupoSelecionado = grupos.get(index);
        return this.grupoSelecionado;
    }
}
