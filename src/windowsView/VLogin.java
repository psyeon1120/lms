package windowsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.CHwewon;
import valueObject.OHwewon;

public class VLogin extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private CHwewon cHwewon;
	private VMenuBar vMenuBar;

	public VLogin(VMainFrame vMainFrame, VMainPanel vMainPanel) {
		this.cHwewon = new CHwewon();
		OHwewon inputHwewon=new OHwewon();

		JLabel id = new JLabel("ID : ");
		JLabel pw = new JLabel("Password : ");
		JTextField txtID = new JTextField(10);
		JPasswordField txtPW = new JPasswordField(10);
		JButton logBtn = new JButton("로그인");

		this.add(id);
		this.add(txtID);
		this.add(pw);
		this.add(txtPW);
		this.add(logBtn);

		logBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				inputHwewon.setId(txtID.getText());
				String inputPW = "";
				for (int i = 0; i < txtPW.getPassword().length; i++) {
					inputPW += txtPW.getPassword()[i];
				}
				inputHwewon.setPassword(inputPW);

				OHwewon oHwewon = cHwewon.validate(inputHwewon);
				if (oHwewon != null) {
					JOptionPane.showMessageDialog(null, "로그인되었습니다.");
					setVisible(false);
					vMainPanel.setvSugangSincheongPanel(new VSugangSincheongPanel(oHwewon));
					vMainPanel.add(vMainPanel.getvSugangSincheongPanel());
					vMenuBar=new VMenuBar(oHwewon);
					vMainFrame.setJMenuBar(vMenuBar);
					vMainPanel.initialize();
				} else {
					JOptionPane.showMessageDialog(null, "아이디나 비밀번호가 올바르지 않습니다.");
				}
			}
		});

		this.setSize(400, 100);
		this.setLocation(200, 300);
		this.setVisible(true);

	}

}
