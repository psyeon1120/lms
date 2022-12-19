package windowsView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import constants.ConfigW.FVGangjwa;
import constants.ConfigW.FVIndex;
import valueObject.OIndex;
import valueObject.OLecture;

public class VGangjwaSelectionPanel extends JPanel implements MouseListener {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	
	private VIndex vCampus;
	private VIndex vCollege;
	private VIndex vHakgwa;
	
	private VGangjwa vGangjwa;
	

	public VGangjwaSelectionPanel(VSugangSincheongPanel vSugangSincheongPanel) {
		super();
		
		GridBagLayout layoutManager=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		this.setLayout(layoutManager);
		
		JScrollPane scrollPane;
		this.vCampus = new VIndex();
		scrollPane= new JScrollPane(this.vCampus);
		scrollPane.setPreferredSize(FVIndex.size);
		this.addGrid(layoutManager, gbc, scrollPane,0,0,1,1,0,0);
		this.add(scrollPane);
		
		this.vCollege = new VIndex();
		scrollPane= new JScrollPane(this.vCollege);
		scrollPane.setPreferredSize(FVIndex.size);
		this.addGrid(layoutManager, gbc, scrollPane,0,1,1,1,0,0);
		this.add(scrollPane);
		
		this.vHakgwa = new VIndex();
		scrollPane= new JScrollPane(this.vHakgwa);
		scrollPane.setPreferredSize(FVIndex.size);
		this.addGrid(layoutManager, gbc, scrollPane,0,2,1,1,0,0);
		this.add(scrollPane);
		
		this.vGangjwa = new VGangjwa(vSugangSincheongPanel);
		scrollPane= new JScrollPane(this.vGangjwa);
		scrollPane.setPreferredSize(FVGangjwa.size);
		this.addGrid(layoutManager, gbc, scrollPane,1,0,1,3,0,0);
		this.add(scrollPane);
		
		
	}
	
	private void addGrid(GridBagLayout layoutManager, GridBagConstraints gbc, JScrollPane scroll, 
            int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      layoutManager.setConstraints(scroll, gbc);
      
}

	public void initialize() {
		this.vCampus.initialize();
		this.vCollege.initialize();
		this.vHakgwa.initialize();
		this.vGangjwa.initialize();
	}
	
	public OLecture getData(String fileName) {
		OIndex oIndex;
		oIndex=this.vCampus.getData(fileName,FVIndex.CAMPUS);
		this.vCampus.addMouseListener(this);
		oIndex=this.vCollege.getData(oIndex.getFileName(),FVIndex.COLLEGE);
		this.vCollege.addMouseListener(this);
		oIndex=this.vHakgwa.getData(oIndex.getFileName(),FVIndex.DEPARTMENT);
		this.vHakgwa.addMouseListener(this);
		OLecture oLecture =this.vGangjwa.getData(oIndex.getFileName());
		return oLecture;
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table=(JTable)e.getSource();
		int row=table.getSelectedRow();
		if(row!=-1) {
			this.initialize();
			this.getData("root");
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
