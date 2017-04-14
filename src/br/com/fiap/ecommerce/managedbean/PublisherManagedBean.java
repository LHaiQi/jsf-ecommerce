package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bo.PublisherBO;

@ManagedBean
public class PublisherManagedBean {
	private PublisherBean publisher;
	private List<PublisherBean> listPublisher = new ArrayList<PublisherBean>();
	
	public List<PublisherBean> getListPublisher() {
		return listPublisher;
	}

	public void setListPublisher(List<PublisherBean> listPublisher) {
		this.listPublisher = listPublisher;
	}

	public PublisherBean getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherBean publisher) {
		this.publisher = publisher;
	}
	
	public String searchController(){
		PublisherBO publisherBO = new PublisherBO();
		listPublisher = publisherBO.getPublisherList();
		
		return "search-publisher";
	}
}
