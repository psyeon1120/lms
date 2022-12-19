package dataAccessObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.pathName;
import model.MLecture;
import valueObject.OLecture;

public class DLecture {
	private MLecture mLecture;

	public Vector<OLecture> readAll(String fileName) {
		Vector<OLecture> lectures = new Vector<OLecture>();
		try {
			File file = new File(pathName.LECTUREPATHNAME + fileName);
			Scanner scanner = new Scanner(file);
			this.mLecture = new MLecture();
			while (this.mLecture.read(scanner)) {
				OLecture oLecture = new OLecture();
				oLecture.set(mLecture);
				lectures.add(oLecture);
			}
			return lectures;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}

	// 파일에서 id를 이용해 하나의 강좌 읽기
	public OLecture readOne(String fileName, String lectureId) {
		OLecture lecture = new OLecture();
		try {
			File file = new File(pathName.LECTUREPATHNAME + fileName);
			Scanner scanner = new Scanner(file);
			this.mLecture = new MLecture();
			while (this.mLecture.read(scanner)) {
				if (this.mLecture.getId().equals(lectureId)) {
					lecture.set(this.mLecture);
				}
			}
			return lecture;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}

	// 신청 or 미리담기 인원 업데이트
	public void changePerson(FileWriter fileWriter, String fileName, Vector<OLecture> lectures) {
		try {
			new FileOutputStream(pathName.LECTUREPATHNAME + fileName).close();
			for (OLecture lecture : lectures) {
				this.mLecture.save(fileWriter, lecture);
			}
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}

	// 해당 파일에 해당 강좌 존재 여부 확인
	public boolean find(String fileName, OLecture olecture) {
		Vector<OLecture> lectures = this.readAll(fileName);
		for (OLecture lecture : lectures) {
			if (lecture.getId().equals(olecture.getId()))
				return true;
		}
		return false;
	}

	// 새로운 강좌 저장하기
	public void save(String fileName, OLecture lecture) {
		try {
			File file = new File(pathName.LECTUREPATHNAME + fileName);
			FileWriter fw = new FileWriter(file, true);
			this.mLecture.save(fw, lecture);
		} catch (IOException e) {
//			e.printStackTrace();
		}

	}

	// 해당 강좌 삭제하기
	public void delete(String fileName, OLecture olecture) {
		Vector<OLecture> lectures = this.readAll(fileName);
		int index = 0;
		for (OLecture lecture : lectures) {
			if (lecture.getId().equals(olecture.getId()))
				break;
			index++;
		}
		lectures.remove(index);

		try {
			File file = new File(pathName.LECTUREPATHNAME + fileName);
			FileWriter fw = new FileWriter(file);
			for (OLecture lecture : lectures) {
				this.mLecture.save(fw, lecture);
			}
		} catch (IOException e) {
//			e.printStackTrace();
		}

	}
}
