
package com.vendenet.negocio.entidad;

public class BasicBean implements Comparable {


	private int id;
	private String name;
	
	/**
	 * 
	 */
	public BasicBean(){
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Object o) {
		if(o!=null){
			if(this.id==((BasicBean)o).getId())return 0;
			else return 1;
		}else return 1;
	}
	
	@Override
	 public boolean equals(Object o) {
		return (this.id == ((BasicBean)o).getId());
	}
	
}
