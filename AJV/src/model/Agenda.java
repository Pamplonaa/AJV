package model;


import java.io.Serializable;
import java.util.ArrayList;



/**
 * @author Pizetta
 * @version 1.0
 * @created 27-set-2020 10:21:39
 */
public class Agenda  implements Serializable{

	private int disciplinaId;
	private ArrayList<Integer> equipeIds;
	private ArrayList<Integer> horarios;

	public Agenda(){
            this.equipeIds = new ArrayList<Integer>();
            this.horarios = new ArrayList<Integer>();
	}

	public void finalize() throws Throwable {

	}
}//end Agenda