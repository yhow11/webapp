package inc.member.form;

import java.util.ArrayList;
import java.util.List;

public class WorkerForm{

	private String id = "";
	private String name = "";
	private List<OptionForm> options = new ArrayList<OptionForm>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OptionForm> getOptions() {
		return options;
	}
	public void setOptions(List<OptionForm> options) {
		this.options = options;
	}
	
}
