package br.cd.wcaquino.servicos;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	// Aula 5
	@Test
	public void testeLocacao() {
		// Cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 10, 4.5);
		
		// A��o
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		// Verifica��o
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(locacao.getDataRetorno().after(new Date()));
		Assert.assertTrue(locacao.getValor() == filme.getPrecoLocacao());
	}
}