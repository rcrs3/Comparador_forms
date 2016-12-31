package basicas;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DiagnosticoFinal {
	
	private XSSFSheet sheet;
	private XSSFRow row;
	private int index;
	private int producao, compras, vendas, marketing, financas, pessoas, estrategia;
	private int comunicacao, relacionamento, negociacao, planejamento;
	private int tempoTarefas;
	private int indexArmazenado;
	private String ativEmpresa, caixaEmpresa, remuneracaoProprietario, colaboradoresInternos, qualidadeVida;
	private String conhecimentoPerfil, relacaoConcorrencia;
	private String[] sobreControles, sobrePlanejamento, maioresInvestimentos, avaliacaoPosInicio, legendasAvaliacao;
	private boolean[] armazenado;
	
	public DiagnosticoFinal(XSSFSheet sheet, XSSFRow row) {
		this.sheet = sheet;
		this.row = row;
		this.index = 0;
		this.indexArmazenado = 0;
		this.armazenado = new boolean[16];
		this.legendasAvaliacao = new String[16];
		this.producao = search("Produção");
		this.compras = search("Compras");
		this.vendas = search("Vendas");
		this.marketing = search("Marketing");
		this.financas = search("Finanças");
		this.estrategia = search("Estratégia");
		this.comunicacao = search("Comunicação");
		this.relacionamento = search("Relacionamento");
		this.negociacao = search("Negociação");
		this.planejamento = search("Planejamento");
		this.tempoTarefas = searchTempoTarefas("3");
		this.ativEmpresa = searchOneAnswer("4");
		this.sobreControles = searchMultiAnswer("5");
		this.sobrePlanejamento = searchMultiAnswer("6");
		this.caixaEmpresa = searchOneAnswer("7");
		this.remuneracaoProprietario = searchOneAnswer("8");
		this.colaboradoresInternos = searchOneAnswer("9");
		this.qualidadeVida = searchOneAnswer("10");
		this.maioresInvestimentos = searchMultiAnswer("11");
		this.conhecimentoPerfil = searchOneAnswer("12");
		this.relacaoConcorrencia = searchOneAnswer("13");
		this.avaliacaoPosInicio = searchAvaliacaoPosInicio("14");
	}
	
	public int getProducao() {
		return producao;
	}

	public int getCompras() {
		return compras;
	}

	public int getVendas() {
		return vendas;
	}

	public int getMarketing() {
		return marketing;
	}

	public int getFinancas() {
		return financas;
	}

	public int getPessoas() {
		return pessoas;
	}

	public int getEstrategia() {
		return estrategia;
	}
	
	public int getComunicacao() {
		return comunicacao;
	}

	public int getRelacionamento() {
		return relacionamento;
	}

	public int getNegociacao() {
		return negociacao;
	}

	public int getPlanejamento() {
		return planejamento;
	}
	
	public int getTempoTarefas() {
		return tempoTarefas;
	}
	
	public String getAtivEmpresa() {
		return ativEmpresa;
	}

	public String[] getSobreControles() {
		return sobreControles;
	}

	public String[] getSobrePlanejamento() {
		return sobrePlanejamento;
	}
	
	public String getCaixaEmpresa() {
		return caixaEmpresa;
	}

	public String getRemuneracaoProprietario() {
		return remuneracaoProprietario;
	}

	public String getColaboradoresInternos() {
		return colaboradoresInternos;
	}

	public String getQualidadeVida() {
		return qualidadeVida;
	}

	public String getConhecimentoPerfil() {
		return conhecimentoPerfil;
	}

	public String getRelacaoConcorrencia() {
		return relacaoConcorrencia;
	}

	public String[] getMaioresInvestimentos() {
		return maioresInvestimentos;
	}

	public String[] getAvaliacaoPosInicio() {
		return avaliacaoPosInicio;
	}

	public String[] getLegendasAvaliacao() {
		return legendasAvaliacao;
	}

	//Procura a avaliação dada para questões que são avaliativas
	private int search(String find) {
		int i = 0;
		int lastIndex = this.sheet.getRow(this.index).getLastCellNum();
		String str;
		do {
			i++;
			str = this.sheet.getRow(this.index).getCell(i).getStringCellValue();
			
		} while((!str.equals(find)) && (i < lastIndex-1));
		
		String cmp = this.row.getCell(i).getStringCellValue();
		int value;
		if(cmp.equals("Bom")) value = 3;
		else if(cmp.equals("Regular")) value = 2;
		else value = 1;
		
		return value;
	}
		
	//Procura e retorna a resposta para a pergunta sobre o tempo de realização das atividades
	private int searchTempoTarefas(String find) {
		int i = indexQ(find);
		
		String cmp = this.row.getCell(i).getStringCellValue();
		int value;
		
		if(cmp.equals("Mais que o suficiente")) value = 4;
		else if(cmp.equals("Bem distribuído")) value = 3;
		else if(cmp.equals("Pouco")) value = 2;
		else value = 1;
		
		return value;
	}
	
	//Procura e retorna a resposta para perguntas que possuem apenas uma resposta
	private String searchOneAnswer(String find) {
		int i = indexQ(find);
		String str = this.row.getCell(i).getStringCellValue();
		
		return str;
	}
		
	private String[] searchMultiAnswer(String find) {
		int i = indexQ(find);
		String str = this.row.getCell(i).getStringCellValue();
		
		String[] ar = new String[10];
		int k = 0;
		int fim = str.indexOf(',', 0);
		for(int j = 0; j < 10; j++) {
			ar[j] = str.substring(k, (fim == -1? (str.length()) : (fim)));
			if(fim == -1) break;
			
			k = fim+2;
			fim = str.indexOf(',', fim+2);
		}
		
		return ar;
	}
	
	private String[] searchAvaliacaoPosInicio(String find) {
		int i;
		String[] arr = new String[16];
		
		for(int j = 0; j < 16; j++) {
			i = indexAvaliacaoPosInicio(find);
			arr[j] = this.row.getCell(i).getStringCellValue();
			searchLegendasAvaliacao(i, j);
		}
		return arr;
	}
	
	private void searchLegendasAvaliacao(int i, int j) {
		String str;
		str = this.sheet.getRow(this.index).getCell(i).getStringCellValue();
		
		legendasAvaliacao[j] = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
	}
	
	private int indexAvaliacaoPosInicio(String find) {
		int i = 0;
		String str;
		do {
			i++;
			str = this.sheet.getRow(this.index).getCell(i).getStringCellValue();
		} while(!str.contains(find) || armazenado[(i-28 >= 0? i-28 : 0)]);
		
		armazenado[this.indexArmazenado++] = true;
		
		return i;
	}
		
	//Retorna o indice da questão passada como parametro
	private int indexQ(String find) {
		int i = 0;
		int lastIndex = this.sheet.getRow(this.index).getLastCellNum();
		String str;
		do {
			i++;
			str = this.sheet.getRow(this.index).getCell(i).getStringCellValue();
			
		} while((!str.contains(find)) && (i < lastIndex-1));
		
		return i;
	}
}
