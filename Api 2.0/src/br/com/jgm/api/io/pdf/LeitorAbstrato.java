package br.com.jgm.api.io.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public abstract class LeitorAbstrato {
	
	private String[] conteudo;
	private String pathFile;
	private SimpleDateFormat spdf;
	
	public LeitorAbstrato(){
		this.setSpdf(new SimpleDateFormat("dd/MM/yyyy"));
	}
	
	protected String retornaMemoPorArrayeIndex(int inicio , int fim, String[] dados){

		StringBuilder sbMemo = new StringBuilder();
		
		while(inicio <= fim){
			sbMemo.append(dados[inicio++] + " ");
		}
		
		return sbMemo.toString().trim();

	}


	public void mapear_arquivo(String filePath){
		try {
			this.conteudo = retornaContudoStringDePDf(filePath);
			for(int i = 0; i < this.conteudo.length; i++){
				logica_por_linha(i,this.conteudo[i].trim());
			}
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void logica_por_linha(int index_linha_conteudo, String linha);

	public String[] retornaContudoStringDePDf(String filePath) throws IOException, InvalidPasswordException {
		Path path = Paths.get(filePath);
		byte[] data = Files.readAllBytes(path);
	
		PDDocument load = PDDocument.load(data);
		
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String conteudo = pdfStripper.getText(load);
		load.close();
		
		return conteudo.split("\n");
		
	}
	
	
//Metodo utilizado por mais de um leitor.
//	protected String retornaDataLinha(String linha){
//		int init = 0;
//
//		boolean achouData = false;
//		String data = "";
//		while(!achouData && init+10 < linha.length()){
//			data = linha.substring(init,init+10);
//
//			try{
//				spdf.parse(data.trim());
//				if(StringUtils.stringDataValida(data))
//					break;
//				else
//					init++;
//			}catch(Exception e){
//				
//				init++;	
//				continue;
//			
//			}
//		
//		}
//		
//		return data;
//	}
	
	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(String[] conteudo) {
		this.conteudo = conteudo;
	}


	public SimpleDateFormat getSpdf() {
		return spdf;
	}

	public void setSpdf(SimpleDateFormat spdf) {
		this.spdf = spdf;
	}
}
