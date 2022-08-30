package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;

import java.util.ArrayList;

public class CaracteristicaObjetiva extends Caracteristica {

	
	protected ArrayList<DadoQuantitativo> dadoQuantitativoList = new ArrayList<DadoQuantitativo>();

	public CaracteristicaObjetiva(String caracteristica, int index) {
		super();
		this.caracteristica = caracteristica;
		this.index = index;
		this.carregaItensEscala();
	}

	public int conta(String avaliacao) {
		for (DadoQuantitativo dadoQuantitativo : dadoQuantitativoList) {
			if (dadoQuantitativo.getNomeItemDadoQuantitativo().trim().equals(avaliacao.trim())) {
				dadoQuantitativo.incrementa();
				return dadoQuantitativo.getQuantidade();
			}
		}
	
		return -1;
	
	}
	
	public String toString() {
		String result;
		result = this.caracteristica + " index: " + this.getIndex() + " Total Avaliações: " + this.getTotalAvaliacoes()
				+ "\n";
	
		for (DadoQuantitativo avaliacaoQuantidade : dadoQuantitativoList) {
			result = result + avaliacaoQuantidade.toString();
		}
	
		return result;
	}
	
	

	public int getTotalAvaliacoes() {
		return totalAvaliacoes;
	}

	public void setTotalAvaliacoes(int totalAvaliacoes) {
		this.totalAvaliacoes = totalAvaliacoes;
	}

	public ArrayList<DadoQuantitativo> getDadoQuantitativoList() {
		return dadoQuantitativoList;
	}

	public void setAvaliacaoList(ArrayList<DadoQuantitativo> avaliacaoList) {
		this.dadoQuantitativoList = avaliacaoList;
	}

}