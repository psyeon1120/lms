package windowsView;

import javax.swing.JFrame;

import constants.ConfigW.FVMainFrame;

public class VMainFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;

	// components
	private VMainPanel vMainPanel;

	public VMainFrame() {
		// attributes
		this.setTitle(FVMainFrame.TITLE);
		this.setLocation(FVMainFrame.location);
		this.setSize(FVMainFrame.size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// components
		this.vMainPanel = new VMainPanel(this);
		this.add(this.vMainPanel);
	}
	
	public void initialize() {
		this.vMainPanel.initialize();
	}
	
	

	public static void main(String[] args) {
		VMainFrame vMain = new VMainFrame();
		vMain.initialize();
		vMain.setVisible(true);
	}

}
