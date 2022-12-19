package textView;

import java.util.Scanner;

import constants.ConfigT.selectNum;

public class VExit {
	private Scanner scanner;
	
	public VExit(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public boolean show() {
		System.out.println("수강신청 프로그램을 종료하시겠습니까?");
		while (true) {
			System.out.println("(1)예 (2)아니오");
			String exitInput = this.scanner.next();
			if (exitInput.equals(selectNum.ONE)) {
				System.out.println("수강신청 프로그램을 종료합니다.");
				return true;
			} else if (exitInput.equals(selectNum.TWO))
				return false;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
