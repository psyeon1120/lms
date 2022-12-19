package textView;

import java.util.Vector;

import constants.ConfigT.numOfChar;
import valueObject.OLecture;

public class VList {

	public void show(Vector<OLecture> list) {
		System.out.println("과목번호-------과목명-------담당교수---학점-----시간-----미리담기--신청--정원");
		for (OLecture line : list) {
			System.out.printf(numOfChar.ID, line.getId());
			System.out.printf(numOfChar.NAME, line.getName());
			System.out.printf(numOfChar.PROFESSOR, line.getProfessor());
			System.out.printf(numOfChar.CREDIT, line.getCredit());
			System.out.printf(numOfChar.TIME, line.getTime());
			System.out.printf(numOfChar.PERSON, line.getMiriPerson());
			System.out.printf(numOfChar.PERSON, line.getSincheongPerson());
			System.out.println(line.getTO());
		}
	}

}
