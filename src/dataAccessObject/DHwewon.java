package dataAccessObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.basicCredit;
import constants.ConfigT.fileType;
import constants.ConfigT.pathName;
import constants.ConfigT.selectNum;
import model.MHwewon;
import valueObject.OHwewon;
import valueObject.OLecture;

public class DHwewon {

	private MHwewon mHwewon;
	private DBackpack dBackpack;

	// 회원정보 저장
	public boolean save(OHwewon oHwewon) {
		if (findHwewon(oHwewon.getId()) == false) {
			try {
				File folder = new File(pathName.USERPATHNAME + oHwewon.getId());
				folder.mkdir();
				File file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.HWEWONINFOFILENAME);
				File sincheongFile = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.SINCHEONGFILENAME);
				File miriFile = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.MIRIFILENAME);
				sincheongFile.createNewFile();
				miriFile.createNewFile();

				FileWriter filewriter = new FileWriter(file);
				// 수강신청과 미리담기 학점 '0'으로 초기화
				oHwewon.setSincheongCredit(basicCredit.BASICCREDIT);
				oHwewon.setMiriCredit(basicCredit.BASICCREDIT);
				this.mHwewon = new MHwewon();
				this.mHwewon.save(filewriter, oHwewon);
			} catch (IOException e) {
//				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	// 회원 존재 여부 확인
	private boolean findHwewon(String id) {
		File folder = new File(pathName.USERPATHNAME);
		File folders[] = folder.listFiles();
		for (int i = 0; i < folders.length; i++) {
			if (folders[i].toString().substring(10).equals(id)) {
				return true;
			}
		}
		return false;
	}

	// 회원정보 읽기
	public OHwewon read(String id) {
		try {
			File file = new File(pathName.USERPATHNAME + id + pathName.HWEWONINFOFILENAME);
			Scanner scanner = new Scanner(file);
			this.mHwewon = new MHwewon();
			while (this.mHwewon.read(scanner)) {
				if (this.mHwewon.getId().equals(id)) {
					OHwewon oHwewon = new OHwewon();
					oHwewon.set(this.mHwewon);
					return oHwewon;
				}
			}
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}

	// 회원정보 수정
	public void change(OHwewon oHwewon, String infoNum, String inputInfo) {

		if (infoNum.equals(selectNum.ONE))
			oHwewon.setPassword(inputInfo);
		else if (infoNum.equals(selectNum.TWO))
			oHwewon.setName(inputInfo);
		else if (infoNum.equals(selectNum.THREE))
			oHwewon.setAddress(inputInfo);
		else if (infoNum.equals(selectNum.FOUR))
			oHwewon.setHakgwa(inputInfo);

		try {
			File file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.HWEWONINFOFILENAME);
			FileWriter filewriter = new FileWriter(file);
			this.mHwewon = new MHwewon();
			this.mHwewon.save(filewriter, oHwewon);
		} catch (IOException e) {
//			e.printStackTrace();
		}

	}

	// 회원 삭제
	public void delete(OHwewon oHwewon) {
		File folder = new File(pathName.USERPATHNAME + oHwewon.getId());
		File files[] = folder.listFiles();

		// 회원 삭제할 때 해당 회원이 미리담기와 신청한 과목의 각 해당 인원을 1씩 감소
		this.dBackpack = new DBackpack();
		FileWriter lecFilewriter;

		Vector<OLecture> miris = this.dBackpack.readAll(oHwewon.getId(), fileType.MIRI);
		for (OLecture miri : miris) {
			try {
				String fileName = this.dBackpack.findFileName(miri.getId());
				File lectureFile = new File(pathName.LECTUREPATHNAME + fileName);
				miri.setMiriPerson(Integer.toString(Integer.parseInt(miri.getMiriPerson()) - 1));
				lecFilewriter = new FileWriter(lectureFile, true);
				this.dBackpack.setChangePerson(lecFilewriter, fileName, miri);
			} catch (IOException e) {
//				e.printStackTrace();
			}

		}

		Vector<OLecture> sincheongs = this.dBackpack.readAll(oHwewon.getId(), fileType.SINCHEONG);
		for (OLecture sincheong : sincheongs) {
			try {
				String fileName = this.dBackpack.findFileName(sincheong.getId());
				File lectureFile = new File(pathName.LECTUREPATHNAME + fileName);
				sincheong.setSincheongPerson(Integer.toString(Integer.parseInt(sincheong.getSincheongPerson()) - 1));
				lecFilewriter = new FileWriter(lectureFile, true);
				this.dBackpack.setChangePerson(lecFilewriter, fileName, sincheong);
				lecFilewriter.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}

		}
		
		for (int i = 0; i < files.length; i++) {
			while (files[i].delete() != true)
				System.gc();
		}
		folder.delete();
	}

}
