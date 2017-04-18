package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.dao.PublisherDAO;

public class PublisherBO {
	public List<PublisherBean> getPubliser(PublisherBean publisherBean){
		PublisherDAO publisherDAO = new PublisherDAO();
		return publisherDAO.getAllPublishers(publisherBean);		
	}

	public void alterPublisher(PublisherBean publisher) {
		PublisherDAO publisherDAO = new PublisherDAO();
		publisherDAO.alterPublisher(publisher);
	}
}
