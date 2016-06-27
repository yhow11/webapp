package com.ispmint.utilities.podio.object.item;

import java.util.Collection;

import com.podio.item.map.ExternalId;

public class TriggerItem {

	private int externalID;
	private Collection<String> activity;
	private int organizationID;
	private int workspaceID;
	private int applicationID;
	private int viewID;
	
	private Collection<String> apps;
	private Collection<String> actions;
	
	@ExternalId
	public int getExternalID() {
		return externalID;
	}
	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}
	public Collection<String> getActivity() {
		return activity;
	}
	public void setActivity(Collection<String> activity) {
		this.activity = activity;
	}
	public int getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}
	public int getWorkspaceID() {
		return workspaceID;
	}
	public void setWorkspaceID(int workspaceID) {
		this.workspaceID = workspaceID;
	}
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public int getViewID() {
		return viewID;
	}
	public void setViewID(int viewID) {
		this.viewID = viewID;
	}
	public Collection<String> getApps() {
		return apps;
	}
	public void setApps(Collection<String> apps) {
		this.apps = apps;
	}
	public Collection<String> getActions() {
		return actions;
	}
	public void setActions(Collection<String> actions) {
		this.actions = actions;
	}
	
	
	
}
