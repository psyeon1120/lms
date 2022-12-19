package textView;

import java.util.Scanner;

import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import control.CHwewon;
import valueObject.OHwewon;

public class VChangeHwewon {

	private Scanner scanner;
	private CHwewon cHwewon;

	public VChangeHwewon(Scanner scanner) {
		this.scanner = scanner;
		this.cHwewon = new CHwewon();
	}

	public void show(OHwewon oHwewon) {
		System.out.println("-------------회원정보 수정-------------");
		System.out.println("( )ID : " + oHwewon.getId());
		System.out.println("(1)PW : " + oHwewon.getPassword());
		System.out.println("(2)NAME : " + oHwewon.getName());
		System.out.println("(3)ADDRESS : " + oHwewon.getAddress());
		System.out.println("(4)HAKGWA : " + oHwewon.getHakgwa());
		while (true) {
			System.out.println("바꿀 정보를 선택하세요. (C)취소");
			String input = this.scanner.next();

			if (input.equals(selectNum.ONE) || input.equals(selectNum.TWO) || input.equals(selectNum.THREE)
					|| input.equals(selectNum.FOUR)) {
				System.out.println("무엇으로 바꾸시겠습니까?");
				String inputInfo = this.scanner.next();
				this.cHwewon.changeHwewon(oHwewon, input, inputInfo);
				System.out.println("수정되었습니다.");
				return;
			} else if (input.equals(moveType.BIGCANCEL) || input.equals(moveType.SMALLCANCEL))
				return;
			else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
