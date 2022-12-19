package windowsView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import constants.ConfigW.FVGangjwa;
import control.CLecture;
import valueObject.OLecture;

public class VGangjwa extends JTable implements MouseListener{
	private static final long serialVersionUID = 1L;

	// associations
	private CLecture cLecture;
	private DefaultTableModel model;
	private VSugangSincheongPanel vSugangSincheongPanel;
	
	private int selectRow = 0;

	public VGangjwa(VSugangSincheongPanel vSugangSincheongPanel) {
		super();
		this.vSugangSincheongPanel=vSugangSincheongPanel;
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
		
		DefaultTableCellRenderer cr=new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cm=this.getColumnModel();
		for(int i=0; i<cm.getColumnCount();i++)
			cm.getColumn(i).setCellRenderer(cr);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		this.addMouseListener(this);
	}

	public void initialize() {
		model.setNumRows(0);
	}

	public OLecture getData(String fileName) {

		this.cLecture = new CLecture();
		Vector<OLecture> sincheongs = cLecture.getAll(fileName);

		String[] row = new String[model.getColumnCount()];
		for (OLecture sincheong : sincheongs) {
			row[0] = sincheong.getId();
			row[1] = sincheong.getName();
			row[2] = sincheong.getProfessor();
			row[3] = sincheong.getCredit();
			row[4] = sincheong.getTime();
			row[5]=sincheong.getMiriPerson();
			row[6]=sincheong.getSincheongPerson();
			row[7]=sincheong.getTO();
			model.addRow(row);
		}
		if (this.selectRow+1 > this.model.getRowCount())
			this.setRowSelectionInterval(0, 0);
		else
			this.setRowSelectionInterval(this.selectRow, this.selectRow);
		this.updateUI();
		OLecture lecture = sincheongs.elementAt(this.getSelectedRow());
		return lecture;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		int row = table.getSelectedRow();
		if (row != -1) {
			selectRow = row;
		}
		this.vSugangSincheongPanel.initialize();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
