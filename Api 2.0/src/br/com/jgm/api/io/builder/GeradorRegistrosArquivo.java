package br.com.jgm.api.io.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import br.com.jgm.api.io.CampoLeiaute;
import br.com.jgm.api.io.registro.Registro;




public abstract class GeradorRegistrosArquivo {

	private List<CampoLeiaute> campos_layout;
	
	public GeradorRegistrosArquivo(){	
		this.campos_layout = gerarCamposLayout();
	}
	
	public abstract List<CampoLeiaute> gerarCamposLayout();
	
	public  Registro gerarRegistro(Object obj){
		return registroPorTipo(obj , campos_layout);
	}
	
	public abstract Registro registroPorTipo(Object obj, List<CampoLeiaute> campos_layout);
	
	public List<Registro> gerarRegistroPorLayout(Object[] array) throws InvalidPasswordException, IOException{

		ArrayList<Registro> registros = new ArrayList<>();
		for(int i = 0; i < array.length; i++){

			Registro registro = gerarRegistro(array[i]);

			registros.add(registro);
		}
		
		return registros;
	}
}
