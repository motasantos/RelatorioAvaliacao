package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;

import tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.enums.EscalaLikertEnum;


public class CaracteristicaObjetivaLikert extends CaracteristicaObjetiva {


	public CaracteristicaObjetivaLikert(String caracteristica, int index) {
		super(caracteristica, index);
	}

	protected void carregaItensEscala() {
		this.carregaItensEscalaLikert();
	}
	
	void carregaItensEscalaLikert() {
		for (EscalaLikertEnum avaliacao : EscalaLikertEnum.values()) {
			this.dadoQuantitativoList.add(new DadoQuantitativo(avaliacao, this));
		}
	}

}
