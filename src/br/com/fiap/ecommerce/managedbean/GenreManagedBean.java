package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bo.GenreBO;

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
}
