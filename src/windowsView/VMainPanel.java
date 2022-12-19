package windowsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.ConfigW.FVMainPanel;

public class VMainPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private VSugangSincheongPanel vSugangSincheongPanel;
	private VLogin vLogin;
	private VHwewonDeungrok vHwewonDeungrok;

	public VMainPanel(VMainFrame vMainFrame) {
		super();
		this.setBackground(FVMainPanel.background);

		JButton logBtn = new JButton("로그인");
		logBtn.setPreferredSize(FVMainPanel.btnSize);
		JButton joinBtn = new JButton("회원가입");
		joinBtn.setPreferredSize(FVMainPanel.btnSize);
		this.add(logBtn);
		this.add(joinBtn);
		this.vLogin = new VLogin(vMainFrame,this);
		logBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logBtn.setVisible(false);
				joinBtn.setVisible(false);
				vLogin.setVisible(true);
				add(vLogin);
			}
		});
		
		this.vHwewonDeungrok=new VHwewonDeungrok(vMainFrame,this);
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logBtn.setVisible(false);
				joinBtn.setVisible(false);
				vHwewonDeungrok.setVisible(true);
				add(vHwewonDeungrok);
			}
		});
	}

	public void initialize() {
		if(this.vSugangSincheongPanel!=null)
				this.vSugangSincheongPanel.initialize();
	}

	public void setvSugangSincheongPanel(VSugangSincheongPanel vSugangSincheongPanel) {
		this.vSugangSincheongPanel = vSugangSincheongPanel;
	}

	public VSugangSincheongPanel getvSugangSincheongPanel() {
		return vSugangSincheongPanel;
	}

}
