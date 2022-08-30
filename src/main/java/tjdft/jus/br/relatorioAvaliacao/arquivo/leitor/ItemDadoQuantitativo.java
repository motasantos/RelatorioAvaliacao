package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;
import tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.CaracteristicaObjetiva;


public class ItemDadoQuantitativo {

	protected CaracteristicaObjetiva parent;
	private int quantidade = 0;
	
	@SuppressWarnings("rawtypes")
	protected Enum escalaEnum;
	

	public ItemDadoQuantitativo(@SuppressWarnings("rawtypes") Enum escalaEnum) {
		this.escalaEnum = escalaEnum;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public String getEscala() {
		return this.escalaEnum.toString();
	}

	public int incrementa() {
		this.quantidade = this.quantidade + 1;
		parent.incrementaTotal();
		return this.getQuantidade();
	}

	public String toString() {
		String result  = this.escalaEnum.toString() + ": " + this.getQuantidade() + " Percentual: " +this.getPercentual()+ "\n";
		return result;
	}

	private String getPercentual() {
		return Double.toString(((this.getQuantidade() *100) / parent.getTotalAvaliacoes()));
	}

}