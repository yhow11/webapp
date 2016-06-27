package com.ispmint.utilities.podio.object.type;

import java.util.List;

import com.podio.app.Application;
import com.podio.contact.Profile;
import com.podio.file.File;
import com.podio.space.Space;

public class RelationType {

	private int app_item_id;
	private List<File> files;
	private Space space;
	private Application app;
	private String title;
	private Profile created_by;
	private String created_on;
	private String link;
	private int item_id;
	private int revision;
	
	public int getApp_item_id() {
		return app_item_id;
	}
	public void setApp_item_id(int app_item_id) {
		this.app_item_id = app_item_id;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Profile getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Profile created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
	
	
	
}
