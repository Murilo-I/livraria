package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.transaçao.Transactional;

@Named
@ViewScoped
public class LivroBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao; 
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext context;

	private Livro livro = new Livro();

	private Integer autorId;
	
	private List<Livro> livros;
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação e Aventura",
			"Fantasia", "Terror", "Ficção científica", "Quadrinhos");

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}
	
	public List<String> getGeneros() {
		return generos;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getLivros() {
		if(this.livros == null)
			this.livros = livroDao.listaTodos();
		
		return livros;
	}

	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void carregaLivroPeloId() {
		this.livro = livroDao.buscaPorId(this.livro.getId()); 
	}
	
	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	@Transactional
	public void gravar() {

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		if(this.livro.getId() == null) {
			System.out.println("Gravando livro " + this.livro.getTitulo());
			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();
		} else {
			System.out.println("Alterando livro " + this.livro.getTitulo());
			livroDao.atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	@Transactional
	public void excluir(Livro livro) {
		System.out.println("Removendo livro");
		livroDao.remove(livro);
	}
	
	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public void carregar(Livro livro) {
		System.out.println("Carregando livro");
		this.livro = livroDao.buscaPorId(livro.getId());
	}

	public void valorIsbn(FacesContext fc, UIComponent component, Object value) throws ValidatorException {

	    String valor = value.toString();
	    if (valor.length() != 17 || naoSegueoPadrao(valor)) {
	        throw new ValidatorException(new FacesMessage("O modelo de isbn deve seguir "
	        		+ "o padrão xxx-x-xx-xxxxxx-x e ser numérico"));
	    }
	}

	private boolean naoSegueoPadrao(String valor) {
		String[] isbn = valor.split("-");
		
		if(isbn[0].length() != 3 & isbn[1].length() != 1 & isbn[2].length() != 2 
				& isbn[3].length() != 6 & isbn[4].length() != 1) 	
			return true;
		
		return false;
			
	}
}
