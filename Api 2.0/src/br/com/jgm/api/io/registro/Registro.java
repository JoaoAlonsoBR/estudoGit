package br.com.jgm.api.io.registro;

import java.util.HashMap;
import java.util.List;

import br.com.jgm.api.io.CampoLeiaute;




public abstract class Registro {

	protected List<CampoLeiaute> camposRegistros;
	protected Object dadosObj;
	private HashMap<String, CampoLeiaute> camposPorNomeCampo;
	private List<Registro> registroFilho;
	
	public Registro(Object dadosObj, List<CampoLeiaute> camposRegistro, List<Registro> registroFilho){
		
		this.camposRegistros = camposRegistro;
		this.dadosObj = dadosObj;
		this.setCamposPorNomeCampo(new HashMap<String, CampoLeiaute>());
		gerarHashCamposPorNomeCampo();
		this.setRegistroFilho(registroFilho);
		
	}
	
	private void gerarHashCamposPorNomeCampo() {

		setCamposPorNomeCampo(new HashMap<String, CampoLeiaute>());
		for(CampoLeiaute campo :  camposRegistros){
			getCamposPorNomeCampo().put(campo.getNomeCampo(), campo);
		}
		popularCampoLeiaute();
		
	}
	
	public abstract void popularCampoLeiaute();
	
	protected void preencheCampo(String nomeCampo, String conteudoCampo) {
		this.getCamposPorNomeCampo().get(nomeCampo).setConteudoCampo(conteudoCampo);
	}

	public List<CampoLeiaute> getCamposRegistros() {
		return camposRegistros;
	}
	public void setCamposRegistros(List<CampoLeiaute> camposRegistros) {
		this.camposRegistros = camposRegistros;
	}

	public HashMap<String, CampoLeiaute> getCamposPorNomeCampo() {
		return camposPorNomeCampo;
	}

	public void setCamposPorNomeCampo(HashMap<String, CampoLeiaute> camposPorNomeCampo) {
		this.camposPorNomeCampo = camposPorNomeCampo;
	}

	public List<Registro> getRegistroFilho() {
		return registroFilho;
	}

	public void setRegistroFilho(List<Registro> registroFilho) {
		this.registroFilho = registroFilho;
	}
	
}
