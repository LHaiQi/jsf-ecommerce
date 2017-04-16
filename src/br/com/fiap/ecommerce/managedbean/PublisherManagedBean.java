package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bo.PublisherBO;

@ManagedBean
public class PublisherManagedBean {
	PublisherBean publisher = new PublisherBean();
	List<PublisherBean> listPublishers = new ArrayList<PublisherBean>();
	
	public List<PublisherBean> getListPublishers() {
		return listPublishers;
	}

	public void setListPublishers(List<PublisherBean> listPublishers) {
		this.listPublishers = listPublishers;
	}

	public PublisherBean getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherBean publisher) {
		this.publisher = publisher;
	}
	
	public String searchPubliserController(){
		PublisherBO publisherBO = new PublisherBO();
		listPublishers = publisherBO.getPubliser(publisher);
		
		return "search-publisher";
	}
}
