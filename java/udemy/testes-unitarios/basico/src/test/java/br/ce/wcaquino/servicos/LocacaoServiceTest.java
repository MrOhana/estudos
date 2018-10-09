package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;

public class LocacaoServiceTest {

	private LocacaoService service;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	// Aula 9
	@Test
	public void deveAlugarFilme() throws Exception {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 1, 4.6);
		
		List<Filme> filmes = Arrays.asList(filme);
		
		// a��o
		Locacao loc = service.alugarFilme(usuario, filmes);
		
		// valida��o
		assertThat(loc.getValor(), is(4.6));
	}
	
	@Test(expected=FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 0, 4.6);
		
		List<Filme> filmes = Arrays.asList(filme);
		
		// a��o
		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		// cenariio
		Filme filme = new Filme("Dan�a do amanh�", 1, 4.6);
		
		List<Filme> filmes = Arrays.asList(filme);
		
		// a��o
		try {
			service.alugarFilme(null, filmes);
			fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usu�rio vazio"));
		}
	}
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws LocadoraException, FilmeSemEstoqueException {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		
		expected.expect(LocadoraException.class);
		expected.expectMessage("Filme vazio");
		
		// a��o
		service.alugarFilme(usuario, null);
	}
	
	
	/** ########################### TDD ########################## 
	 * @throws FilmeSemEstoqueException 
	 * @throws LocadoraException */
	
	@Test
	public void deveDarDesconto25PctFilme3() throws LocadoraException, FilmeSemEstoqueException {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 5, 5.0);
		Filme filme2 = new Filme("Dan�a do amanh� II", 8, 5.0);
		Filme filme3 = new Filme("Dan�a do depois amanh�", 3, 5.0);
		
		List<Filme> filmes = Arrays.asList(filme, filme2, filme3);
		
		// a��o
		Locacao loc = service.alugarFilme(usuario, filmes);
		
		// valida��o
		assertThat(loc.getValor(), is(13.75));
	}
	
	@Test
	public void deveDarDesconto50PctFilme4() throws LocadoraException, FilmeSemEstoqueException {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 5, 5.0);
		Filme filme2 = new Filme("Dan�a do amanh� II", 8, 5.0);
		Filme filme3 = new Filme("Dan�a do depois amanh�", 3, 5.0);
		Filme filme4 = new Filme("Chuvas do dia", 2, 5.0);
		
		List<Filme> filmes = Arrays.asList(filme, filme2, filme3, filme4);
		
		// a��o
		Locacao loc = service.alugarFilme(usuario, filmes);
		
		// valida��o
		assertThat(loc.getValor(), is(16.25));
	}
	
	@Test
	public void deveDarDesconto75PctFilme5() throws LocadoraException, FilmeSemEstoqueException {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 5, 5.0);
		Filme filme2 = new Filme("Dan�a do amanh� II", 8, 5.0);
		Filme filme3 = new Filme("Dan�a do depois amanh�", 3, 5.0);
		Filme filme4 = new Filme("Chuvas do dia", 2, 5.0);
		Filme filme5 = new Filme("Chuvas do dia II", 2, 5.0);
		
		List<Filme> filmes = Arrays.asList(filme, filme2, filme3, filme4, filme5);
		
		// a��o
		Locacao loc = service.alugarFilme(usuario, filmes);
		
		// valida��o
		assertThat(loc.getValor(), is(17.50));
	}
	
	@Test
	public void deveDarDescontoTotalAPartirDoFilme6() throws LocadoraException, FilmeSemEstoqueException {
		// cen�rio
		Usuario usuario = new Usuario("Adriano");
		Filme filme = new Filme("Dan�a do amanh�", 5, 5.0);
		Filme filme2 = new Filme("Dan�a do amanh� II", 8, 5.0);
		Filme filme3 = new Filme("Dan�a do depois amanh�", 3, 5.0);
		Filme filme4 = new Filme("Chuvas do dia", 2, 5.0);
		Filme filme5 = new Filme("Chuvas do dia II", 2, 5.0);
		Filme filme6 = new Filme("Chuvas do dia III", 2, 5.0);
		
		List<Filme> filmes = Arrays.asList(filme, filme2, filme3, filme4, filme5, filme6);
		
		// a��o
		Locacao loc = service.alugarFilme(usuario, filmes);
		
		// valida��o
		assertThat(loc.getValor(), is(17.50));
	}
}