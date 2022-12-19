package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import valueObject.OLecture;

public class MBackpack {
	private String id;
	
	public void set(OLecture lecture) {
		this.id=lecture.getId();
	}

	public void save(FileWriter filewriter, OLecture lecture) {
		this.set(lecture);
		try {
			filewriter.write(id+"\n");
			filewriter.flush();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			return true;
		}
		return false;
	}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
}
