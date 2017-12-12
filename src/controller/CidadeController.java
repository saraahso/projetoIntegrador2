package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.DaoCidade;
import model.Cidade;

@ManagedBean
public class CidadeController {

	private Cidade cidade;
	private List<Cidade> lista;
	
	public CidadeController(){
		cidade = new Cidade();
		lista = new ArrayList<Cidade>();
		getCidades();
	}

	public String cadastrarCidade(){
		
		DaoCidade dao = new DaoCidade();
		dao.save(cidade);
		
		cidade = new Cidade();
		
		return "index?faces-redirect=true";
	}
	
	public String removerCidade(){
		DaoCidade dao = new DaoCidade();
		
		cidade = dao.findById(cidade.getId());
		
		if(cidade.getSetores() == null || cidade.getSetores().isEmpty()){
			dao.remove(cidade);
		}
		
		
		return "index?faces-redirect=true";
	}
	
	public void getCidades(){
		
		DaoCidade dao = new DaoCidade();
		lista = dao.findAll();
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getLista() {
		return lista;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}
}
