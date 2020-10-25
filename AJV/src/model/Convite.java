package model;

import java.io.Serializable;



/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:46
 */
public class Convite  implements Serializable{

	private Aluno convidado;
	private String mensagem;

	public Convite(){

	}

	public void finalize() throws Throwable {

	}
}//end Convite