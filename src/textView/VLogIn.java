package textView;

import java.util.Scanner;

import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import control.CHwewon;
import valueObject.OHwewon;

public class VLogIn {

	private Scanner scanner;
	private CHwewon cHwewon;
	private VHweWonDeungrok vHweWonDeungRok;
	private VFindPW vFindPW;

	public VLogIn(Scanner scanner) {
		this.scanner = scanner;
		this.cHwewon = new CHwewon();
		this.vFindPW = new VFindPW(this.scanner);
	}

	public OHwewon show() {
		while (true) {
			System.out.println("----------------로그인----------------");
			OHwewon inputHwewon=new OHwewon();

			System.out.print("아이디(학번) : ");
			inputHwewon.setId(scanner.next());
			System.out.print("비밀번호 : ");
			inputHwewon.setPassword(scanner.next());

			OHwewon oHwewon = this.cHwewon.validate(inputHwewon);
			if (oHwewon != null) {
				return oHwewon;
			} else {
				System.out.println("아이디나 비밀번호가 올바르지 않습니다.");
				while (true) {
					System.out.println("(1)회원가입 (2)다시 입력 (3)비밀번호 찾기 (C)취소");
					String input = this.scanner.next();
					if (input.equals(selectNum.ONE)) {
						this.vHweWonDeungRok = new VHweWonDeungrok(this.scanner);
						this.vHweWonDeungRok.show();
						return null;
					} else if (input.equals(selectNum.TWO))
						break;
					else if (input.equals(selectNum.THREE)) {
						this.vFindPW.show();
						break;
					} else if (input.equals(moveType.BIGCANCEL) || input.equals(moveType.SMALLCANCEL))
						return null;
					else
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			}
		}
	}

}
