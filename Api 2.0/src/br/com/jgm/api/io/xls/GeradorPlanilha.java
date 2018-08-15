package br.com.jgm.api.io.xls;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

public abstract class GeradorPlanilha {

	
	private String[] colunas_header;
	private HSSFWorkbook wb;
	private HSSFSheet st_atual;
	private Object[]  lista;
	private String caminho_arquivo;
	private CellStyle style , header_style;
	
	public GeradorPlanilha(String[] colunas_header, String caminho_arquivo, Object[] lista){
		this.colunas_header = colunas_header;
		this.wb = new HSSFWorkbook();
		this.setLista(lista);
		this.caminho_arquivo = caminho_arquivo;
		
		CellStyle style = wb.createCellStyle();

		Font font = wb.createFont(); 
		font.setFontName("Calibri"); 
		font.setFontHeightInPoints((short) 10); 

		style.setFont(font);
		
		this.style = wb.createCellStyle();;
		this.style.setFont(font);

		Font header_font = wb.createFont(); 
		header_font.setFontName("Calibri"); 
		header_font.setFontHeightInPoints((short) 10); 
		header_font.setBoldweight(Font.BOLDWEIGHT_BOLD); 
		
		this.header_style = wb.createCellStyle();
		this.header_style.setFont(header_font);
		
	}
	
	public void gerarPlanilha(){ 
		st_atual  = wb.createSheet();
		int rowCount = 0;
		gerarHeader(st_atual.createRow(rowCount++));
		
		for(Object obj : getLista()){
			Row row = st_atual.createRow(rowCount++);
			Object[] dados = retornaArrayDeDados(obj);
			preencheRowComArray(row, dados);
		}
		
		FileOutputStream fos;
		File plan = new File(caminho_arquivo);
		try {
			fos = new FileOutputStream(plan);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract Object[] retornaArrayDeDados(Object obj);
	
	protected void preencheRowComArray(Row row, Object[] dados){
		for (int i = 0 ; i < dados.length; i++) {
			preencheCell(row.createCell(i), dados[i]);
		}
	}
	
	protected void preencheCell(Cell cell, Object conteudo){
		String conteudo_celula = "";
		if(cell.getRowIndex() == 0)
			cell.setCellStyle(header_style);
		else
			cell.setCellStyle(style);
		
		if(conteudo instanceof String){
			cell.setCellValue((String)conteudo);
			conteudo_celula = (String)conteudo;
		}else if(conteudo instanceof Double){
			cell.setCellValue((Double)conteudo);
			conteudo_celula = Double.toString((Double) conteudo);
		}else if(conteudo instanceof Integer){
			cell.setCellValue((Integer)conteudo);
			conteudo_celula = Integer.toString((Integer)conteudo);
		}
		
		int tamanho_atual_coluna = st_atual.getColumnWidth(cell.getColumnIndex());
		if(conteudo_celula.length() * 295 > tamanho_atual_coluna){
			try{
				st_atual.setColumnWidth(cell.getColumnIndex(), conteudo_celula.length() * 295);
			}catch(Exception e){
				System.out.println("Tamanho maior doq o permitido");
			}
		}
	}

	private void gerarHeader(Row row) {
		int col_count = 0;
		for(int i = 0 ; i < colunas_header.length; i++){
			preencheCell(row.createCell(col_count++), colunas_header[i]);
		}
	}

	public Object[] getLista() {
		return lista;
	}

	public void setLista(Object[] lista) {
		this.lista = lista;
	}
	
	
}
