package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bo.PublisherBO;

@ManagedBean
@SessionScoped
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
		listPublishers = publisherBO.getListPubliser(publisher);
		
		return "search-publisher";
	}
	
	public String fillEditPublisherController(){
		PublisherBO publisherBO = new PublisherBO();
		publisher = publisherBO.getPublisher(publisher);
		
		return "edit-publisher";
	}
	
	public String editPublisherController(){
		PublisherBO publisherBO = new PublisherBO();
		publisherBO.alterPublisher(publisher);
		
		return "search-publisher";
	}
}
