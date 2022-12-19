package dataAccessObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.pathName;
import model.MIndex;
import valueObject.OIndex;

public class DIndex {

	private MIndex mIndex;

	public Vector<OIndex> readAll(String fileName) {
		Vector<OIndex> indices = new Vector<OIndex>();
		try {
			File file = new File(pathName.LECTUREPATHNAME + fileName);
			Scanner scanner = new Scanner(file);
			this.mIndex = new MIndex();
			while (this.mIndex.read(scanner)) {
				OIndex oIndex = new OIndex();
				oIndex.set(this.mIndex);
				indices.add(oIndex);
			}
			return indices;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}

}
