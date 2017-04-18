package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.dao.GenreDAO;

public class GenreBO {
	public List<GenreBean> getGenre(GenreBean genreBean){
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.pesquisarAllGenres(genreBean);		
	}	
}
