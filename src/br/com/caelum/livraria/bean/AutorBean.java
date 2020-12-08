package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.transaçao.Transactional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	@Inject
	private AutorDao dao;

	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregaAutorPeloId() {
		this.autor = dao.buscaPorId(autorId);
	}

	@Transactional
	public void gravar() {

		if(this.autor.getId() == null) {
			System.out.println("Gravando autor " + this.autor.getNome());
			dao.adiciona(this.autor);
		} else {
			System.out.println("Atualizando dados do autor " + this.autor.getNome());
			dao.atualiza(this.autor);
		}

		this.autor = new Autor();
	}
	
	@Transactional
	public void excluir(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		dao.remove(autor);
	}
	
	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}
	
	public List<Autor> getAutores() {
		return dao.listaTodos();
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
