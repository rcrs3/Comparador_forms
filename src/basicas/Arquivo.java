package basicas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Arquivo {
	
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFRow row;
	private FileOutputStream fileOutput;
	private FileInputStream fileInput;
	private File file;
	private String nameResp;
	private String nameCompany;
	private String CNPJ;
	private String activity;
	private int index;
	private int indexCNPJ;
	
	public Arquivo(File file, int type, String CNPJ) throws IOException {
		this.file = file;
		this.fileInput = new FileInputStream(this.file);
		
		//Abre o workbook do arquivo xlsx
		this.wb = new XSSFWorkbook(this.fileInput);
		
		//Abre a primeira sheet do workbook
		this.sheet = this.wb.getSheetAt(0);
		
		this.index = 0;
		
		//CNPJ da empresa
		this.CNPJ = CNPJ;
		this.indexCNPJ = searchCell("CNPJ: ");
		
		this.row = this.sheet.getRow(this.searchRow(indexCNPJ));
		
		//Pega o nome do responsavel
		this.nameResp = this.row.getCell(searchCell("Responsável: ")).getStringCellValue();
		
		//Pega o nome da empresa
		this.nameCompany = this.row.getCell(searchCell("Empresa: ")).getStringCellValue();
		
		//Pega o ramo de atividade da empresa
		if(type == 1) {
			this.activity = this.row.getCell(searchCell("Ramo de Atividade: ")).getStringCellValue();
		} else this.activity = "";
	}
	
	public XSSFSheet getSheet() {
		return this.sheet;
	}

	public String getNameResp() {
		return this.nameResp;
	}

	public String getNameCompany() {
		return this.nameCompany;
	}

	public String getCNPJ() {
		return this.CNPJ;
	}

	public String getActivity() {
		return this.activity;
	}
	
	public XSSFRow getRow() {
		return this.row;
	}
	
	//Procura pela célula que contém o nome da string passada como parametro
	private int searchCell(String find) {
		int i = 0;
		int lastIndex = this.sheet.getRow(this.index).getLastCellNum();
		String str;
		do {
			i++;
			str = this.sheet.getRow(this.index).getCell(i).getStringCellValue();
		} while((!str.equals(find)) && (i < lastIndex-1));
		
		return i;
	}
	
	private int searchRow(int indexCNPJ) {
		int i = this.index;
		String str;
		do {
			i++;
			str = this.sheet.getRow(i).getCell(this.indexCNPJ).getStringCellValue();
		} while(!str.equals(CNPJ));
		
		return i;
	}
	
}
