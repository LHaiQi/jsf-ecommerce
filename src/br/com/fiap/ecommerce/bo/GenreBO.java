package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.GenreDAO;
import br.com.fiap.ecommerce.dao.UserDAO;

public class GenreBO {
	public List<GenreBean> getListGenre(){
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.getListGenre();
	}
	
	public List<GenreBean> getGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.pesquisarAllGenres(genreBean);		
	}	
	
	public GenreBean pesquisarGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.pesquisarGenre(genreBean);
	}
	
	public void inserirGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		genreDAO.inserirGenre(genreBean);
	}
	
	public void deletarGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		
		genreDAO.deletarGenre(genreBean);
	}
	
	public void alterarGenre(GenreBean genreBean) {
		GenreDAO genreDAO = new GenreDAO();
		genreDAO.alterarGenre(genreBean);
	}
}
