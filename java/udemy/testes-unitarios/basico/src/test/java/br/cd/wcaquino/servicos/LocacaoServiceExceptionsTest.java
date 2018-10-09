package br.cd.wcaquino.servicos;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import junit.framework.Assert;

public class LocacaoServiceExceptionsTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	// Aula 9
	@Test
	public void testeLocacaoSemEstoqueErro() throws Exception {
		// Cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 1, 4.6);
		
		// A��o
		service.alugarFilme(usuario, filme);
	}
	
	@Test(expected=FilmeSemEstoqueException.class)
	public void testeLocacaoSemEstoqueElegante() throws Exception {
		// Cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 0, 4.6);
		
		// A��o
		service.alugarFilme(usuario, filme);
	}
	
	@Test
	public void testeLocacaoSemEstoqueRobusta() throws FilmeSemEstoqueException {
		// Cen�rio
		LocacaoService service = new LocacaoService();
		//Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 1, 4.6);
		
		// A��o
		try {
			service.alugarFilme(null, filme);
			fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usu�rio vazio"));
		}
		
		System.out.println("Forma robusta");
	}
	
	@Test
	public void testeLocacaoFilmeVazio() throws LocadoraException, FilmeSemEstoqueException {
		// Cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Adriano");
		//Filme filme = new Filme("Dan�a do amanh�", 0, 4.6);
		
		expected.expect(LocadoraException.class);
		expected.expectMessage("Filme vazio");
		
		// A��o
		service.alugarFilme(usuario, null);
		
		System.out.println("Forma nova");
	}
	
}