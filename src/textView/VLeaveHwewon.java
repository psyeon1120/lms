package textView;

import java.util.Scanner;

import constants.ConfigT.selectNum;
import control.CHwewon;
import valueObject.OHwewon;

public class VLeaveHwewon {

	private Scanner scanner;
	private CHwewon cHwewon;

	public VLeaveHwewon(Scanner scanner) {
		this.scanner = scanner;
		this.cHwewon = new CHwewon();
	}

	public boolean show(OHwewon oHwewon) {
		System.out.println("---------------회원탈퇴---------------");
		while (true) {
			System.out.println("정말로 탈퇴하시겠습니까?");
			System.out.println("(1)예 (2)아니오");
			String input = this.scanner.next();

			if (input.equals(selectNum.ONE)) {
				this.cHwewon.leaveHwewon(oHwewon);
				System.out.println("회원탈퇴가 완료되었습니다.");
				return true;
			} else if (input.equals(selectNum.TWO))
				return false;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
