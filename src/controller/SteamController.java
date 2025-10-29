package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.gui.listagenerica.Lista;
import model.Jogo;


public class SteamController {
	
	// gamename,year,month,avg,gain,peak,avg_peak_perc
	
	public void pesquisaJogo(int ano, String mes, double avg) {
		Lista<Jogo> lista = new Lista<>();
		
		percorreCSV(ano, mes, avg, lista);
		
		if (lista.isEmpty()) System.out.println("Nenhum jogo nos parâmetros!");
		
		int tam = lista.size();
		
		for (int i = 0; i < tam; i++) {
			try {
				System.out.printf("%-60s %-10s %n", lista.get(i).nome, lista.get(i).avgJogo);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} 
		}
			
	}
	
	public void gravaLista (int ano, String mes, String diretorio, String nomeArquivo) throws IOException {
		Lista<Jogo> lista = new Lista<>();
		
		File pasta = new File("C:\\" + diretorio.trim());
		
		if (!pasta.exists() && !pasta.isDirectory()) {
			throw new IOException("Diretorio inválido");
		}
		
		File arquivo = new File(pasta, "\\" + nomeArquivo.trim() + ".csv");
		
		percorreCSV(ano, mes, 0, lista);
		int contador = 0;
		BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo));
		Jogo jogo;
		int tam = lista.size();
		try {
			while (contador < tam) {
				jogo = lista.get(contador);
				
				gravar.write(jogo.nome + ";" + jogo.avgJogo);
				gravar.newLine();
				contador++;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			gravar.close();
		}
		
	}
	
	
	private void percorreCSV(int ano, String mes, double avg, Lista<Jogo> lista) {
		
		try {
			BufferedReader ler = new BufferedReader(new FileReader("C:\\temp\\SteamCharts.csv"));
			ler.readLine();
			String linha;
			while ((linha = ler.readLine()) != null) {
		        String[] dados = linha.split(";");
		        try {
			        String nome = dados[0];
			        int anoJogo = Integer.parseInt(dados[1]);
			        String mesJogo = dados[2];
			        double avgJogo = Double.parseDouble(dados[3]);
		        
			        if (nome.contains("<U+")) continue;
			        if (anoJogo == ano && mesJogo.equalsIgnoreCase(mes) && avgJogo >= avg) {
			        	Jogo j = new Jogo(nome, anoJogo, mesJogo, avgJogo);
			        	lista.addLast(j);
			        }
		        } catch (Exception e) {
		        	continue; 
		        } 
			}
			
			ler.close();
			
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
			
	}
	
}