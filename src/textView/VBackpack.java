package textView;

import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.canCredit;
import constants.ConfigT.fileType;
import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import control.CBackpack;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VBackpack {
	private CBackpack cBackpack;
	private VBackpackSelect vBackpackSelect;
	private VList vList;
	private Scanner scanner;

	public VBackpack(Scanner scanner) {
		this.scanner = scanner;
		this.cBackpack = new CBackpack();
		this.vBackpackSelect = new VBackpackSelect(this.scanner);
		this.vList = new VList();
	}

	public void show(OHwewon oHwewon) {
		while (true) {
			System.out.println("-----------------------------첵가방-----------------------------");
			System.out.println("----------------미리담기 내역(총 학점 : " + oHwewon.getMiriCredit() + "/가능학점 : "
					+ (canCredit.CANSINCHEONGCREDIT + canCredit.CANPLUSMIRICREDIT) + ")---------------");
			Vector<OLecture> miris = cBackpack.getAll(oHwewon.getId(), fileType.MIRI);
			this.vList.show(miris);
			System.out.println();

			System.out.println("-----------------신청 내역(총 학점 : " + oHwewon.getSincheongCredit() + "/가능학점 : "
					+ canCredit.CANSINCHEONGCREDIT + ")-----------------");
			Vector<OLecture> sincheongs = cBackpack.getAll(oHwewon.getId(), fileType.SINCHEONG);
			this.vList.show(sincheongs);
			System.out.println();
			System.out.println("내역을 선택하세요.");
			while (true) {
				System.out.println("(1)미리담기 내역 (2)수강신청 내역 (C)취소");
				String input = this.scanner.next();
				if (input.equals(selectNum.ONE)) {
					if (miris.size() > 0) {
						this.vBackpackSelect.show(oHwewon, miris, fileType.MIRI);
						break;
					} else
						System.out.println("미리담기 내역이 없습니다.");
				} else if (input.equals(selectNum.TWO)) {
					if (sincheongs.size() > 0) {
						this.vBackpackSelect.show(oHwewon, sincheongs, fileType.SINCHEONG);
						break;
					} else
						System.out.println("신청 내역이 없습니다.");
				} else if (input.equals(moveType.BIGCANCEL) || input.equals(moveType.SMALLCANCEL))
					return;
				else
					System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");

			}
		}
	}

}
