package br.com.fiap.ecommerce.bo;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.dao.PublisherDAO;

public class PublisherBO {
	public PublisherBean getPubliser(PublisherBean publisherBean){
		PublisherDAO publisherDAO = new PublisherDAO();
		return publisherDAO.getPublisher(publisherBean);		
	}
}
