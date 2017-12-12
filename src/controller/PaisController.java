package controller;

import javax.faces.bean.ManagedBean;

import dao.DaoPais;
import model.Pais;

@ManagedBean
public class PaisController {

	private Pais pais;
	private Pais aux;
	
	public PaisController(){
		pais = new Pais();
		aux = new Pais();
	}
	
	public String salvarPais(){
		
		DaoPais dao = new DaoPais();
		dao.save(pais);
		pais = new Pais();	
		
		return "index?faces-redirect=true";
	}
	
	public String removerPais(){
		
		DaoPais dao = new DaoPais();
		pais = dao.findById(pais.getId());
		dao.remove(pais);
		pais = new Pais();
		
		return "index?faces-redirect=true";
	}
	
	public String editarPais(){
		
		DaoPais dao = new DaoPais();
		pais = dao.findById(pais.getId());
		pais.setNome(aux.getNome());
		dao.update(pais);
		pais = new Pais();
		aux = new Pais();
		
		return "index?faces-redirect=true";
	}
	
	public String paginaCadastro(){
		
		return "cadastro?faces-redirect=true";
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Pais getAux() {
		return aux;
	}

	public void setAux(Pais aux) {
		this.aux = aux;
	}
}
