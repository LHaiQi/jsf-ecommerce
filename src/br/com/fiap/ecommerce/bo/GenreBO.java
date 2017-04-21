package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.GenreDAO;
import br.com.fiap.ecommerce.dao.UserDAO;

public class GenreBO {
	public List<GenreBean> getGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.pesquisarAllGenres(genreBean);		
	}	
	
	public void inserirGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		genreDAO.inserirGenre(genreBean);
	}
}
