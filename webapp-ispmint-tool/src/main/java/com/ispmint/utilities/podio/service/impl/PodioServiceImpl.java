package com.ispmint.utilities.podio.service.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ispmint.utilities.podio.annotation.PodioApplication;
import com.ispmint.utilities.podio.annotation.PodioField;
import com.ispmint.utilities.podio.object.IPodioCredentials;
import com.ispmint.utilities.podio.object.item.TestAppItem;
import com.ispmint.utilities.podio.object.type.CommunicationType;
import com.ispmint.utilities.podio.object.type.DateType;
import com.ispmint.utilities.podio.object.type.MoneyType;
import com.ispmint.utilities.podio.object.type.RelationType;
import com.ispmint.utilities.podio.service.IPodioService;
import com.podio.APIFactory;
import com.podio.ResourceFactory;
import com.podio.app.CategoryOption;
import com.podio.contact.Profile;
import com.podio.embed.Embed;
import com.podio.file.File;
import com.podio.file.FileAPI;
import com.podio.filter.FilterBy;
import com.podio.filter.FilterByValue;
import com.podio.item.FieldValuesView;
import com.podio.item.ItemAPI;
import com.podio.item.ItemBadge;
import com.podio.item.ItemsResponse;
import com.podio.item.map.MappedItemAPI;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUsernameCredentials;
import com.podio.org.OrgAPI;
import com.podio.org.OrganizationWithSpaces;
import com.podio.space.Space;
import com.podio.space.SpaceAPI;
import com.podio.user.User;
import com.podio.user.UserAPI;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PodioServiceImpl implements IPodioService {

	private static final Logger logger = Logger.getLogger(PodioServiceImpl.class);
	
	@Value("${podio.clientSecret}") private String clientSecret;
	@Value("${podio.clientID}") private String clientID; 
	
	private APIFactory apiFactory;

	
	public APIFactory getApiFactory() {
		return this.apiFactory;
	}
	
	@Override
	public void setCredentials(IPodioCredentials iPodioCredentialsForm) throws Exception {
		// TODO Auto-generated method stub
		
		ResourceFactory resourceFactory = new ResourceFactory(
		        new OAuthClientCredentials(clientID, clientSecret),
		        new OAuthUsernameCredentials(iPodioCredentialsForm.getUsername(), iPodioCredentialsForm.getPassword()));
		
		this.apiFactory  = new APIFactory(resourceFactory);
		
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return apiFactory.getAPI(UserAPI.class).getUser();
	}

	@Override
	public Profile getProfile() {
		// TODO Auto-generated method stub
		return apiFactory.getAPI(UserAPI.class).getProfile();
	}

	@Override
	public Space getSpace(int id) {
		// TODO Auto-generated method stub
		return apiFactory.getAPI(SpaceAPI.class).getSpace(id);
	}

	@Override
	public List<OrganizationWithSpaces> getOrganization() {
		// TODO Auto-generated method stub
		return apiFactory.getAPI(OrgAPI.class).getOrganizations();
	}

	@Override
	public File getFile(int id) {
		// TODO Auto-generated method stub
		return apiFactory.getAPI(FileAPI.class).getFile(id);
	}

	private <T> MappedItemAPI<T> getMappedAPI(int appID, Class<T> cls) {
		return new MappedItemAPI<T>(apiFactory, appID, cls);
	}


	@Override
	public <T> T getItem(int id, int appID, Class<T> clazz) {
		// TODO Auto-generated method stub
		return  getMappedAPI(appID, clazz).get(id);
	}
	
	private <T> T map(ItemBadge itemBadge, Class<T> clazz) throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		T newInstance = clazz.newInstance();
		for(FieldValuesView fieldValuesView: itemBadge.getFields()) {
			newInstance = map(fieldValuesView, beanInfo, clazz, newInstance);
		}
		return newInstance;
	}
	private <T> T map(FieldValuesView fieldValuesView, BeanInfo beanInfo, Class<T> clazz, T instance) throws Exception{
		logger.info(fieldValuesView.getLabel());
		Gson gson = new Gson(); 
		switch(fieldValuesView.getType()) {
			case TEXT:case CALCULATION:case LOCATION:
				List<String> texts = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					String text = (String) map.get("value");
					texts.add(text);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, texts);
				
				break;
			case CATEGORY:
				List<CategoryOption> categories = new ArrayList<>();
				
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					CategoryOption category = gson.fromJson(gson.toJson(map.get("value")), CategoryOption.class);
					categories.add(category);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, categories);
			
				break;
			case DATE:
				List<DateType> dates = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					DateType date = gson.fromJson(gson.toJson(map), DateType.class);
					dates.add(date);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, dates);
				
				break;
			case APP:
				List<RelationType> relations = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					RelationType relation = gson.fromJson(gson.toJson(map.get("value")), RelationType.class);
					relations.add(relation);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, relations);
				
				break;
			case CONTACT:
				List<Profile> contacts = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					Profile contact = gson.fromJson(gson.toJson(map.get("value")), Profile.class);
					contacts.add(contact);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, contacts);
				
				break;
			case PHONE:case EMAIL:
				List<CommunicationType> communs = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					CommunicationType commun = gson.fromJson(gson.toJson(map), CommunicationType.class);
					communs.add(commun);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, communs);
				
				break;
			case EMBED:
				List<Embed> embeds = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					Embed embed = gson.fromJson(gson.toJson(map.get("value")), Embed.class);
					embeds.add(embed);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, embeds);
				
				break;
			case MONEY:
				List<MoneyType> monies = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					MoneyType money = gson.fromJson(gson.toJson(map), MoneyType.class);
					monies.add(money);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, monies);
				
				break;
			case PROGRESS:case DURATION:
				List<Integer> progresses = new ArrayList<>();
				for(Map<String, ?> map: fieldValuesView.getValues()) {
					Integer progress = (Integer) map.get("value");
					progresses.add(progress);
				}
				instance = setValues(fieldValuesView.getLabel(), beanInfo, instance, progresses);
				
				break;
		} 
		String json = gson.toJson(fieldValuesView.getValues());
		logger.info(json);
		return instance;
	}
	private <T> T setValues(String label, BeanInfo beanInfo, T instance, List<?> values) throws Exception{
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            if (pd.getWriteMethod() == null) continue;
            if(AnnotationUtils.findAnnotation(pd.getWriteMethod(), PodioField.class).label().equals(label)){
            	System.out.println("\tSetting " + pd.getName());
	            pd.getWriteMethod().invoke(instance, values);
            }
            
        }
		return instance;
	}

	@Override
	public <T> List<T> getItems(Integer id, Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		List<T> results = new ArrayList<T>();
		FilterBy filterBy = new FilterBy<String>() {

			private String key = "id";
			
			@Override
			public String getKey() {
				// TODO Auto-generated method stub
				return key;
			}

			@Override
			public String format(String arg0) {
				// TODO Auto-generated method stub
				return key;
			}
		};
		
		ItemsResponse itemResponse = null;
		
		FilterByValue<Integer> byValue = null;
		if(id != null){
			byValue = new FilterByValue<Integer>(filterBy, id);
			itemResponse = apiFactory.getAPI(ItemAPI.class).getItems(AnnotationUtils.findAnnotation(clazz, PodioApplication.class).id(), null, null, null, null, byValue);
		} else {
			itemResponse = apiFactory.getAPI(ItemAPI.class).getItems(AnnotationUtils.findAnnotation(clazz, PodioApplication.class).id(), null, null, null, null);
		}
		
		
		
		List<ItemBadge> itemBadges =  itemResponse.getItems();
		for(ItemBadge itemBadge: itemBadges) {
			results.add(map(itemBadge, clazz));
		}
		return (List<T>) results;
	}

	@Override
	public <T> List<T> getItems(Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return getItems(null, clazz);
	}
}
