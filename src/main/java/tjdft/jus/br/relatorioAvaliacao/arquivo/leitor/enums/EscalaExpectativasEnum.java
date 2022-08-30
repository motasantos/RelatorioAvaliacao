package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.enums;

public enum EscalaExpectativasEnum {
////	/Péssimo
//	Ruim
//	Regular
//	Bom
//	Ótimo
//	Não se Aplica
    SUPERADAS("Superadas"),
    ATENDIDAS("Atendidas"),
    PARCIALMENTE_ATENDIDAS("Parcialmente Atendidas"),
    NAO_ATENDIDAS("Não atendidas");

    private final String avaliacao;

    /**
     * @param avaliacao
     */
    EscalaExpectativasEnum(final String avaliacao) {
        this.avaliacao = avaliacao;
    }


    @Override
    public String toString() {
        return avaliacao;
    }
}