package textView;

import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.failType;
import constants.ConfigT.selectNum;
import control.CBackpack;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VMiri_Sincheong {
	private Scanner scanner;
	private CBackpack cBackpack;

	public VMiri_Sincheong(Scanner scanner) {
		this.scanner = scanner;
		this.cBackpack = new CBackpack();
	}

	// 강좌탐색에서 신청할 때
	public void show(OHwewon oHwewon, OLecture lecture, String message) {
		while (true) {
			System.out.println(lecture.getName() + " " + lecture.getProfessor() + " " + lecture.getTime() + "을(를) "
					+ message + "하시겠습니까?");
			System.out.println("(1)예 (2)아니오");

			String input = this.scanner.next();
			if (input.equals(selectNum.ONE)) {
				String suc = this.cBackpack.save(oHwewon, lecture, message);
				if (suc == failType.CREDITFAIL)
					System.out.println(message + " 가능 학점을 초과했습니다.");
				else if (suc == failType.ALREADYFAIL)
					System.out.println("이미 신청된 과목입니다.");
				else if (suc == failType.FULLFAIL)
					System.out.println("신청 인원 초과입니다.");
				else if (suc == failType.MALREADYFAIL)
					System.out.println("이미 미리담기된 과목입니다.");
				else
					System.out.println(message + "됐습니다.");
				break;
			} else if (input.equals(selectNum.TWO)) {
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	// 책가방에서 신청이나 삭제할 때
	public void show(OHwewon oHwewon, OLecture lecture, String message, String type, Vector<OLecture> lists) {
		while (true) {
			System.out.println(lecture.getName() + " " + lecture.getProfessor() + " " + lecture.getTime() + "을(를) "
					+ message + "하시겠습니까?");
			System.out.println("(1)예 (2)아니오");

			String input = this.scanner.next();
			if (input.equals(selectNum.ONE)) {
				String suc = this.cBackpack.change(oHwewon, lecture, message, type, lists);
				if (suc == failType.CREDITFAIL)
					System.out.println(message + " 가능 학점을 초과했습니다.");
				else if (suc == failType.ALREADYFAIL)
					System.out.println("이미 " + message + "된 과목입니다.");
				else if (suc == failType.FULLFAIL)
					System.out.println("신청 인원 초과입니다.");
				else
					System.out.println(message + "됐습니다.");
				break;
			} else if (input.equals(selectNum.TWO)) {
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

}
