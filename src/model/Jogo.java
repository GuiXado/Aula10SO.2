package model;

public class Jogo {
    
	public String nome;
	public int ano;
	public String mes;
	public double avgJogo;
    
    public Jogo() {
		super();
	}

	public Jogo(String nome, int ano, String mes, double avgJogo) {
        this.nome = nome;
        this.ano = ano;
        this.mes = mes;
        this.avgJogo = avgJogo;
    }
	
}
