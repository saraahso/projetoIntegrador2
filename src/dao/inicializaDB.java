package dao;

import model.Cidade;
import model.Estado;
import model.Grau;
import model.Pais;
import model.Setor;
import model.TipoUsuario;
import model.Via;

public class inicializaDB {

	public static void main(String[] args) {	
		inicializaGrau();
		inicializaTipoUsuario();
		inicializaLocais();
	}

	private static void inicializaGrau() {
		DaoGrau dao = new DaoGrau();
		
		Grau grau = new Grau();
		grau.setBra("V");
		grau.setEua("5");
		grau.setFra("VI");		
		dao.save(grau);
		
		grau = new Grau();
		grau.setBra("VI");
		grau.setEua("6");
		grau.setFra("VIsup");
		dao.save(grau);
		
		grau = new Grau();
		grau.setBra("VII");
		grau.setEua("6");
		grau.setFra("VIsup");
		dao.save(grau);
		
		grau = new Grau();
		grau.setBra("VIII");
		grau.setEua("6");
		grau.setFra("VIsup");
		dao.save(grau);
		
		grau = new Grau();
		grau.setBra("IX");
		grau.setEua("6");
		grau.setFra("VIsup");
		dao.save(grau);
	}
	
	private static void inicializaTipoUsuario() {
		DaoTipoUsuario dao = new DaoTipoUsuario();
		
		TipoUsuario tp = new TipoUsuario();
		tp.setNome("Usuario");
		tp.setDescricao("UsuÃ¡rio comum do sistema...");
		dao.save(tp);
		
		tp = new TipoUsuario();
		tp.setNome("Moderador");
		tp.setDescricao("Quem faz o gerenciamento das vias de acordo com a localizaÃ§Ã£o...");
		dao.save(tp);
		
		tp = new TipoUsuario();
		tp.setNome("Administrador");
		tp.setDescricao("AdministraÃ§Ã£o do site...");
		dao.save(tp);
		
	}
	
	private static void inicializaLocais() {
		DaoEstado dao = new DaoEstado();
		DaoPais daoP = new DaoPais();
		DaoCidade daoC = new DaoCidade();
		DaoSetor daoS = new DaoSetor();
		DaoVia daoV = new DaoVia();
		DaoGrau daog = new DaoGrau();
		
		Pais pais = new Pais();
		pais.setNome("Argentina");
		daoP.save(pais);
		
			Estado estado = new Estado();
			estado.setNome("Bariloche");
			estado.setPais(pais);
			dao.save(estado);
			
			estado = new Estado();
			estado.setNome("Patagonia");
			estado.setPais(pais);
			dao.save(estado);
		
		pais = new Pais();
		pais.setNome("Brasil");
		daoP.save(pais);
		
			estado = new Estado();
			estado.setNome("Parana");
			estado.setPais(pais);
			dao.save(estado);
			
				Cidade cidade = new Cidade();
				cidade.setNome("Cascavel");
				cidade.setEstado(estado);
				daoC.save(cidade);
				
					Setor setor = new Setor();
					setor.setCidade(cidade);
					setor.setNome("Espaço Verde Oliva");
					setor.setEquipamento("Cordas 25m+");
					setor.setPercurso("Seguir a sinalização");
					setor.setLocal("Bairro Azul");
					setor.setLatitude(-24.84d);
					setor.setLongitude(-54.32d);
					daoS.save(setor);
						
						Via via = new Via();
						via.setAtivo(true);
						via.setNome("Macaco Azul");
						via.setSetor(setor);
						via.setOrientacao("Cuidado com pedras soltas");
						via.setConquistador("Evandro Cantu");
						via.setGrau(daog.findById(1));
						daoV.save(via);
						
						via = new Via();
						via.setAtivo(true);
						via.setNome("Diabo é o pai do Rock");
						via.setSetor(setor);
						via.setOrientacao("Cuidado com rochas lisas");
						via.setConquistador("Faahd Solano");
						via.setGrau(daog.findById(2));
						daoV.save(via);
				
				cidade = new Cidade();
				cidade.setNome("Foz do Iguaçu");
				cidade.setEstado(estado);
				daoC.save(cidade);
				
					setor = new Setor();
					setor.setCidade(cidade);
					setor.setNome("Canion do Iguaçu");
					setor.setEquipamento("Cordas 50m, 13 costuras e repelente");
					setor.setPercurso("Marcar horário com o parque nacional e seguir a sinalização do parque.");
					setor.setLocal("Avenida das cataratas");
					setor.setLatitude(-25.00d);
					setor.setLongitude(-53.99d);
					daoS.save(setor);
					
						via = new Via();
						via.setAtivo(true);
						via.setNome("Toca da onça");
						via.setSetor(setor);
						via.setOrientacao("Via tranquila");
						via.setConquistador("Julio Cesar");
						via.setUrl("toca.jpg");
						via.setGrau(daog.findById(3));
						daoV.save(via);
						
						via = new Via();
						via.setAtivo(true);
						via.setNome("Granito chuvoso");
						via.setSetor(setor);
						via.setOrientacao("Via muito escorregadia, usar magnésio.");
						via.setConquistador("Mauricio Pedroso");
						via.setUrl("canion.jpg");
						via.setGrau(daog.findById(4));
						daoV.save(via);
				
			estado = new Estado();
			estado.setNome("Tocantins");
			estado.setPais(pais);
			dao.save(estado);
			
				cidade = new Cidade();
				cidade.setNome("Palmas");
				cidade.setEstado(estado);
				daoC.save(cidade);
				
			estado = new Estado();
			estado.setNome("Rio de Janeiro");
			estado.setPais(pais);
			dao.save(estado);
				
				cidade = new Cidade();
				cidade.setNome("Niterói");
				cidade.setEstado(estado);
				daoC.save(cidade);
				
					setor = new Setor();
					setor.setCidade(cidade);
					setor.setNome("Agulha Guarischi");
					setor.setEquipamento("Cordas 30m, 10 costuras e repelente");
					setor.setPercurso("Seguir pela estrada com pinheiros até o fim.");
					setor.setLocal("Itacoatiara");
					setor.setLatitude(-22.9035d);
					setor.setLongitude(-43.2096d);
					daoS.save(setor);
					
						via = new Via();
						via.setAtivo(true);
						via.setNome("Eldorado");
						via.setSetor(setor);
						via.setOrientacao("Na metade da via existem bons pontos para descanso.");
						via.setConquistador("Mario Rejout");
						via.setUrl("rio01.jpg");
						via.setGrau(daog.findById(5));
						daoV.save(via);
						
						via = new Via();
						via.setAtivo(true);
						via.setNome("Estela Vulcanis");
						via.setSetor(setor);
						via.setOrientacao("Via simples a partir dos 4m.");
						via.setConquistador("Mario Rejout");
						via.setUrl("rio02.jpg");
						via.setGrau(daog.findById(2));
						daoV.save(via);
				
				cidade = new Cidade();
				cidade.setNome("Rio de Janeiro");
				cidade.setEstado(estado);
				daoC.save(cidade);
				
					setor = new Setor();
					setor.setCidade(cidade);
					setor.setNome("Dona Marta");
					setor.setEquipamento("Cordas 15m, 6 costuras, magnésio líquido e repelente");
					setor.setPercurso("Seguir as placas até o setor.");
					setor.setLocal("Cosme Velho");
					setor.setLatitude(-22.9035d);
					setor.setLongitude(-43.2096d);
					daoS.save(setor);
					
						via = new Via();
						via.setAtivo(true);
						via.setNome("Primeiro de Abril");
						via.setSetor(setor);
						via.setOrientacao("Cuidado com pedras soltas!");
						via.setConquistador("Pedro Amaral");
						via.setUrl("rioc01.jpg");
						via.setGrau(daog.findById(4));
						daoV.save(via);
						
						via = new Via();
						via.setAtivo(true);
						via.setNome("30 de julho");
						via.setSetor(setor);
						via.setOrientacao("Via tranquila.");
						via.setConquistador("Marcos Gazzoti");
						via.setUrl("rioc02.jpg");
						via.setGrau(daog.findById(1));
						daoV.save(via);
					
				cidade = new Cidade();
				cidade.setNome("Nova Friburgo");
				cidade.setEstado(estado);
				daoC.save(cidade);
						
						
	}
	
}
