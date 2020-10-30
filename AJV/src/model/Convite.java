package model;

import java.io.Serializable;



/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:46
 */
public class Convite  implements Serializable{

        private static final long serialVersionUID = 1L;
	private Aluno convidado;
	private String mensagem;
        private int equipeId;
        private int conviteId;

	public Convite(){

	}

    public int getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(int equipeId) {
        this.equipeId = equipeId;
    }

    public Aluno getConvidado() {
        return convidado;
    }

    public void setConvidado(Aluno convidado) {
        this.convidado = convidado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getConviteId() {
        return conviteId;
    }

    public void setConviteId(int conviteId) {
        this.conviteId = conviteId;
    }

    
    

}//end Convite