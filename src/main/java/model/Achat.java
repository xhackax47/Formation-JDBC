package model;

public class Achat {
	int id;
	int idclient;
	int idbook;

	public Achat(int id, int idclient, int idbook) {
		super();
		this.id = id;
		this.idclient = idclient;
		this.idbook = idbook;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public int getIdbook() {
		return idbook;
	}
	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}
	
	
}
