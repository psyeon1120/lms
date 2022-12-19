package constants;

public class ConfigT {
	
	public static class admin{
		public static final String ADMINID="admin";
		public static final String MAKELECTURE="#";
	}

	public static class selectNum {
		public static final String ONE = "1";
		public static final String TWO = "2";
		public static final String THREE = "3";
		public static final String FOUR = "4";
		public static final String FIVE = "5";
	}

	public static class fileType {
		public static final String MIRI = "m";
		public static final String SINCHEONG = "s";
	}

	public static class moveType {
		public static final String BIGCANCEL = "C";
		public static final String SMALLCANCEL = "c";
		public static final String BIGBACK = "B";
		public static final String SMALLBACK = "b";
	}

	public static class actionType {
		public static final String MIRI = "미리담기";
		public static final String SINCHEONG = "신청";
		public static final String DELETE = "삭제";
	}

	public static class numOfChar {
		public static final String ID = "%-5S";
		public static final String NAME = "%-17S";
		public static final String PROFESSOR = "%-8S";
		public static final String CREDIT = "%-3S";
		public static final String TIME = "%-12S";
		public static final String PERSON = "%-5S";
	}

	public static class justString {
		public static final String NONE = "";
		public static final String CANCLE = "cancel";
	}

	public static class failType {
		public static final String CREDITFAIL = "credit";
		public static final String ALREADYFAIL = "already";
		public static final String MALREADYFAIL = "mAlready";
		public static final String FULLFAIL = "full";
	}

	public static class indexType {
		public static final String ROOT = "root";
		public static final String CAMPUS = "캠퍼스를";
		public static final String COLLEGE = "대학을";
		public static final String DEPARTMENT = "학과를";
	}

	public static class pathName {
		public static final String USERPATHNAME = "data/user/";
		public static final String LECTUREPATHNAME = "data/lecture/";
		public static final String HWEWONINFOFILENAME = "/hwewonInfo";
		public static final String MIRIFILENAME = "/miri";
		public static final String SINCHEONGFILENAME = "/sincheong";
	}

	public static class canCredit {
		public static final int CANSINCHEONGCREDIT = 18;
		public static final int CANPLUSMIRICREDIT = 3;
	}

	public static class basicCredit {
		public static final String BASICCREDIT = "0";

	}
}
