package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		
		try {
			listPublishers = publisherBO.getListPubliser(publisher);
		}
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao buscar", "Detalhes:  " + e));
		}
		
		return "search-publisher";
	}
	
	public String insertPublisherController(){
		PublisherBO publisherBO = new PublisherBO();
		try {
			publisherBO.setPublisher(publisher);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir", "Detalhes:  " + e));
		}
		
		return "insert-publisher";
	}
	
	public String deletePublisherController(){
		PublisherBO publisherBO = new PublisherBO();
		try {
			publisherBO.deletePublisher(publisher);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Deletar", "Detalhes: " + e));
		}
		
		return searchPubliserController();
	}
	
	public String fillEditPublisherController(){
		PublisherBO publisherBO = new PublisherBO();
		
		try {
			publisher = publisherBO.getPublisher(publisher);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Preencher Dados", "Detalhes: " + e));
		}
		
		return "edit-publisher";
	}
	
	public String editPublisherController(){
		PublisherBO publisherBO = new PublisherBO();
		try {
			publisherBO.alterPublisher(publisher);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Editar", "Detalhes: " + e));
		}
		
		return searchPubliserController();
	}
}
