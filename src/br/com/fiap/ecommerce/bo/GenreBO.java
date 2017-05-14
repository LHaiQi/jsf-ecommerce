package br.com.fiap.ecommerce.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.dao.GenreDAO;

public class GenreBO {
	public List<GenreBean> getListGenre() throws SQLException{
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.getListGenre();
	}
	
	public List<GenreBean> getGenre(GenreBean genreBean) throws SQLException{
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.pesquisarAllGenres(genreBean);		
	}	
	
	public GenreBean pesquisarGenre(GenreBean genreBean) throws SQLException{
		GenreDAO genreDAO = new GenreDAO();
		return genreDAO.pesquisarGenre(genreBean);
	}
	
	public void inserirGenre(GenreBean genreBean) throws SQLException{
		GenreDAO genreDAO = new GenreDAO();
		genreDAO.inserirGenre(genreBean);
	}
	
	public void deletarGenre(GenreBean genreBean) throws SQLException{
		GenreDAO genreDAO = new GenreDAO();
		
		genreDAO.deletarGenre(genreBean);
	}
	
	public void alterarGenre(GenreBean genreBean) throws SQLException {
		GenreDAO genreDAO = new GenreDAO();
		genreDAO.alterarGenre(genreBean);
	}
}
