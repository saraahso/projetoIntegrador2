package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "data_nascimento")
	private Date dataNasc;

	@Column
	private boolean ativo;

	@Column(name = "tipo_grau")
	private int tipoGrau;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_usuario_id")
	private TipoUsuario tipoUsuario;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pais_id")
	private Pais pais;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="verificado")
	private Set<Via> verificado;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="cadastrador")
	private Set<Via> viasCadastradas;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="cadastrador")
	private Set<Setor> setoresCadastrados;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="usuario")
	private Set<Nota> notas;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="usuario")
	private Set<Comentario> comentarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getTipoGrau() {
		return tipoGrau;
	}

	public void setTipoGrau(int tipoGrau) {
		this.tipoGrau = tipoGrau;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Set<Via> getVerificado() {
		return verificado;
	}

	public void setVerificado(Set<Via> verificado) {
		this.verificado = verificado;
	}

	public Set<Via> getViasCadastradas() {
		return viasCadastradas;
	}

	public void setViasCadastradas(Set<Via> viasCadastradas) {
		this.viasCadastradas = viasCadastradas;
	}

	public Set<Setor> getSetoresCadastrados() {
		return setoresCadastrados;
	}

	public void setSetoresCadastrados(Set<Setor> setoresCadastrados) {
		this.setoresCadastrados = setoresCadastrados;
	}

	public Set<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Set<Nota> notas) {
		this.notas = notas;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
