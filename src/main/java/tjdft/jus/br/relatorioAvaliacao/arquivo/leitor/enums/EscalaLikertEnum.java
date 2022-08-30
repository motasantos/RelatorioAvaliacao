package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.enums;

public enum EscalaLikertEnum {
    NAO_SE_APLICA("Não se Aplica"),
    OTIMO("Ótimo"),
    BOM("Bom"),
    REGULAR("Regular"),
    RUIM("Ruim"),
    PESSIMO("Péssimo");

    private final String avaliacao;

    /**
     * @param avaliacao
     */
    EscalaLikertEnum(final String avaliacao) {
        this.avaliacao = avaliacao;
    }


    @Override
    public String toString() {
        return avaliacao;
    }
}