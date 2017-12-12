package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.DaoGrau;
import dao.DaoVia;
import model.Grau;
import model.Via;

@ManagedBean
public class ViaController {

	private Via via;
	private List<Grau> graus;
	
	public ViaController(){
		graus = new ArrayList<Grau>();
		via = new Via();
		getGrausLista();
	}
	
	private void getGrausLista() {
		DaoGrau dao = new DaoGrau();
		graus = dao.findAll();
	}

	public String paginaCadastro(){
		return "viaCadastro?faces-redirect=true";
	}
	
	public String cadastrarVia(){
		DaoVia dao = new DaoVia();
		dao.save(via);
		via = new Via();
		
		return "index?faces-redirect=true";
	}
	
	public String removerVia(){
		DaoVia dao = new DaoVia();
		via = dao.findById(via.getId());
		dao.remove(via);
		via = new Via();
		
		return "index?faces-redirect=true";
	}
	
	public void editarVia(){
		DaoVia dao = new DaoVia();
		via = dao.findById(via.getId());		
	}
	
	public void atualizarVia(){
		DaoVia dao = new DaoVia();
		dao.update(via);
	}
	
	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public List<Grau> getGraus() {
		return graus;
	}

	public void setGraus(List<Grau> graus) {
		this.graus = graus;
	}
}
