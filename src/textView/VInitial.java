package textView;

import java.util.Scanner;

import constants.ConfigT.admin;
import constants.ConfigT.selectNum;
import valueObject.OHwewon;

public class VInitial {

	private Scanner scanner;
	private VHweWonDeungrok vHweWonDeungRok;
	private VLogIn vLogIn;
	private VSugangSincheong vSugangsincheong;
	private VAdmin vAdmin;
	private VFindPW vFindPW;
	private VExit vExit;

	public VInitial(Scanner scanner) {
		this.scanner = scanner;
		this.vHweWonDeungRok = new VHweWonDeungrok(this.scanner);
		this.vSugangsincheong = new VSugangSincheong(this.scanner);
		this.vAdmin=new VAdmin(this.scanner);
		this.vLogIn = new VLogIn(this.scanner);
		this.vFindPW = new VFindPW(this.scanner);
		this.vExit = new VExit(this.scanner);
	}

	public void show() {
		while (true) {
			System.out.println("------------수강신청 프로그램------------");
			System.out.println("(1)로그인 (2)회원가입 (3)비밀번호 찾기 (4)종료");

			String input = scanner.next();

			if (input.equals(selectNum.ONE)) {
				OHwewon oHwewon = this.vLogIn.show();
				if (oHwewon != null) {
					if (oHwewon.getId().equals(admin.ADMINID))
						this.vAdmin.show(oHwewon);
					else
						this.vSugangsincheong.show(oHwewon);
				}
			} else if (input.equals(selectNum.TWO)) {
				this.vHweWonDeungRok.show();
			} else if (input.equals(selectNum.THREE)) {
				this.vFindPW.show();
			} else if (input.equals(selectNum.FOUR)) {
				if (this.vExit.show())
					return;
			} else
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");

		}

	}

}
