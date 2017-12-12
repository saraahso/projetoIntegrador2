package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DaoUsuario;
import model.Usuario;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario userLogado;
	private boolean logado;
	
	public LoginController(){
		userLogado = new Usuario();
		logado = false;
	}
	
	public void logar(){
		
		DaoUsuario dao = new DaoUsuario();
		MD5 md = new MD5();
		System.out.println(userLogado.getEmail() + "--------" + userLogado.getSenha());
		userLogado = dao.checkLogin(userLogado.getEmail(), md.MD5M(userLogado.getSenha()));
		if(userLogado != null){
			if(!userLogado.getNome().equals("")){
				logado = true;
			}else{
				userLogado = new Usuario();
			}
		}else{
			userLogado = new Usuario();
		}
		
	}
	
	public String index(){
		
		return "/paginas/index?faces-redirect=true";
	}
	
	public void teste(){
		DaoUsuario dao = new DaoUsuario();
		MD5 md = new MD5();
		userLogado = dao.checkLogin("faahd", "202cb962ac59075b964b07152d234b70");
		userLogado.setSenha(md.MD5M("123"));
		System.out.println("Login ---> " + userLogado.getEmail() + " |||| Senha ---> " + userLogado.getSenha());
	}
	
	public String deslogar(){
		userLogado = new Usuario();
		logado = false;
		
		return "/paginas/index?faces-redirect=true";
	}

	public Usuario getUserLogado() {
		return userLogado;
	}

	public void setUserLogado(Usuario userLogado) {
		this.userLogado = userLogado;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
}
