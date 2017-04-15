package br.com.fiap.ecommerce.managedbean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bo.PublisherBO;

@ManagedBean
public class PublisherManagedBean {
	PublisherBean publisher = new PublisherBean();

	public PublisherBean getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherBean publisher) {
		this.publisher = publisher;
	}
	
	public String searchPubliserController(){
		PublisherBO publisherBO = new PublisherBO();
		publisher = publisherBO.getPubliser(publisher);
		
		return "search-publisher";
	}
}
