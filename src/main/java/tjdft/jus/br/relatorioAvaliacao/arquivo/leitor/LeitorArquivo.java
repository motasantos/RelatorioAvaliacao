package tjdft.jus.br.relatorioAvaliacao.arquivo.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import tjdft.jus.br.relatorioAvaliacao.relatorio.RelatorioAvaliacao;

public class LeitorArquivo {
	
	ArrayList<CaracteristicaObjetiva> caracteristicaList = new ArrayList<>();

	public void trataArquivo(MultipartFile multipart, String fileName) throws IOException {
	    File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
	    multipart.transferTo(convFile);
	    trataArquivo(convFile);
	}
	
	@SuppressWarnings("resource")
	public void trataArquivo(File arquivo) throws IOException {

		InputStream arquivoExcelInputSream = new FileInputStream(arquivo);
		Workbook workbook = null;
		workbook = new XSSFWorkbook(arquivoExcelInputSream);

		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int colIndex = cell.getColumnIndex();
					int rowIndex = cell.getRowIndex();

					if (colIndex > 4) {
						if (colIndex <= 11) {
							if (rowIndex == 0) {// Cria um novo Item de Avaliação
								this.caracteristicaList
										.add(new CaracteristicaObjetivaLikert(getValorCelula(cell), colIndex));
							} else {
								// Obtém o itemAvaliacao criado realiza a contagem segundo o valor da célula
								CaracteristicaObjetiva contador = getContadorAvaliacaoItem(colIndex);
								if (contador != null) {
									contador.conta(getValorCelula(cell));
								}
							}
						}

						if (colIndex == 12) {
							if (rowIndex == 0) {// Cria um novo Item de Avaliação
								this.caracteristicaList
										.add(new CaracteristicaObjetivaExpectativas(getValorCelula(cell), colIndex));
							} else {
								// Obtém o itemAvaliacao criado realiza a contagem segundo o valor da célula
								CaracteristicaObjetiva contador = getContadorAvaliacaoItem(colIndex);
								if (contador != null) {
									contador.conta(getValorCelula(cell));
								}
							}
						}
						
						
					}
					
					
				}

			}
		}

		arquivoExcelInputSream.close();


		new RelatorioAvaliacao(this.caracteristicaList).geraRelatorio();

		return;
	}

	private CaracteristicaObjetiva getContadorAvaliacaoItem(int index) {
		for (CaracteristicaObjetiva contadorAvaliacaoItem : this.caracteristicaList) {
			if (contadorAvaliacaoItem.getIndex() == index) {
				return contadorAvaliacaoItem;
			}
		}
		return null;

	}

	private String getValorCelula(Cell cell) {
		String valor = "";
		switch (cell.getCellType()) {
		case STRING:
			valor = cell.getStringCellValue().trim();
			break;
		case NUMERIC:
			valor = "NUMERICO";
		default:
			break;
		}

		return valor;

	}

}
