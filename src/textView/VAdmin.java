package textView;

import java.util.Scanner;

import constants.ConfigT.indexType;
import constants.ConfigT.justString;
import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VAdmin {
	private Scanner scanner;
	private VIndex vCampus;
	private VIndex vCollege;
	private VIndex vDepartment;
	private VLecture vLecture;
	private VAdminDeleteLecture vAdminDeleteLecture;
	private VLogout vLogout;

	public VAdmin(Scanner scanner) {
		this.scanner = scanner;
		this.vCampus = new VIndex(this.scanner);
		this.vCollege = new VIndex(this.scanner);
		this.vDepartment = new VIndex(this.scanner);
		this.vLecture = new VLecture(this.scanner);
		this.vAdminDeleteLecture=new VAdminDeleteLecture(this.scanner);
		this.vLogout=new VLogout(this.scanner);
	}

	public void show(OHwewon oHwewon) {
		while (true) {
			System.out.println("---------------관리자 환경---------------");
			System.out.println("(1)강좌 추가 & 삭제(폐강) (2)로그아웃");
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
								System.out.println("(1)삭제 (C)취소");
								String nInput = this.scanner.next();
								if (nInput.equals(selectNum.ONE)) {
									this.vAdminDeleteLecture.show(departmentFileName, oLecture);
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
