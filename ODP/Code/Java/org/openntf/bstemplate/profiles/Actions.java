package org.openntf.bstemplate.profiles;

import java.io.Serializable;

public class Actions implements Serializable {

	private static final long serialVersionUID = -5341113919851408973L;
	
	public Actions(){
		
	}
	
	public void addNoNetwork(){
		System.out.println("added to network");
	}

}
