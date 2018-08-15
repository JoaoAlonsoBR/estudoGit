package br.com.jgm.api.io.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


public abstract class LeitorPlanilha {

	private HSSFWorkbook wb;
	private SheetController sheetController;

	
	public LeitorPlanilha(File arquivoPlanilha) throws FileNotFoundException, IOException{
		this.wb = new HSSFWorkbook(new FileInputStream(arquivoPlanilha));
	}

	public void selecionarSheet(int sheet_index){
		this.setSheetController(new SheetController(wb.getSheetAt(sheet_index)));
	}
	
	public void selecionarSheet(String sheet_name){
		this.setSheetController(new SheetController(wb.getSheet(sheet_name)));
	}
	
	public final void iterarPelaSheet(){
		if(getSheetController() == null)
			throw new RuntimeException("Não foi selecionada nenhuma aba");
		while(getSheetController().nextRow()){
			logicaPorRow(getSheetController().getRowAtual());
		}
	}

	protected abstract void logicaPorRow(Row rowAtual);

	public SheetController getSheetController() {
		return sheetController;
	}

	public void setSheetController(SheetController sheetController) {
		this.sheetController = sheetController;
	}
}
