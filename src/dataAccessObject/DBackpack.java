package dataAccessObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.actionType;
import constants.ConfigT.canCredit;
import constants.ConfigT.failType;
import constants.ConfigT.fileType;
import constants.ConfigT.indexType;
import constants.ConfigT.pathName;
import model.MBackpack;
import model.MHwewon;
import valueObject.OBackpack;
import valueObject.OHwewon;
import valueObject.OIndex;
import valueObject.OLecture;

public class DBackpack {
	private MBackpack mBackpack;
	private MHwewon mHwewon;
	private DIndex dIndex;
	private DLecture dLecture;

	// 수강신청 or 미리담기
	public String saveLecture(OHwewon oHwewon, OLecture oLecture, String message) {
		try {
			int afterSCredit = Integer.parseInt(oHwewon.getSincheongCredit()) + Integer.parseInt(oLecture.getCredit()); // 수강신청
																														// 후의
																														// 신청학점
			int afterMCredit = Integer.parseInt(oHwewon.getMiriCredit()) + Integer.parseInt(oLecture.getCredit()); // 미리담기
																													// 후의
																													// 신청학점
			File file;
			File infoFile = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.HWEWONINFOFILENAME); // 회원정보파일
			String fileName = this.findFileName(oLecture.getId()); // 입력받은 강좌가 존재하는 파일명
			File lectureFile = new File(pathName.LECTUREPATHNAME + fileName); // 입력받은 강좌가 존재하는 파일

			Vector<OLecture> sincheongs = this.readAll(oHwewon.getId(), fileType.SINCHEONG);
			Vector<OLecture> miris = this.readAll(oHwewon.getId(), fileType.MIRI);

			int sincheongPerson = Integer.parseInt(oLecture.getSincheongPerson()); // 현재 입력받은 강좌의 신청 인원
			int TO = Integer.parseInt(oLecture.getTO()); // 현재 입력받은 강좌의 정원
			if (message.equals(actionType.SINCHEONG)) {
				if (sincheongPerson < TO) { // 강좌의 신청인원 초과 여부 확인
					file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.SINCHEONGFILENAME);
					if (afterSCredit <= canCredit.CANSINCHEONGCREDIT) { // 신청가능학점 초과 여부 확인
						if (findLecture(oLecture, sincheongs) == -1) { // 이미 신청한 과목 여부 확인
							oHwewon.setSincheongCredit(Integer.toString(afterSCredit)); // 회원의 신청 학점 증가
							oLecture.setSincheongPerson(
									Integer.toString(Integer.parseInt(oLecture.getSincheongPerson()) + 1)); // 강좌의 신청인원
																											// 증가
							if (this.findLecture(oLecture, miris) != -1) // 신청 과목의 미리담기 존재 여부 확인
								this.deleteLecture(oHwewon, oLecture, actionType.MIRI, miris); // 미리담기 내역에서 신청과목 삭제
						} else
							return failType.ALREADYFAIL; // 이미 신청한 과목
					} else
						return failType.CREDITFAIL; // 신청 가능학점 초과
				} else
					return failType.FULLFAIL; // 수강 인원 초과
			} else {
				file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.MIRIFILENAME);
				if (afterMCredit <= canCredit.CANSINCHEONGCREDIT + canCredit.CANPLUSMIRICREDIT) {
					if (this.findLecture(oLecture, miris) == -1) {
						oHwewon.setMiriCredit(Integer.toString(afterMCredit));
						oLecture.setMiriPerson(Integer.toString(Integer.parseInt(oLecture.getMiriPerson()) + 1));
					} else
						return failType.MALREADYFAIL;
				} else
					return failType.CREDITFAIL;
			}

			FileWriter lFilewriter = new FileWriter(file, true);
			FileWriter iFilewriter = new FileWriter(infoFile);
			FileWriter lecFilewriter = new FileWriter(lectureFile, true);
			this.mHwewon = new MHwewon();
			this.mHwewon.save(iFilewriter, oHwewon); // 회원의 수강신청 or 미리담기 학점 업데이트
			this.mBackpack = new MBackpack();
			this.mBackpack.save(lFilewriter, oLecture); // 수강신청 or 미리담기 과목 저장
			this.setChangePerson(lecFilewriter, fileName, oLecture);

		} catch (IOException e) {
//			e.printStackTrace();
		}
		return null;

	}

	// 강좌의 신청 or 미리담기 인원 업데이트
	public void setChangePerson(FileWriter filewriter, String fileName, OLecture oLecture) {
		this.dLecture = new DLecture();
		Vector<OLecture> lectures = this.dLecture.readAll(fileName);
		for (OLecture lecture : lectures) {
			if (lecture.getId().equals(oLecture.getId())) {
				lecture.setSincheongPerson(oLecture.getSincheongPerson());
				lecture.setMiriPerson(oLecture.getMiriPerson());
			}
		}
		this.dLecture.changePerson(filewriter, fileName, lectures);
	}

	// 강좌 리스트에서 입력받은 강좌의 위치 찾기
	private int findLecture(OLecture lecture, Vector<OLecture> lists) {
		int index = 0;
		for (OLecture list : lists) {
			if (list.getId().equals(lecture.getId()))
				return index;
			index++;
		}
		return -1;
	}

	// 신청 or 미리담기 강좌 삭제
	public void deleteLecture(OHwewon oHwewon, OLecture lecture, String type, Vector<OLecture> lists) {
		lists.remove(this.findLecture(lecture, lists));
		String fileName = this.findFileName(lecture.getId());
		File lectureFile = new File(pathName.LECTUREPATHNAME + fileName);

		try {
			File file;
			File infoFile = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.HWEWONINFOFILENAME);
			if (type.equals(fileType.SINCHEONG)) { // 신청 내역에서 삭제할 때
				file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.SINCHEONGFILENAME);
				oHwewon.setSincheongCredit(Integer.toString(
						(Integer.parseInt(oHwewon.getSincheongCredit()) - Integer.parseInt(lecture.getCredit()))));
				lecture.setSincheongPerson(Integer.toString(Integer.parseInt(lecture.getSincheongPerson()) - 1));

				Vector<OLecture> miris = this.readAll(oHwewon.getId(), fileType.MIRI);
				if (this.findLecture(lecture, miris) == -1) // 신청 취소 과목의 미리담기 존재 여부 확인
					this.saveLecture(oHwewon, lecture, actionType.MIRI); // 미리담기 내역에서 신청과목 삭제
			} else { // 미리담기 내역에서 삭제할 때
				file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.MIRIFILENAME);
				oHwewon.setMiriCredit(Integer
						.toString((Integer.parseInt(oHwewon.getMiriCredit()) - Integer.parseInt(lecture.getCredit()))));
				lecture.setMiriPerson(Integer.toString(Integer.parseInt(lecture.getMiriPerson()) - 1));
			}
			FileWriter lFilewriter = new FileWriter(file);
			FileWriter iFilewriter = new FileWriter(infoFile);
			FileWriter lecFilewriter = new FileWriter(lectureFile, true);

			this.mHwewon = new MHwewon();
			this.mHwewon.save(iFilewriter, oHwewon);
			this.mBackpack = new MBackpack();
			for (int i = 0; i < lists.size(); i++) {
				this.mBackpack.save(lFilewriter, lists.get(i));
			}

			this.setChangePerson(lecFilewriter, fileName, lecture);

		} catch (IOException e) {
//			e.printStackTrace();
		}

	}

	// 입력받은 강좌가 존재하는 파일 찾기
	public String findFileName(String lectureId) {
		String fileName = indexType.ROOT;
		for (int i = 1; i < 4; i++) {
			String id = lectureId.substring(0, i);
			this.dIndex = new DIndex();
			Vector<OIndex> indices = dIndex.readAll(fileName);
			for (OIndex index : indices) {
				if (index.getId().equals(id)) {
					fileName = index.getFileName();
					break;
				}
			}
		}
		return fileName;
	}

	public Vector<OLecture> readAll(String hwewonId, String type) {
		Vector<OLecture> lectures = new Vector<OLecture>();
		Vector<OBackpack> lectureIds = new Vector<OBackpack>();
		try {
			File file;
			if (type.equals(fileType.SINCHEONG)) {
				file = new File(pathName.USERPATHNAME + hwewonId + pathName.SINCHEONGFILENAME);
			} else {
				file = new File(pathName.USERPATHNAME + hwewonId + pathName.MIRIFILENAME);
			}
			Scanner scanner = new Scanner(file);

			// 내역에 있는 강좌 아이디 가져오기
			this.mBackpack = new MBackpack();
			while (this.mBackpack.read(scanner)) {
				OBackpack oBackpack = new OBackpack();
				oBackpack.set(mBackpack);
				lectureIds.add(oBackpack);
			}

			this.dIndex = new DIndex();
			for (OBackpack lectureId : lectureIds) {
				String fileName = this.findFileName(lectureId.getId());
				this.dLecture = new DLecture();
				lectures.add(this.dLecture.readOne(fileName, lectureId.getId()));
			}
			return lectures;
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}

	// 책가방 순서 바꾸기
	public void changeOrder(OHwewon oHwewon, OLecture select, String order, Vector<OLecture> lists) {
		lists.remove(this.findLecture(select, lists));

		lists.add(Integer.parseInt(order) - 1, select);

		try {
			File file = new File(pathName.USERPATHNAME + oHwewon.getId() + pathName.MIRIFILENAME);
			FileWriter filewriter = new FileWriter(file);

			this.mBackpack = new MBackpack();
			for (int i = 0; i < lists.size(); i++) {
				this.mBackpack.save(filewriter, lists.get(i));
			}
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}

}
