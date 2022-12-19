package control;

import java.util.Vector;

import dataAccessObject.DLecture;
import valueObject.OLecture;

public class CLecture {
	private DLecture dLecture;
	public CLecture() {
		this.dLecture = new DLecture();
	}

	public Vector<OLecture> getAll(String fileName){
		Vector<OLecture> lectures=this.dLecture.readAll(fileName);
		return lectures;
	}
	
	public boolean findLecture(String fileName, OLecture lecture) {
		boolean exist=this.dLecture.find(fileName,lecture);
		return exist;
	}
	
	public void makeLecture(String fileName, OLecture lecture) {
		this.dLecture.save(fileName,lecture);
	}
	
	public void deleteLecture(String fileName, OLecture lecture) {
		this.dLecture.delete(fileName,lecture);
	}
}
