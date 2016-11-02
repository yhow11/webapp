package usertracker.browser.browserfp.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="browserFP")
public class BrowserFPModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String ID;
	
	@PhoenixColumn(type="VARCHAR(255)")
	private String ANONYMOUSVISITORID;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getANONYMOUSVISITORID() {
		return ANONYMOUSVISITORID;
	}

	public void setANONYMOUSVISITORID(String aNONYMOUSVISITORID) {
		ANONYMOUSVISITORID = aNONYMOUSVISITORID;
	}

}
