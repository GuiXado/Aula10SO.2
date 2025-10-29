package view;

import java.io.IOException;
import javax.swing.JOptionPane;

import controller.SteamController;

public class Principal {

	public static void main(String[] args) {
		try {
			SteamController sc = new SteamController();
			
			String menu = "1 - Pesquisa jogo\n"
					+ "2 - Grava jogo\n"
					+ "9 - Sair";
			int opc;
			do {
				opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
				switch(opc) {
					case 1:
						int ano = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano: \n"));
			            String mes = JOptionPane.showInputDialog("Escreva o mês: (em inglês)\n");
			            double avg = Double.parseDouble(JOptionPane.showInputDialog("Digite a média mínima: \n"));
						
			            sc.pesquisaJogo(ano, mes, avg);
						break;
					case 2:
						int ano_ = Integer.parseInt(JOptionPane.showInputDialog("Ano: \n"));
			            String mes_ = JOptionPane.showInputDialog("Mês: (em inglês)\n");
			            String dir = JOptionPane.showInputDialog("Diretório: \n");
			            String nomeArq = JOptionPane.showInputDialog("Nome do arquivo: \n");
						
			            sc.gravaLista(ano_, mes_, dir, nomeArq);
						break;
					case 9:
						break;
					default:
						JOptionPane.showMessageDialog(null, "Inválido");
				
				}
				
			} while (opc != 9);
			
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
	}

}
