package windowsView;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import constants.ConfigW.FVGangjwa;
import control.CBackpack;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VMiri_Sincheong extends JTable {
	private static final long serialVersionUID = 1L;

	private CBackpack cBackpack;
	private DefaultTableModel model;

	public VMiri_Sincheong() {
		super();
		this.model = (DefaultTableModel) this.getModel();
		String[] columnName = { "과목번호", "강좌명", "담당교수", "학점", "시간", "미리담기", "신청", "정원" };
		for (int i = 0; i < columnName.length; i++)
			model.addColumn(columnName[i]);
		
		this.getColumnModel().getColumn(0).setPreferredWidth(FVGangjwa.IDSize);
		this.getColumnModel().getColumn(1).setPreferredWidth(FVGangjwa.NameSize);
		this.getColumnModel().getColumn(2).setPreferredWidth(FVGangjwa.professorSize);
		this.getColumnModel().getColumn(3).setPreferredWidth(FVGangjwa.creditSize);
		this.getColumnModel().getColumn(4).setPreferredWidth(FVGangjwa.timeSize);
		this.getColumnModel().getColumn(5).setPreferredWidth(FVGangjwa.miriSize);
		this.getColumnModel().getColumn(6).setPreferredWidth(FVGangjwa.sinchoengSize);
		this.getColumnModel().getColumn(7).setPreferredWidth(FVGangjwa.TOSize);

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cm = this.getColumnModel();
		for (int i = 0; i < cm.getColumnCount(); i++)
			cm.getColumn(i).setCellRenderer(cr);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void initialize() {
		model.setNumRows(0);
	}

	public void getData(OHwewon oHwewon, String type) {
		this.cBackpack = new CBackpack();
		Vector<OLecture> lectures = cBackpack.getAll(oHwewon.getId(), type);

		String[] row = new String[model.getColumnCount()];
		for (OLecture lecture : lectures) {
			row[0] = lecture.getId();
			row[1] = lecture.getName();
			row[2] = lecture.getProfessor();
			row[3] = lecture.getCredit();
			row[4] = lecture.getTime();
			row[5] = lecture.getMiriPerson();
			row[6] = lecture.getSincheongPerson();
			row[7] = lecture.getTO();
			model.addRow(row);
		}
		this.updateUI();

	}
}
