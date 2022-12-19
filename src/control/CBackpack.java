package control;

import java.util.Vector;

import constants.ConfigT.actionType;
import dataAccessObject.DBackpack;
import valueObject.OHwewon;
import valueObject.OLecture;

public class CBackpack {
	private DBackpack dBackpack;

	public CBackpack() {
		this.dBackpack = new DBackpack();
	}

	// 수강신청 or 미리담기 저장
	public String save(OHwewon oHwewon, OLecture lecture, String message) {
		String suc = this.dBackpack.saveLecture(oHwewon, lecture, message);
		return suc;
	}

	// 책가방에서 신청 or 삭제
	public String change(OHwewon oHwewon, OLecture lecture, String message, String type, Vector<OLecture> lists) {
		if (message.equals(actionType.DELETE)) {  // 수강신청 or 미리담기 삭제
			this.dBackpack.deleteLecture(oHwewon, lecture, type, lists);
			return null;
		} else {  // 미리담기에서 수강신청
			String suc = this.dBackpack.saveLecture(oHwewon, lecture, message);
			return suc;
		}
	}

	// 책가방 내역 가져오기
	public Vector<OLecture> getAll(String hwewonId, String type) {
		Vector<OLecture> lists = this.dBackpack.readAll(hwewonId, type);
		return lists;
	}

	// 미리담기 내역 순서 바꾸기
	public void changeOrder(OHwewon oHwewon, OLecture select, String order, Vector<OLecture> lists) {
		this.dBackpack.changeOrder(oHwewon, select, order, lists);

	}

}
