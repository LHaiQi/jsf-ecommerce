package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bo.GenreBO;

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
		try {
			listGenres = genreBO.getGenre(genre);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao procurar", "Detalhes:  " + e));
		}
		
		return "search-genre";
	}
	
	public String insertGenreController(){
		GenreBO genreBO = new GenreBO();
		try {
			genreBO.inserirGenre(genre);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao inserir", "Detalhes:  " + e));
		}
		
		return "insert-genre";
	}
	
	public String deletarGenreController(){
		GenreBO genreBO = new GenreBO();
		try {
			genreBO.deletarGenre(genre);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao apagar", "Detalhes:  " + e));
		};
		
		return procurarGenreController() ;
	}
	
	public String editGenreController(){
		GenreBO genreBO = new GenreBO();
	    try {
			genreBO.alterarGenre(genre);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao editar", "Detalhes:  " + e));
		}
		
		return "search-genre";
	}

	public String preencherGenreController(){
		GenreBO genreBO = new GenreBO();
		try {
			genre = genreBO.pesquisarGenre(genre);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao retornar", "Detalhes:  " + e));
		}
		
		return "edit-genre";
	}
}
