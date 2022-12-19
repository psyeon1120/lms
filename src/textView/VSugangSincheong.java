package textView;

import java.util.Scanner;

import constants.ConfigT.actionType;
import constants.ConfigT.indexType;
import constants.ConfigT.justString;
import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VSugangSincheong {

	private Scanner scanner;
	private VBackpack vBackpack;
	private VIndex vCampus;
	private VIndex vCollege;
	private VIndex vDepartment;
	private VLecture vLecture;
	private VMiri_Sincheong vMiri_Sincheong;
	private VChangeHwewon vChangeHwewon;
	private VLeaveHwewon vLeaveHwewon;
	private VLogout vLogout;

	public VSugangSincheong(Scanner scanner) {
		this.scanner = scanner;
		this.vBackpack = new VBackpack(this.scanner);
		this.vCampus = new VIndex(this.scanner);
		this.vCollege = new VIndex(this.scanner);
		this.vDepartment = new VIndex(this.scanner);
		this.vLecture = new VLecture(this.scanner);
		this.vMiri_Sincheong = new VMiri_Sincheong(this.scanner);
		this.vChangeHwewon = new VChangeHwewon(this.scanner);
		this.vLeaveHwewon = new VLeaveHwewon(this.scanner);
		this.vLogout = new VLogout(this.scanner);
	}

	public void show(OHwewon oHwewon) {
		while (true) {
			System.out.println("---------------" + oHwewon.getName() + "님의 수강신청 프로그램---------------");
			System.out.println("(1)강좌 탐색 (2)책가방 (3)회원정보수정 (4)회원탈퇴 (5)로그아웃");
			String input = this.scanner.next();
			if (input.equals(selectNum.ONE)) {
				String campusFileName = justString.NONE;
				String collgeFileName = justString.NONE;
				String departmentFileName = justString.NONE;
				while (true) {
					if (campusFileName == justString.NONE) {
						campusFileName = this.vCampus.show(indexType.ROOT, indexType.CAMPUS);
						if (campusFileName == justString.NONE || campusFileName.equals(justString.CANCLE))
							break;
					}
					if (campusFileName != justString.NONE && collgeFileName == justString.NONE) {
						collgeFileName = this.vCollege.show(campusFileName, indexType.COLLEGE);
						if (collgeFileName == justString.NONE)
							campusFileName = justString.NONE;
					}
					if (collgeFileName != justString.CANCLE && collgeFileName != justString.NONE
							&& departmentFileName == justString.NONE) {
						departmentFileName = this.vDepartment.show(collgeFileName, indexType.DEPARTMENT);
						if (departmentFileName == justString.NONE)
							collgeFileName = justString.NONE;
					}
					if (departmentFileName != justString.CANCLE && departmentFileName != justString.NONE) {
						OLecture oLecture = this.vLecture.show(departmentFileName, oHwewon);
						if (oLecture != null) {
							while (true) {
								System.out.println("(1)신청 (2)미리담기 (C)취소");
								String nInput = this.scanner.next();
								if (nInput.equals(selectNum.ONE)) {
									this.vMiri_Sincheong.show(oHwewon, oLecture, actionType.SINCHEONG);
									break;
								} else if (nInput.equals(selectNum.TWO)) {
									this.vMiri_Sincheong.show(oHwewon, oLecture, actionType.MIRI);
									break;
								} else if (nInput.equals(moveType.BIGCANCEL) || nInput.equals(moveType.SMALLCANCEL))
									break;
								else {
									System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
								}
							}
						} else
							departmentFileName = justString.NONE;
					}
					if (campusFileName.equals(justString.CANCLE) || collgeFileName.equals(justString.CANCLE)
							|| departmentFileName.equals(justString.CANCLE)) {
						campusFileName = justString.NONE;
						collgeFileName = justString.NONE;
						departmentFileName = justString.NONE;
						break;
					}

				}
			} else if (input.equals(selectNum.TWO)) {
				this.vBackpack.show(oHwewon);
			} else if (input.equals(selectNum.THREE)) {
				this.vChangeHwewon.show(oHwewon);
			} else if (input.equals(selectNum.FOUR)) {
				if (this.vLeaveHwewon.show(oHwewon))
					return;
			} else if (input.equals(selectNum.FIVE)) {
				if (this.vLogout.show(oHwewon) == null) {
					oHwewon = null;
					return;
				}
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

}
