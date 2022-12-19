package textView;

import java.util.Scanner;

import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import control.CHwewon;
import valueObject.OHwewon;

public class VHweWonDeungrok {

	private Scanner scanner;
	private CHwewon cHwewon;
	private VSugangSincheong vSugangsincheong;

	public VHweWonDeungrok(Scanner scanner) {
		this.scanner = scanner;
		this.cHwewon = new CHwewon();
		this.vSugangsincheong = new VSugangSincheong(this.scanner);
	}

	public void show() {
		while (true) {
			System.out.println("---------------회원가입---------------");
			System.out.println("회원정보를 입력하세요.");
			OHwewon oHwewon = new OHwewon();

			// 사용자 정보 입력받아서 OHwewon에 저장
			System.out.print("아이디(학번) : ");
			oHwewon.setId(scanner.next());
			System.out.print("비밀번호 : ");
			oHwewon.setPassword(scanner.next());
			System.out.print("이름 : ");
			oHwewon.setName(scanner.next());
			System.out.print("주소 : ");
			oHwewon.setAddress(scanner.next());
			System.out.print("학과 : ");
			oHwewon.setHakgwa(scanner.next());

			boolean suc = this.cHwewon.saveHwewon(oHwewon);
			if (suc == true) {
				System.out.println("회원등록 완료되었습니다! 자동으로 로그인합니다.");
				this.vSugangsincheong.show(oHwewon);
				return;
			} else {
				System.out.println("이미 존재하는 아이디입니다.");
				while (true) {
					System.out.println("(1)다시 입력 (C)취소");
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

}
