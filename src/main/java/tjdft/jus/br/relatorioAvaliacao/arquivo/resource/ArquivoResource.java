package tjdft.jus.br.relatorioAvaliacao.arquivo.resource;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tjdft.jus.br.relatorioAvaliacao.arquivo.leitor.LeitorArquivo;

@RestController
@RequestMapping("/arquivos")
public class ArquivoResource {
	@PostMapping
	public void  upload(@RequestParam MultipartFile arquivo) {
		LeitorArquivo leitorArquivo = new LeitorArquivo();
		try {
			leitorArquivo.trataArquivo(arquivo, "/dadosAvaliacao.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
