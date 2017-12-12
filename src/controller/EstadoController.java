package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.DaoEstado;
import model.Estado;

@ManagedBean
public class EstadoController {

	private List<Estado> estados;
	
	public EstadoController(){
		estados = new ArrayList<Estado>();
		getAll();
	}
	
	public void getAll(){
		DaoEstado dao = new DaoEstado();
		estados = dao.findAll();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
