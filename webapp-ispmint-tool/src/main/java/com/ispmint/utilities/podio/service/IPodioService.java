package com.ispmint.utilities.podio.service;

import java.util.List;

import com.ispmint.utilities.podio.object.IPodioCredentials;
import com.podio.APIFactory;
import com.podio.contact.Profile;
import com.podio.file.File;
import com.podio.org.OrganizationWithSpaces;
import com.podio.space.Space;
import com.podio.user.User;

public interface IPodioService {

	public APIFactory getApiFactory();
	public void setCredentials(IPodioCredentials iPodioCredentialsForm) throws Exception;
	public User getUser();
	public Profile getProfile();
	public Space getSpace(int id);
	public File getFile(int id);
	public List<OrganizationWithSpaces> getOrganization();
	public <T> T getItem( int id, int appID, Class<T> clazz);
	public <T> List<T> getItems(Integer id, Class<T> clazz)throws Exception;
	public <T> List<T> getItems(Class<T> clazz)throws Exception;
}
