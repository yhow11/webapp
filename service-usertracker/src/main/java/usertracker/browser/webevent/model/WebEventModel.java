package usertracker.browser.webevent.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="webEvent")
public class WebEventModel implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	protected Long ID;

	@PhoenixColumn(type="VARCHAR(255)")
	protected String ANONYMOUSVISITORID;

	@PhoenixColumn(type="VARCHAR(255)")
	protected String BROWSERFPID;

	@PhoenixColumn(type="VARCHAR(255)")
	protected String DEVICEFPID;

	@PhoenixColumn(type="BIGINT")
	protected Long TIMESTAMP;
	
	@PhoenixColumn(type="VARCHAR(255)")
	protected String TYPE;
	
	@PhoenixColumn(type="VARCHAR(255)")
	protected String URL;
	
	@PhoenixColumn(type="VARCHAR(255)")
	protected String TITLE;

	@PhoenixColumn(type="VARCHAR(255)")
	protected String ELAPSEDTIME;

	@PhoenixColumn(type="VARCHAR(255)")
	protected String COUNTRY;
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getANONYMOUSVISITORID() {
		return ANONYMOUSVISITORID;
	}

	public void setANONYMOUSVISITORID(String aNONYMOUSVISITORID) {
		ANONYMOUSVISITORID = aNONYMOUSVISITORID;
	}

	public String getBROWSERFPID() {
		return BROWSERFPID;
	}

	public void setBROWSERFPID(String bROWSERFPID) {
		BROWSERFPID = bROWSERFPID;
	}

	public String getDEVICEFPID() {
		return DEVICEFPID;
	}

	public void setDEVICEFPID(String dEVICEFPID) {
		DEVICEFPID = dEVICEFPID;
	}

	public Long getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Long tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getELAPSEDTIME() {
		return ELAPSEDTIME;
	}

	public void setELAPSEDTIME(String eLAPSEDTIME) {
		ELAPSEDTIME = eLAPSEDTIME;
	}
	
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}

}
