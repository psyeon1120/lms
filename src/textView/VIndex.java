package textView;

import java.util.Scanner;
import java.util.Vector;

import constants.ConfigT.moveType;
import constants.ConfigT.justString;
import control.CIndex;
import valueObject.OIndex;

public class VIndex {

	private Scanner scanner;
	private CIndex cIndex; // 루트파일에 있는 모든 인덱스(oIndex는 하나하나 인덱스)

	public VIndex(Scanner scanner) {
		this.scanner = scanner;
		this.cIndex = new CIndex();
	}

	public String show(String fileName, String message) {
		System.out.println("---------" + message + " 선택---------");
		System.out.println("(B)뒤로가기 (C)취소");
		Vector<OIndex> indices = cIndex.getAll(fileName);
		for (OIndex index : indices) {
			System.out.println(index.getId() + " " + index.getName());
		}
		while (true) {
			String input = this.scanner.next();
			if (input.equals(moveType.BIGBACK) || input.equals(moveType.SMALLBACK))
				return justString.NONE;
			else if (input.equals(moveType.BIGCANCEL) || input.equals(moveType.SMALLCANCEL))
				return justString.CANCLE;
			for (OIndex index : indices) {
				if (index.getId().equals(input)) {
					return index.getFileName();
				}
			}
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
	}

}
