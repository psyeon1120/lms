package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public final class ConfigW {
	public static class FVMainFrame {
		public static final Point location = new Point(40, 50);
		public static final Dimension size = new Dimension(1400, 500);
		public static final String TITLE = "수강신청 시스템";
	}

	public static class FVMainPanel {
		public static final Color background = Color.WHITE;
		public static final Dimension btnSize = new Dimension(100, 30);
	}

	public static class FVGangjwa {
		public static final Dimension size = new Dimension(510, 150);
		public static final Dimension labelSize = new Dimension(100, 20);
		public static final int IDSize=50;
		public static final int NameSize=150;
		public static final int professorSize=80;
		public static final int creditSize=30;
		public static final int timeSize=90;
		public static final int miriSize=50;
		public static final int sinchoengSize=30;
		public static final int TOSize=30;
	}

	public static class FVIndex {
		public static final Dimension size = new Dimension(150, 100);
		public static final String CAMPUS = "캠퍼스";
		public static final String COLLEGE = "대학";
		public static final String DEPARTMENT = "학과";

	}
	
	public static class FVChangeHwewon {
		public static final Point location = new Point(400, 300);
		public static final Dimension size = new Dimension(300, 200);
		public static final String TITLE = "회원정보수정";
	}

}
