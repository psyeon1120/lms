package textView;

import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.admin;
import constants.ConfigT.moveType;
import control.CLecture;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VLecture {

	private Scanner scanner;
	private CLecture cLecture;
	private VList vList;
	private VAdminMakeLecture vAdminMakeLecture;

	public VLecture(Scanner scanner) {
		this.scanner = scanner;
		this.cLecture = new CLecture();
		this.vList = new VList();
		this.vAdminMakeLecture=new VAdminMakeLecture(this.scanner);
	}

	public OLecture show(String fileName, OHwewon oHwewon) {
		System.out.println("---------------------강좌 선택----------------------");
		System.out.println("(B)뒤로가기");
		Vector<OLecture> lectures = cLecture.getAll(fileName);
		this.vList.show(lectures);
		if(oHwewon.getId().equals(admin.ADMINID)) {
			System.out.println("(#)강좌추가");
		}
		while (true) {
			String input = this.scanner.next();
			if (input.equals(moveType.BIGBACK) || input.equals(moveType.SMALLBACK))
				return null;
			if(oHwewon.getId().equals(admin.ADMINID)) {
				if(input.equals(admin.MAKELECTURE)) {
					this.vAdminMakeLecture.show(fileName);
					return null;
				}
			}
			for (OLecture lecture : lectures) {
				if (lecture.getId().equals(input)) {
					return lecture;
				}
			}
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
