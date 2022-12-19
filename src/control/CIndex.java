package control;

import java.util.Vector;

import dataAccessObject.DIndex;
import valueObject.OIndex;

public class CIndex {

	private DIndex dindex;

	public CIndex() {
		this.dindex = new DIndex();
	}

	public Vector<OIndex> getAll(String fileName){
		Vector<OIndex> indices=this.dindex.readAll(fileName);
		return indices;
	}
}
