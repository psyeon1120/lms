package textView;

import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.actionType;
import constants.ConfigT.fileType;
import constants.ConfigT.moveType;
import constants.ConfigT.selectNum;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VBackpackSelect {
	private Scanner scanner;
	private VMiri_Sincheong vMiri_Sincheong;
	private VChangeOrder vChangeOrder;

	public VBackpackSelect(Scanner scanner) {
		this.scanner = scanner;
		this.vMiri_Sincheong = new VMiri_Sincheong(this.scanner);
		this.vChangeOrder = new VChangeOrder(this.scanner);
	}

	public void show(OHwewon oHwewon, Vector<OLecture> lists, String type) {
		OLecture select = null;
		while (true) {
			System.out.println("강좌를 선택하세요. (C)취소");
			String id = this.scanner.next();
			if (id.equals(moveType.BIGCANCEL) || id.equals(moveType.SMALLCANCEL)) {
				return;
			}

			for (OLecture list : lists) {
				if (list.getId().equals(id)) {
					select = list;
					break;
				}
			}
			if (select != null) {
				if (type.equals(fileType.MIRI)) { // 미리담기내역일 때
					while (true) {
						System.out.println("(1)신청 (2)삭제 (3)순서 바꾸기 (C)취소");
						String input = this.scanner.next();
						if (input.equals(selectNum.ONE)) {
							this.vMiri_Sincheong.show(oHwewon, select, actionType.SINCHEONG, type, lists);
							return;
						} else if (input.equals(selectNum.TWO)) {
							this.vMiri_Sincheong.show(oHwewon, select, actionType.DELETE, type, lists);
							return;
						} else if (input.equals(selectNum.THREE)) {
							this.vChangeOrder.show(oHwewon, select, lists);
							return;
						} else if (input.equals(moveType.BIGCANCEL) || input.equals(moveType.SMALLCANCEL))
							break;
						else
							System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					}
				} else { // 신청내역일 때
					this.vMiri_Sincheong.show(oHwewon, select, actionType.DELETE, type, lists);
					return;
				}
			} else
				System.out.println("강좌를 잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
