package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basicas.Arquivo;
import basicas.DiagnosticoFinal;
import basicas.DiagnosticoInicial;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JFileChooser fc;
	private JTextField textField_1;
	private File f1, f2;
	private JButton btnComparar;
	private Arquivo a1;
	private Arquivo a2;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(132, 110, 262, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 181, 262, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		fc = new JFileChooser();
		fc.setDialogTitle("Escolhendo arquivo");
		
		JButton btnArquivo = new JButton("Inicial");
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = fc.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					a1 = readFile(f1, textField, 1);
				}
			}
		});
		btnArquivo.setBounds(20, 109, 89, 23);
		contentPane.add(btnArquivo);
		
		JButton btnArquivo_1 = new JButton("Final");
		btnArquivo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fc.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					a2 = readFile(f2, textField_1, 2);
				}
			}
		});
		btnArquivo_1.setBounds(20, 180, 89, 23);
		contentPane.add(btnArquivo_1);
		
		btnComparar = new JButton("Comparar");
		btnComparar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Empresa: " + a1.getNameCompany() + ", CNPJ: " + a1.getCNPJ());
				System.out.println("Responsável: " + a1.getNameResp());
				System.out.println("Ramo de atividade: " + a1.getActivity());
				
				DiagnosticoInicial di = new DiagnosticoInicial(a1.getSheet(), a1.getRow());
				DiagnosticoFinal df = new DiagnosticoFinal(a2.getSheet(), a2.getRow());
				int diff;
				
				System.out.println("");
				
				System.out.print("Produção - ");
				diff = df.getProducao() - di.getProducao();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Compras - ");
				diff = df.getCompras() - di.getCompras();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Vendas - ");
				diff = df.getVendas() - di.getVendas();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Marketing - ");
				diff = df.getMarketing() - di.getMarketing();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Finanças - ");
				diff = df.getFinancas() - di.getFinancas();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Pessoas - ");
				diff = df.getPessoas() - di.getPessoas();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Estratégia - ");
				diff = df.getEstrategia() - di.getEstrategia();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Comunicação - ");
				diff = df.getComunicacao() - di.getComunicacao();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Relacionamento - ");
				diff = df.getEstrategia() - di.getEstrategia();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Negociação - ");
				diff = df.getNegociacao() - di.getNegociacao();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Planejamento - ");
				diff = df.getPlanejamento() - di.getPlanejamento();
				result(diff);
				
				System.out.println("");
				
				System.out.print("Tempo para realização de suas atividades - ");
				diff = df.getTempoTarefas() - di.getTempoTarefas();
				result(diff);
				
				System.out.println("");
				
				System.out.println("As atividades em sua empresa são em geral:");
				printString(di.getAtivEmpresa(), df.getAtivEmpresa());
				System.out.println("------------------------------------------------");
				
				System.out.println("Os controles gerenciais e/ou financeiros lhe auxiliam em:");
				System.out.print("Antes - ");
				printArray(di.getSobreControles());
				
				System.out.print("\nDepois - ");
				printArray(df.getSobreControles());
				System.out.println("\n------------------------------------------------");
				
				System.out.println("O planejamento realizado na empresa abrange:");
				System.out.print("Antes - ");
				printArray(di.getSobrePlanejamento());
				
				System.out.print("\nDepois - ");
				printArray(df.getSobrePlanejamento());
				
				System.out.println("\n------------------------------------------------");
				
				System.out.println("O caixa da empresa está:");
				printString(di.getCaixaEmpresa(), df.getCaixaEmpresa());
				System.out.println("------------------------------------------------");
				
				System.out.println("O proprietário recebe por seu trabalaho:");
				printString(di.getRemuneracaoProprietario(), df.getRemuneracaoProprietario());
				System.out.println("------------------------------------------------");
				
				System.out.println("Os colaboradores internos da empresa estão:");
				printString(di.getColaboradoresInternos(), df.getColaboradoresInternos());
				System.out.println("------------------------------------------------");
				
				System.out.println("A qualidade de vida do empresário é:");
				printString(di.getQualidadeVida(), df.getQualidadeVida());
				System.out.println("------------------------------------------------");
				
				System.out.println("Os maiores investimentos que a empresa realiza são em:");
				System.out.print("Antes - ");
				printArray(di.getMaioresInvestimentos());
				System.out.print("\nDepois - ");
				printArray(df.getMaioresInvestimentos());
				System.out.println("\n------------------------------------------------");
				
				System.out.println("Os maiores investimentos que a empresa necessita são em:");
				printArray(di.getMaioresInvestimentosNecessita());
				System.out.println("\n------------------------------------------------");
				
				System.out.println("O empresário conhece sobre o perfil de seus clientes e o mercado:");
				printString(di.getConhecimentoPerfil(), df.getConhecimentoPerfil());
				System.out.println("------------------------------------------------");
				
				System.out.println("A empresa possui hoje, em relação à concorrência:");
				printString(di.getRelacaoConcorrencia(), df.getRelacaoConcorrencia());
				System.out.println("------------------------------------------------");
				
				System.out.println("Área em que a empresa necessita maior apoio:");
				printArray(di.getNecessitaApoio());
				System.out.println("\n------------------------------------------------");
				
				System.out.println("No período de início da Consultoria \"Primeiros Passos\" até o momento, avaliou:\n");
				printAvaliacoes(df.getLegendasAvaliacao(), df.getAvaliacaoPosInicio());
				
			}
		});
		btnComparar.setBounds(172, 236, 104, 29);
		contentPane.add(btnComparar);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnpj.setBounds(93, 37, 53, 20);
		contentPane.add(lblCnpj);
		
		textField_2 = new JTextField();
		textField_2.setBounds(156, 39, 193, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
	}
	
	private Arquivo readFile(File f, JTextField field, int type) {
		f = new File(fc.getSelectedFile().getAbsolutePath());
		field.setText(f.getName());
		Arquivo a = null;
		try {
			a = new Arquivo(f, type, textField_2.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	private void result(int diff) {
		
		if(diff > 0) System.out.println("Melhorou");
		else if(diff < 0) System.out.println("Piorou");
		else System.out.println("Sem alteração");
	}
	
	private void printArray(String[] arr) {
		boolean first = true;
		for(int i = 0; i < arr.length && arr[i] != null; i++) {
			if(!first) System.out.print(", ");
			else first = false;
			
			System.out.print(arr[i]);
		}
	}
	
	private void printString(String str1, String str2) {
		System.out.println("Antes - " + str1);
		System.out.println("Depois - " + str2);
	}
	
	private void printAvaliacoes(String[] legendas, String[] avaliacoes) {
		for(int i = 0; i < avaliacoes.length && !avaliacoes[i].equals(null); i++) {
			System.out.println(legendas[i] + " - " + avaliacoes[i]);
		}
	}
	
}
