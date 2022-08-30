package tjdft.jus.br.relatorioAvaliacao.relatorio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.util.ResourceUtils;

import tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.CaracteristicaObjetiva;
import tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.DadoQuantitativo;

public class RelatorioAvaliacao {
	private final String[] tokens = { "caracteristica[%d]", "total[%d]", "otimo[%d]", "bom[%d]", "regular[%d]",
			"ruim[%d]", "pessimo[%d]", "nao_se_aplica[%d]", "outros_conteudos[%d]", "total_respostas[%d]",
			"total_otimo", "superadas[%d]", "atendidas[%d]", "parcialmente_atendidas[%d]", "nao_atendidas[%d]" };

	private ArrayList<CaracteristicaObjetiva> caracteristicaObjetivaList;

	public RelatorioAvaliacao(ArrayList<CaracteristicaObjetiva> itemAvaliacaoList) {
		this.caracteristicaObjetivaList = itemAvaliacaoList;
	}

	public void geraRelatorio() throws IOException {

		try (XWPFDocument doc = new XWPFDocument(new FileInputStream(ResourceUtils
				.getFile("/home/jailson/Projetos/relatorioAvaliacao/src/main/resources/templates/modelo.docx")))) {
			List<XWPFTable> tbList = doc.getTables();
			varreTabelas(tbList);
			doc.write(new FileOutputStream(
					"/home/jailson/Projetos/relatorioAvaliacao/src/main/resources/result/result.docx"));
		}

	}

	private void varreTabelas(List<XWPFTable> tbList) {
		for (XWPFTable table : tbList) {
			varreTabela(table);
		}
	}

	private void varreTabela(XWPFTable table) {

		for (XWPFTableRow row : table.getRows()) {
			for (XWPFTableCell cell : row.getTableCells()) {
				for (IBodyElement element : cell.getBodyElements()) {
					if (element instanceof XWPFParagraph) {
						XWPFParagraph p = (XWPFParagraph) element;
						parse(p);
					}

					if (element instanceof XWPFTable) {
						XWPFTable t = (XWPFTable) element;
						varreTabela(t);
					}

				}

			}

		}

	}

	private void parse(XWPFParagraph p) {
		for (int i = 0; i < caracteristicaObjetivaList.size(); i++) {
			for (XWPFRun r : p.getRuns()) {
				String text = r.getText(0);
				if (text != null && text.startsWith("${")) {
					System.out.println(text);
					for (int j = 0; j < tokens.length; j++) {

						String chave = "${" + String.format(tokens[j], i) + "}";
						String replacer = "";

						if (text.contains(chave)) {
							if (chave.contains("caracteristica")) {
								replacer = this.caracteristicaObjetivaList.get(i).getCaracteristica().trim();
							} else

							if (chave.contains("total")) {
								replacer = String.valueOf(this.caracteristicaObjetivaList.get(i).getTotalAvaliacoes());
							}

							else {
								ArrayList<DadoQuantitativo> avaliacaoQuantidadeList = caracteristicaObjetivaList.get(i)
										.getDadoQuantitativoList();
								int totalCaracteristica = caracteristicaObjetivaList.get(i).getTotalAvaliacoes();
								for (DadoQuantitativo avaliacaoQuantidade : avaliacaoQuantidadeList) {
									String t = removeSubscritor(text);

									if (t.toUpperCase().equals(avaliacaoQuantidade.getAvaliacaoEnumName())) {
										if (totalCaracteristica <= 20) {
											replacer = String.valueOf(avaliacaoQuantidade.getQuantidade());
										} else {
											replacer = String.valueOf(avaliacaoQuantidade.getPercentual() + "%");
										}

										if (replacer.equals("0") || replacer.equals("0%")) {
											replacer = "-";
										}
										break;
									}
								}
							}

							text = text.replace(chave, replacer);
							r.setText(text, 0);
							return;
						}
					}

				}
			}
		}

	}

	private String removeSubscritor(String text) {
		return text.replaceAll("[0-9]", "").replace("[", "").replace("]", "").replace("$", "").replace("{", "")
				.replace("}", "").toUpperCase();
	}

}
