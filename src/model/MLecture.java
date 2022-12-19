package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import valueObject.OLecture;

public class MLecture {
	private String id;
	private String name;
	private String professor;
	private String credit;
	private String time;
	private String miriPerson;
	private String sincheongPerson;
	private String TO;
	
	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			this.name = scanner.next();
			this.professor = scanner.next();
			this.credit = scanner.next();
			this.time = scanner.next();
			this.miriPerson = scanner.next();
			this.sincheongPerson = scanner.next();
			this.TO = scanner.next();
			return true;
		}
		return false;
	}
	
	public void save(FileWriter fileWriter, OLecture lecture) {
		this.set(lecture);
		try {
			fileWriter.write(this.id + " ");
			fileWriter.write(this.name + " ");
			fileWriter.write(this.professor + " ");
			fileWriter.write(this.credit + " ");
			fileWriter.write(this.time + " ");
			fileWriter.write(this.miriPerson + " ");
			fileWriter.write(this.sincheongPerson + " ");
			fileWriter.write(this.TO + '\n');
			fileWriter.flush();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
	private void set(OLecture lecture) {
		this.id=lecture.getId();
		this.name = lecture.getName();
		this.professor = lecture.getProfessor();
		this.credit = lecture.getCredit();
		this.time = lecture.getTime();
		this.miriPerson = lecture.getMiriPerson();
		this.sincheongPerson = lecture.getSincheongPerson();
		this.TO = lecture.getTO();
	}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getProfessor() {return professor;}
	public void setProfessor(String proName) {this.professor = proName;}
	public String getCredit() {return credit;}
	public void setCredit(String hakjum) {this.credit = hakjum;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	public String getMiriPerson() {return miriPerson;}
	public void setMiriPerson(String miriPerson) {this.miriPerson = miriPerson;}
	public String getSincheongPerson() {return sincheongPerson;}
	public void setSincheongPerson(String sincheongPerson) {this.sincheongPerson = sincheongPerson;}
	public String getTO() {return TO;}
	public void setTO(String tO) {TO = tO;}
}
