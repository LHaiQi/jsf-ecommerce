package br.com.fiap.ecommerce.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.dao.PublisherDAO;

public class PublisherBO {
	
	public void setPublisher(PublisherBean publisher){
		
	}
	
	public PublisherBean getPublisher(String name) {
		PublisherBean publisher = null;
		
		PublisherDAO publisherDAO = new PublisherDAO();
		publisher = publisherDAO.getPublisher(name);
		
		return publisher;
	}
	
	public List<PublisherBean> getPublisherList(String name) {
		List<PublisherBean> listPublisher = new ArrayList<PublisherBean>();
		
		PublisherDAO publisherDAO = new PublisherDAO();
		listPublisher = publisherDAO.getListPublisher(name);
		
		return listPublisher;
	}
	
	public void dropPublisher(int publisherID){
		
	}
	
	public void alterPublisher(int publisherID, PublisherBean publisher) {
		
	}
}
