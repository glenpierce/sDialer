package com.piercestudio.sdialer;

/**
 * Created by User on 10/17/15.
 */
public class Contact implements Comparable<Contact>
{
	private String name;
	private String phoneNumber;

	Contact(){
	}

	public String getName(){
		return name;
	}

	public void setName(String newName){
		name = newName;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String newPhoneNumber){
		phoneNumber = newPhoneNumber;
	}

	public int compareTo(Contact another){
		return this.getName().compareToIgnoreCase(another.getName());
	}

}
