package common.query;

import java.util.List;

public class QueryResponse<E> {

	private List<E> results;
	
	public List<E> getResults() {
		return results;
	}
	public void setResults(List<E> results) {
		this.results = results;
	}
	
}
