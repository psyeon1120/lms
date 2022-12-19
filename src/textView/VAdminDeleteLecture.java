package textView;

import java.util.Scanner;

import constants.ConfigT.selectNum;
import control.CLecture;
import valueObject.OLecture;

public class VAdminDeleteLecture {

	private Scanner scanner;
	private CLecture cLecture;

	public VAdminDeleteLecture(Scanner scanner) {
		this.scanner = scanner;
		this.cLecture = new CLecture();
	}

	public void show(String fileName, OLecture oLecture) {
		System.out.println("---------------------강좌 삭제----------------------");
		while (true) {
			System.out.println(oLecture.getId() + " " + oLecture.getName() + " 강좌를 삭제하시겠습니까?");
			System.out.println("(1)예 (2)아니오");
			String YNInput = this.scanner.next();
			if (YNInput.equals(selectNum.ONE)) {
				this.cLecture.deleteLecture(fileName, oLecture);
				System.out.println("강좌를 삭제했습니다.");
				break;
			} else if (YNInput.equals(selectNum.TWO)) {
				return;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			}
		}
	}

}
