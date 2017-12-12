package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.DaoTipoUsuario;
import dao.DaoUsuario;
import model.Usuario;

@ManagedBean
public class UsuarioController {

	private Usuario usuario;
	private Usuario aux;
	private List<Usuario> users;

	public UsuarioController(){
		usuario = new Usuario();
		aux = new Usuario();
		users = new ArrayList<Usuario>();
	}
	
	public String cadastrarUsuario(){
		
		DaoUsuario dao = new DaoUsuario();
		MD5 md = new MD5();
		usuario.setSenha(md.MD5M(usuario.getSenha()));
		usuario.setAtivo(true);
		usuario.setTipoGrau(1);
		
		DaoTipoUsuario daoTpu = new DaoTipoUsuario();
		usuario.setTipoUsuario(daoTpu.findById(1));
		
		dao.save(usuario);
		
		usuario = new Usuario();
		
		return "index?faces-redirect=true";
	}
	
	public String atualizarUsuario(){
		DaoUsuario dao = new DaoUsuario();

		dao.update(usuario);
		
		usuario = new Usuario();
		
		return "index?faces-redirect=true";
	}

	public String desativarUsuario(){
		DaoUsuario dao = new DaoUsuario();
		usuario = dao.findById(usuario.getId());
		usuario.setAtivo(false);
		
		usuario = new Usuario();
		return "index?faces-redirect=true";
	}
	
	public String alterarPermissao(){
		DaoUsuario dao = new DaoUsuario();
		dao.update(usuario);
		
		usuario = new Usuario();

		return "index?faces-redirect=true";
	}

	public void editarUsuario(){
		DaoUsuario dao = new DaoUsuario();
		usuario = dao.findById(usuario.getId());
		//fazer verificacao p/ ver se vai atualizar o cadastro ou 
		//alterar a permissao de usuario, e entao redirecionar de acordo.
	}
	
	public void getAll(){
		DaoUsuario dao = new DaoUsuario();
		users = dao.findAll();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getAux() {
		return aux;
	}

	public void setAux(Usuario aux) {
		this.aux = aux;
	}

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
	
}
