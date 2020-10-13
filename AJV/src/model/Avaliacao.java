package model;

import java.io.Serializable;



/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:44
 */
public class Avaliacao  implements Serializable{
    
        private static final long serialVersionUID = 1L;
        
	private String comentario;
	private int id;
	private double nota;

	public Avaliacao(){

	}

	public void finalize() throws Throwable {

	}
}//end Avaliacao��o