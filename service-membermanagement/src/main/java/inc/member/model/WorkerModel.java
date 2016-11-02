package inc.member.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="INCWorkerCollection")
public class WorkerModel{

	@Id
	private String id;
	@Field
	private String name;
	@Field
	private String status; 
	@Field
	private Date createdDate; 
	
	private List<OptionModel> options;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OptionModel> getOptions() {
		return options;
	}
	public void setOptions(List<OptionModel> options) {
		this.options = options;
	}
	
}

