package valueObject;

import model.MLecture;

public class OLecture {
	private String id;
	private String name;
	private String professor;
	private String credit;
	private String time;
	private String miriPerson;
	private String sincheongPerson;
	private String TO;
	
	public void set(MLecture mLecture) {
		this.id = mLecture.getId();
		this.name = mLecture.getName();
		this.professor = mLecture.getProfessor();
		this.credit = mLecture.getCredit();
		this.time = mLecture.getTime();
		this.miriPerson = mLecture.getMiriPerson();
		this.sincheongPerson = mLecture.getSincheongPerson();
		this.TO = mLecture.getTO();
	}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getProfessor() {return professor;}
	public void setProfessor(String professor) {this.professor = professor;}
	public String getCredit() {return credit;}
	public void setCredit(String credit) {this.credit = credit;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	public String getMiriPerson() {return miriPerson;}
	public void setMiriPerson(String miriPerson) {this.miriPerson = miriPerson;}
	public String getSincheongPerson() {return sincheongPerson;}
	public void setSincheongPerson(String sincheongPerson) {this.sincheongPerson = sincheongPerson;}
	public String getTO() {return TO;}
	public void setTO(String tO) {TO = tO;}
	
}
