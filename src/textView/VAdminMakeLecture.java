package textView;

import java.util.Scanner;

import constants.ConfigT.basicCredit;
import constants.ConfigT.moveType;
import control.CLecture;
import valueObject.OLecture;

public class VAdminMakeLecture {

	private Scanner scanner;
	private CLecture cLecture;

	public VAdminMakeLecture(Scanner scanner) {
		this.scanner = scanner;
		this.cLecture = new CLecture();
	}

	public void show(String fileName) {
		while (true) {
			System.out.println("---------------------강좌 추가----------------------");
			System.out.println("(B)뒤로가기");
			OLecture lecture = new OLecture();
			System.out.print("강좌번호 : ");
			String id = this.scanner.next();
			if (id.equals(moveType.BIGBACK) || id.equals(moveType.SMALLBACK))
				return;
			lecture.setId(id);
			System.out.print("강좌명 : ");
			lecture.setName(this.scanner.next());
			System.out.print("교수명 : ");
			lecture.setProfessor(this.scanner.next());
			System.out.print("학점 : ");
			lecture.setCredit(this.scanner.next());
			System.out.print("시간 : ");
			lecture.setTime(this.scanner.next());
			lecture.setMiriPerson(basicCredit.BASICCREDIT);
			lecture.setSincheongPerson(basicCredit.BASICCREDIT);
			System.out.print("정원 : ");
			lecture.setTO(this.scanner.next());

			if (!this.cLecture.findLecture(fileName, lecture)) {
				this.cLecture.makeLecture(fileName, lecture);
				System.out.println("강의를 추가했습니다.");
				break;
			} else {
				System.out.println("동일한 강좌번호가 존재합니다. 다시 입력해주세요.");
			}
		}
	}

}
