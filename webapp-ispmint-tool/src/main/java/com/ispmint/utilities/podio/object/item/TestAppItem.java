package com.ispmint.utilities.podio.object.item;

import java.util.ArrayList;
import java.util.List;

import com.ispmint.utilities.podio.annotation.PodioApplication;
import com.ispmint.utilities.podio.annotation.PodioField;
import com.ispmint.utilities.podio.object.type.CommunicationType;
import com.ispmint.utilities.podio.object.type.DateType;
import com.ispmint.utilities.podio.object.type.MoneyType;
import com.ispmint.utilities.podio.object.type.RelationType;
import com.podio.app.CategoryOption;
import com.podio.contact.Profile;
import com.podio.embed.Embed;

@PodioApplication(id=15183204)
public class TestAppItem {

	private List<String> text = new ArrayList<>();
	private List<DateType> date= new ArrayList<>();
	private List<CategoryOption> category= new ArrayList<>();
	private List<RelationType> relation= new ArrayList<>();
	private List<Profile> contact = new ArrayList<>();
	private List<CommunicationType> phone = new ArrayList<>();
	private List<CommunicationType> emil = new ArrayList<>();
	private List<Embed> link = new ArrayList<>();
	private List<MoneyType> money = new ArrayList<>();
	private List<Integer> progress = new ArrayList<>();
	private List<String> calculation = new ArrayList<>();
	private List<String> location = new ArrayList<>();
	private List<Integer> duration = new ArrayList<>();
	
	public List<String> getText() {
		return text;
	}
	@PodioField(label="Text")
	public void setText(List<String> text) {
		this.text = text;
	}
	public List<DateType> getDate() {
		return date;
	}
	@PodioField(label="Category")
	public void setDate(List<DateType> date) {
		this.date = date;
	}
	public List<CategoryOption> getCategory() {
		return category;
	}
	@PodioField(label="Date")
	public void setCategory(List<CategoryOption> category) {
		this.category = category;
	}
	public List<RelationType> getRelation() {
		return relation;
	}
	@PodioField(label="Relationship")
	public void setRelation(List<RelationType> relation) {
		this.relation = relation;
	}
	public List<Profile> getContact() {
		return contact;
	}
	@PodioField(label="Contact")
	public void setContact(List<Profile> contact) {
		this.contact = contact;
	}
	public List<CommunicationType> getPhone() {
		return phone;
	}
	@PodioField(label="Phone")
	public void setPhone(List<CommunicationType> phone) {
		this.phone = phone;
	}
	public List<CommunicationType> getEmil() {
		return emil;
	}
	@PodioField(label="Email")
	public void setEmil(List<CommunicationType> emil) {
		this.emil = emil;
	}
	public List<Embed> getLink() {
		return link;
	}
	@PodioField(label="Link")
	public void setLink(List<Embed> link) {
		this.link = link;
	}
	public List<MoneyType> getMoney() {
		return money;
	}
	@PodioField(label="Money")
	public void setMoney(List<MoneyType> money) {
		this.money = money;
	}
	public List<Integer> getProgress() {
		return progress;
	}
	@PodioField(label="Progress")
	public void setProgress(List<Integer> progress) {
		this.progress = progress;
	}
	public List<String> getCalculation() {
		return calculation;
	}
	@PodioField(label="Calculation")
	public void setCalculation(List<String> calculation) {
		this.calculation = calculation;
	}
	public List<String> getLocation() {
		return location;
	}
	@PodioField(label="Location")
	public void setLocation(List<String> location) {
		this.location = location;
	}
	public List<Integer> getDuration() {
		return duration;
	}
	@PodioField(label="Duration")
	public void setDuration(List<Integer> duration) {
		this.duration = duration;
	}
	
}
