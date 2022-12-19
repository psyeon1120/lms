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

public class VHwewonDeungrok extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private CHwewon cHwewon;
	private VMenuBar vMenuBar;

	public VHwewonDeungrok(VMainFrame vMainFrame, VMainPanel vMainPanel) {
		this.cHwewon=new CHwewon();

		JLabel id = new JLabel("ID : ");
		JLabel pw = new JLabel("Password : ");
		JLabel name = new JLabel("이름 : ");
		JLabel address = new JLabel("주소 : ");
		JLabel hakgwa = new JLabel("학과 : ");
		JTextField txtID = new JTextField(10);
		JPasswordField txtPW = new JPasswordField(10);
		JTextField txtName = new JTextField(10);
		JTextField txtAddress = new JTextField(10);
		JTextField txtHakgwa = new JTextField(10);
		JButton joinBtn = new JButton("회원가입");

		this.add(id);
		this.add(txtID);
		this.add(pw);
		this.add(txtPW);
		this.add(name);
		this.add(txtName);
		this.add(address);
		this.add(txtAddress);
		this.add(hakgwa);
		this.add(txtHakgwa);
		this.add(joinBtn);

		joinBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				OHwewon oHwewon = new OHwewon();
				oHwewon.setId(txtID.getText());
				String inputPW = "";
				for (int i = 0; i < txtPW.getPassword().length; i++) {
					inputPW += txtPW.getPassword()[i];
				}
				oHwewon.setPassword(inputPW);
				oHwewon.setName(txtName.getText());
				oHwewon.setAddress(txtAddress.getText());
				oHwewon.setHakgwa(txtHakgwa.getText());
				boolean suc = cHwewon.saveHwewon(oHwewon);
				
				if (suc == true) {
					JOptionPane.showMessageDialog(null, "회원등록 완료되었습니다! 자동으로 로그인합니다.");
					setVisible(false);
					vMainPanel.setvSugangSincheongPanel(new VSugangSincheongPanel(oHwewon));
					vMainPanel.add(vMainPanel.getvSugangSincheongPanel());
					vMenuBar=new VMenuBar(oHwewon);
					vMainFrame.setJMenuBar(vMenuBar);
					vMainPanel.initialize();
					return;
				} else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
				}
			}
		});

		this.setSize(400, 100);
		this.setLocation(200, 300);
		this.setVisible(true);

	}

}
