package textView;

import java.util.Scanner;

import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import control.CHwewon;
import valueObject.OHwewon;

public class VFindPW {
	private Scanner scanner;
	private CHwewon cHwewon;

	public VFindPW(Scanner scanner) {
		this.scanner = scanner;
		this.cHwewon = new CHwewon();
	}

	public void show() {
		while (true) {
			System.out.println("-------------비밀번호 찾기-------------");
			System.out.print("아이디(학번) : ");
			String id = this.scanner.next();
			System.out.print("이름 : ");
			String name = this.scanner.next();

			OHwewon findHwewon = this.cHwewon.readHwewon(id);
			if (findHwewon != null) {
				if (findHwewon.getName().equals(name)) {
					System.out.println(findHwewon.getName() + "님의 비밀번호 : " + findHwewon.getPassword());
					return;
				} else
					System.out.println("이름을 잘못 입력하셨습니다.");
			} else
				System.out.println("없는 아이디입니다.");

			while (true) {
				System.out.println("(1)다시 찾기 (C)취소");
				String input = this.scanner.next();
				if (input.equals(selectNum.ONE))
					break;
				else if (input.equals(moveType.BIGCANCEL) || input.equals(moveType.SMALLCANCEL))
					return;
				else
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

}
