package windowsView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import constants.ConfigW.FVGangjwa;
import control.CIndex;
import valueObject.OIndex;

public class VIndex extends JTable implements MouseListener {
	private static final long serialVersionUID = 1L;

	// associations
	private CIndex cIndex;
	private DefaultTableModel model;

	private int selectRow = 0;

	public VIndex() {
		super();

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.model = (DefaultTableModel) this.getModel();
		this.setSize(FVGangjwa.size);

		this.addMouseListener(this);

		model.addColumn("학과명");
		model.addColumn("파일명");
		this.getColumn("파일명").setWidth(0);
		this.getColumn("파일명").setMinWidth(0);
		this.getColumn("파일명").setMaxWidth(0);
	}

	public void initialize() {
		model.setNumRows(0);
	}

	public OIndex getData(String fileName, String indexName) {

		this.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(indexName);

		this.cIndex = new CIndex();
		Vector<OIndex> oIndices = this.cIndex.getAll(fileName);

		String[] row = new String[model.getColumnCount()];
		for (OIndex oIndex : oIndices) {
			row[0] = oIndex.getName();
			row[1] = oIndex.getFileName();
			this.model.addRow(row);
		}

		if (this.selectRow + 1> this.model.getRowCount())
			this.setRowSelectionInterval(0, 0);
		else
			this.setRowSelectionInterval(this.selectRow, this.selectRow);
		this.updateUI();
		OIndex nextFile = oIndices.elementAt(this.getSelectedRow());
		return nextFile;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		int row = table.getSelectedRow();
		if (row != -1) {
			this.selectRow = row;
		}

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
