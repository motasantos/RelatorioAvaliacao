package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;

public class Caracteristica implements ICaracteristica {

	protected int totalAvaliacoes = 0;
	protected String caracteristica;

	protected void carregaItensEscala() {
	
	}

	protected int index;

	public Caracteristica() {
		super();
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int incrementaTotal() {
		this.totalAvaliacoes = this.totalAvaliacoes + 1;
		return totalAvaliacoes;
	}

	@Override
	public String toString() {
		return this.toString();
	}

	@Override
	public String getCaracteristica() {
		return caracteristica;
	}
	

}