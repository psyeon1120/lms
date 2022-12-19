package textView;

import java.util.Scanner;

import constants.ConfigT.selectNum;
import valueObject.OHwewon;

public class VLogout {

	private Scanner scanner;

	public VLogout(Scanner scanner) {
		this.scanner = scanner;
	}

	public OHwewon show(OHwewon oHwewon) {
		System.out.println("----------로그아웃----------");
		while (true) {
			System.out.println("정말로 로그아웃하시겠습니까?");
			System.out.println("(1)예 (2)아니오");

			String input = this.scanner.next();
			if (input.equals(selectNum.ONE)) {
				oHwewon = null;
				System.out.println("로그아웃되었습니다.");
				break;
			} else if (input.equals(selectNum.TWO))
				break;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
		return oHwewon;
	}

}
