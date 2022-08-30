package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;

import tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.enums.EscalaExpectativasEnum;


public class CaracteristicaObjetivaExpectativas extends CaracteristicaObjetiva {


	public CaracteristicaObjetivaExpectativas(String caracteristica, int index) {
		super(caracteristica, index);
	}

	protected void carregaItensEscala() {
		this.carregaItensEscalaExpectativas();
	}
	
	void carregaItensEscalaExpectativas() {
		for (EscalaExpectativasEnum avaliacao : EscalaExpectativasEnum.values()) {
			this.dadoQuantitativoList.add(new DadoQuantitativo(avaliacao, this));
		}
	}

}
