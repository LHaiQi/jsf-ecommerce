package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bo.GenreBO;
import br.com.fiap.ecommerce.bo.UserBO;

@SessionScoped
@ManagedBean
public class GenreManagedBean {
	GenreBean genre = new GenreBean();
	List<GenreBean> listGenres = new ArrayList<GenreBean>();
	
	public GenreBean getGenre() {
		return genre;
	}
	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}
	public List<GenreBean> getListGenres() {
		return listGenres;
	}
	public void setListGenres(List<GenreBean> listGenres) {
		this.listGenres = listGenres;
	}
	
	public String procurarGenreController(){
		GenreBO genreBO = new GenreBO();
		listGenres = genreBO.getGenre(genre);
		
		return "search-genre";
	}
	
	public String insertGenreController(){
		GenreBO genreBO = new GenreBO();
		genreBO.inserirGenre(genre);
		
		return "insert-genre";
	}
	
	public String deletarGenreController(){
		GenreBO genreBO = new GenreBO();
		genreBO.deletarGenre(genre);;
		
		return procurarGenreController() ;
	}
}
