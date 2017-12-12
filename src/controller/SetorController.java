package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RateEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import dao.DaoCidade;
import dao.DaoEstado;
import dao.DaoPais;
import dao.DaoSetor;
import dao.DaoUsuario;
import dao.DaoVia;
import model.Cidade;
import model.Estado;
import model.Nota;
import model.Pais;
import model.Setor;
import model.Via;

@ManagedBean(name = "setorController")
@SessionScoped
public class SetorController {

	private Pais pais;
	private Estado estado;
	private Cidade cidade;
	private Setor setor;
	private Via via;
	private MapModel geoModel;
	private String centerGeoMap = "-25, -54";
	private Marker marker;
	private List<Setor> setores;
	private List<Pais> paises;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Via> viasPorSetor;
	private Boolean paisBool;
	private Boolean estadoBool;
	private Boolean cidadeBool;
	private Boolean trocaTela;
	private Nota nota;
	private Integer userLogado;
	private String url;
	private File img;
	
	public SetorController(){
		setores = new ArrayList<Setor>();
		paises = new ArrayList<Pais>();
		estados = new ArrayList<Estado>();
		cidades = new ArrayList<Cidade>();
		viasPorSetor = new ArrayList<Via>();
		setor = new Setor();
		pais = new Pais();
		estado = new Estado();
		cidade = new Cidade();
		via = new Via();
		nota = new Nota();
		
		trocaTela = false;		
		paisBool = true;
		estadoBool = true;
		cidadeBool = true;
		geoModel = new DefaultMapModel();
		getPaisesLista();
		getSetoresLista();
		getEstadosLista();
		getCidadesLista();

		getMarkers();
	}
	
	public void getSetoresLista() {
		DaoSetor dao = new DaoSetor();
		setores = dao.findAll();
	}

	public void getPaisesLista(){
		DaoPais dao = new DaoPais();
		paises = dao.findAll();
	}
	
	public void getEstadosLista(){
		DaoEstado dao = new DaoEstado();
		estados = dao.findAll();
	}
	
	public void getCidadesLista(){
		DaoCidade dao = new DaoCidade();
		cidades = dao.findAll();
	}
	
	public String map() throws IOException{
		
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		ec.redirect(ec.getRequestContextPath() + "/maps.html");
		getMarkers();
		
		return "map?faces-redirect=true";
	}
	
	public String cadastrarSetor(){
		//System.out.println(setor.getCidade().getNome() + "Eh nois mano!");
		DaoSetor dao = new DaoSetor();
		System.out.println(cidade.getNome() + "--" + cidade.getId());
		setor.setCidade(cidade);
		dao.save(setor);
		setor = new Setor();
		confirmarCidade();
		
		return "/paginas/index?faces-redirect=true";
	}
	
	public String cadastrarVia() throws IOException{

		DaoVia dao = new DaoVia();
		DaoUsuario daoU = new DaoUsuario();
		via.setCadastrador(daoU.findById(userLogado));
		via.setSetor(setor);
		
		dao.save(via);
		
		FileUtils.moveFile(
				 FileUtils.getFile("C:\\Users\\Faahd\\workspace\\PI2\\WebContent\\images\\"+url), 
			     FileUtils.getFile("C:\\Users\\Faahd\\workspace\\PI2\\WebContent\\images\\"+via.getId()+"_"+url)
		 );
		
//		Imagem img = new Imagem();
//		img.setNome("C:\\Users\\Faahd Solano\\eclipse-workspace\\PI2\\WebContent\\images\\"+via.getId()+" - "+url);
		via.setUrl(via.getId()+"_"+url);
		dao.update(via);
//		MUDAR ESSA CLASSE DE IMAGEM E A CLASSE VIA, APENAS 1 FOTO FTW
		
//		FALTA SÓ VINCULAR ESSA FOTO AO CADASTRO DA VIA DAI ESSA PARTE JÁ FICA GGWP
//		FALTA VINCULAR O CADASTRADOR PARA O SETOR TBM
//		FALTA FAZER A VALIDAÇÃO DOS CAMPOS
//		FALTA FAZER A ATIVAÇÃO DE VIAS PELOS ADM, MAS PRA ISSO TEM Q TER GERENCIAR PERFIL
		
		via = new Via();
		//ResourceBundle.clearCache(Thread.currentThread().getContextClassLoader());
		return "/paginas/index?faces-redirect=true";
	}
	
	public String removerSetor(){
		
		DaoSetor dao = new DaoSetor();
		setor = dao.findById(setor.getId());
		
		if(setor.getVias() == null || setor.getVias().isEmpty()){
			dao.remove(setor);		
		}
		
		setor = new Setor();
		
		return "index?faces-redirect=true";
	}
	
	public String editarSetor(){
		DaoSetor dao = new DaoSetor();
		setor = dao.findById(setor.getId());
		
		return "index?faces-redirect=true";
	}
	
	public String atualizarSetor(){
		DaoSetor dao = new DaoSetor();
		dao.update(setor);
		setor = new Setor();

		return "index?faces-redirect=true";
	}
	
	public String cadastrarSetorPagina(){
		
		setor = new Setor();
		
		return "/paginas/cadastroSetor.xhtml?faces-redirect=true";
	}
	
	public void confirmarCidade(){
		if(trocaTela == false) {
			trocaTela = true;
		}else {
			trocaTela = false;
			pais = new Pais();
			estado = new Estado();
			cidade = new Cidade();			
			setor = new Setor();
			
			estadoBool = true;
			paisBool = true;
			cidadeBool = true;		
		}
	}
	
	public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
        System.out.println(results.get(0));
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }

	public void getMarkers(){
		DaoSetor dao = new DaoSetor();
		List<Setor> setores = dao.findAll();
		
		for(Setor obj: setores){
			if(obj.getLatitude() != null && obj.getLongitude() != null){
				LatLng coord1 = new LatLng(obj.getLatitude(), obj.getLongitude());
				Marker mrk = new Marker(coord1, obj.getNome(), obj.getId());
				//geoModel.addOverlay(new Marker(coord1, obj.getNome()));
				geoModel.addOverlay(mrk);
			}
		}
	}
	
	public void addMarker() {
        Marker marker = new Marker(new LatLng(setor.getLatitude(), setor.getLongitude()), setor.getNome());
        marker.setId("mrk");
        geoModel.addOverlay(marker);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + 
        setor.getLatitude() + ", Lng:" + setor.getLongitude()));
    }
	
	public void onMarkerSelect(OverlaySelectEvent event){
		marker = (Marker) event.getOverlay();
		
		System.out.println(marker.getData());
		 
//		return "index?faces-redirect=true";
	}
	
	public String goSetor() {
		
		if(!viasPorSetor.isEmpty()) {
			viasPorSetor = new ArrayList<Via>();
		}
		
		DaoVia daoV = new DaoVia();
		DaoSetor dao = new DaoSetor();
		setor = dao.findById(Integer.parseInt(marker.getData().toString()));
		
		viasPorSetor.addAll(setor.getVias());
		
		for(Via obj: viasPorSetor) {			
			String notaString = daoV.getNotaDB(obj.getId());
			
			if(notaString != null) {
				Double notax = Double.parseDouble(notaString);
				notax = (double) Math.round(notax);
				
				obj.setNota(notax.intValue());
			}else {
				obj.setNota(0);
			}
			
		}				
		
		return "/paginas/setor?faces-redirect=true";
	}
	
	public String goSetorFromSetores() {
		
		if(!viasPorSetor.isEmpty()) {
			viasPorSetor = new ArrayList<Via>();
		}
		
		DaoVia daoV = new DaoVia();
		
		viasPorSetor.addAll(setor.getVias());
		
		for(Via obj: viasPorSetor) {			
			String notaString = daoV.getNotaDB(obj.getId());
			
			if(notaString != null) {
				Double notax = Double.parseDouble(notaString);
				notax = (double) Math.round(notax);
				
				obj.setNota(notax.intValue());
			}else {
				obj.setNota(0);
			}
			
		}				
		
		return "/paginas/setor?faces-redirect=true";
	}
	
	public String goVia() throws IOException {
		System.out.println(via.getUrl() + "!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(via.getUrl() + "!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		url = via.getUrl();
//		if(via.getUrl() != null && !via.getUrl().isEmpty()) {
//			//URL url = new URL("file:///" + via.getUrl());
//			img = new File(via.getUrl());
//			//FileUtils.copyURLToFile(url, img);
//		}else {
//			img = null;
//		}
		
		return "viaInfo?faces-redirect=true";
	}
	
	public void upload(FileUploadEvent event) throws IOException{
        
        UploadedFile uploadedFile = event.getFile();
        
        Path folder = Paths.get("C:\\Users\\Faahd\\workspace\\PI2\\WebContent\\images");
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName()); 
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);
        url = file.getFileName().toString();
        
        try (InputStream input = uploadedFile.getInputstream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }
        
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);		
	}

	 public void onrate(RateEvent rateEvent) {
//		DaoNota dao = new DaoNota();
//		Nota n = dao.getNotaExist(via.getId(), nota.getUsuario().getId());
//		
//		if(n != null) {
//			n.setNota((Integer) rateEvent.getRating());
//			n.setUsuario(nota.getUsuario());
//			n.setVia(via);
//			dao.update(n);
//		}else {
//			nota.setNota((Integer) rateEvent.getRating());
//			nota.setVia(via);
//			dao.save(nota);
//		} DEU ERRADO PQ N TA VINDO USUARIO DO LOGINCONTROLLER, PROBLEMA CHATO...
		
		 
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Avaliar", "Vocï¿½ votou:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);        
	 }
	     
     public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Avaliar", "Avaliaï¿½ï¿½o resetada");
        FacesContext.getCurrentInstance().addMessage(null, message);
     }
	
	public void onCountryChange() {
       
		if(pais !=null && !pais.getNome().equals("")){
			paisBool = false;
			estadoBool = true;
			cidadeBool = true;
			estados = new ArrayList<Estado>();
        	for(Estado obj: pais.getEstados()){
            	estados.add(obj);
            }
        }
        else{
        	paisBool = true;
        	estadoBool = true;
        	cidadeBool = true;
        	estado = new Estado();
        	cidade = new Cidade();
        	estados = new ArrayList<Estado>();
        }
    }
	
	public void onStateChange() {
		
		if(estado !=null && !estado.getNome().equals("")){
        	estadoBool = false;
        	cidadeBool = true;
			cidades = new ArrayList<Cidade>();
        	for(Cidade obj: estado.getCidades()){
            	cidades.add(obj);
            }
        }
        else{
        	estadoBool = true;
        	cidadeBool = true;
        	cidade = new Cidade();
        	cidades = new ArrayList<Cidade>();
        }
    }
	
	public void onCityChange() {
		if(cidade !=null && !cidade.getNome().equals("")){
			cidadeBool = false;
        }
		else{
			cidadeBool = true;
		}
    }
	
	public String paginaCadastro(){		
		return "mapaCadastro?faces-redirect=true";
	}
	
	public String paginaSetores(){
		getSetoresLista();
		return "/paginas/setores?faces-redirect=true";
	}
	
	public String paginaCadastroVia(){
		via = new Via();
		return "/paginas/viaCadastro?faces-redirect=true";
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Boolean getPaisBool() {
		return paisBool;
	}

	public void setPaisBool(Boolean paisBool) {
		this.paisBool = paisBool;
	}

	public Boolean getEstadoBool() {
		return estadoBool;
	}

	public void setEstadoBool(Boolean estadoBool) {
		this.estadoBool = estadoBool;
	}

	public Boolean getCidadeBool() {
		return cidadeBool;
	}

	public void setCidadeBool(Boolean cidadeBool) {
		this.cidadeBool = cidadeBool;
	}

	public Boolean getTrocaTela() {
		return trocaTela;
	}

	public void setTrocaTela(Boolean trocaTela) {
		this.trocaTela = trocaTela;
	}

	public List<Via> getViasPorSetor() {
		return viasPorSetor;
	}

	public void setViasPorSetor(List<Via> viasPorSetor) {
		this.viasPorSetor = viasPorSetor;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Integer getUserLogado() {
		return userLogado;
	}

	public void setUserLogado(Integer userLogado) {
		this.userLogado = userLogado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

}
