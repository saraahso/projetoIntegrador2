package model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="via")
public class Via implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", length = 90, nullable = false)
	private String nome;
	
	@Column(name = "conquistador", length = 90, nullable = false)
	private String conquistador;
	
	@Column(name = "orientacao", length = 500)
	private String orientacao;
	
	@Column
	private boolean ativo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cadastrador_id")
	private Usuario cadastrador;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grau_id")
	private Grau grau;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="setor_id")
	private Setor setor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario_id")
	private Usuario verificado;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="via")
	private Set<Imagem> imagens;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="via")
	private Set<Comentario> comentarios;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="via")
	private Set<Nota> notas;

	@Transient
	private Integer nota;
	
	@Column
	private String url;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConquistador() {
		return conquistador;
	}

	public void setConquistador(String conquistador) {
		this.conquistador = conquistador;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Usuario cadastrador) {
		this.cadastrador = cadastrador;
	}

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Usuario getVerificado() {
		return verificado;
	}

	public void setVerificado(Usuario verificado) {
		this.verificado = verificado;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Imagem> imagens) {
		this.imagens = imagens;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Set<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Set<Nota> notas) {
		this.notas = notas;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Via other = (Via) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
