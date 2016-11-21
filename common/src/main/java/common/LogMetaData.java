package common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogMetaData {

	private String id;
	private List<String> transactions = new ArrayList<>();
	
	public LogMetaData(String transaction) {
		// TODO Auto-generated constructor stub
		this.id = UUID.randomUUID().toString();
		transactions.add(transaction);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
