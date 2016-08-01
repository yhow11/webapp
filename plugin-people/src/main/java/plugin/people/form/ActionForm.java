package plugin.people.form;

import com.inctool.common.annotation.MapUtilField;

public class ActionForm {
	
	@MapUtilField(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
