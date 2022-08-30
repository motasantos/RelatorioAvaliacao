package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;

public class DadoQuantitativo {
	
		@SuppressWarnings("rawtypes")
		public DadoQuantitativo(Enum itemDadoQuantitativoEnum, CaracteristicaObjetiva parent) {
			this.itemDadoQuantitativoEnum = itemDadoQuantitativoEnum;
			this.parent = parent;
		}

		private CaracteristicaObjetiva parent;

		@SuppressWarnings("rawtypes")
		private Enum itemDadoQuantitativoEnum;

		private int quantidade = 0;

		public int getQuantidade() {
			return this.quantidade;
		}
		
		public String getNomeItemDadoQuantitativo() {
			return this.itemDadoQuantitativoEnum.toString();
		}
		
		public String getAvaliacaoEnumName() {
			return this.itemDadoQuantitativoEnum.name();
		}
		

		public int incrementa() {
			this.quantidade = this.quantidade + 1;
			parent.incrementaTotal();
			return this.getQuantidade();
		};

		public String toString() {
			String result  = this.itemDadoQuantitativoEnum.toString() + ": " + this.getQuantidade() + " Percentual: " +this.getPercentual()+ "\n";
			return result;
		}

		public String getPercentual() {
			if (this.getQuantidade() != 0)
			  return Double.toString(((this.getQuantidade() *100) / parent.getTotalAvaliacoes()));
			
			return "0";
		}

	}
