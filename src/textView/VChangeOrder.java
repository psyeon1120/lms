package textView;

import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.moveType;
import control.CBackpack;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VChangeOrder {
	private Scanner scanner;
	private CBackpack cBackpack;

	public VChangeOrder(Scanner scanner) {
		this.scanner = scanner;
		this.cBackpack = new CBackpack();
	}

	public void show(OHwewon oHwewon, OLecture select, Vector<OLecture> lists) {
		System.out.println(
				select.getName() + " " + select.getProfessor() + " " + select.getTime() + "을(를) 몇번째로 옮기시겠습니까?");
		for (int i = 1; i <= lists.size(); i++)
			System.out.print(i + " ");
		while (true) {
			System.out.println("(C)취소");
			System.out.print("바꾸고 싶은 위치 : ");
			String order = this.scanner.next();

			try {
				if (order.equals(moveType.BIGCANCEL) || order.equals(moveType.SMALLCANCEL))
					return;
				else if (Integer.parseInt(order) <= lists.size() && Integer.parseInt(order) > 0) {
					this.cBackpack.changeOrder(oHwewon, select, order, lists);
					System.out.println("순서를 바꿨습니다.");
					return;
				} else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}

		}
	}

}
