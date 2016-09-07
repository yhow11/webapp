package service.membermanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import common.mapper.annotation.MapUtilField;

@Document(collection="INCWorkerCollection")
public class INCWorkerModel extends PersonModel {

	@Id
	@MapUtilField
	private String id;
	@Field
	@MapUtilField
	private String status; 
	@Field
	@MapUtilField
	private Date createdDate; 
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}

