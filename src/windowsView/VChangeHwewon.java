package windowsView;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import constants.ConfigT.selectNum;
import constants.ConfigW.FVChangeHwewon;
import control.CHwewon;
import valueObject.OHwewon;

public class VChangeHwewon extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private CHwewon cHwewon;

	public VChangeHwewon(OHwewon oHwewon) {
		this.cHwewon=new CHwewon();
		this.setSize(FVChangeHwewon.size);
		this.setLocation(FVChangeHwewon.location);
		this.setTitle(FVChangeHwewon.TITLE);
			GridBagLayout layoutManager=new GridBagLayout();
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.fill=GridBagConstraints.BOTH;
			this.setLayout(layoutManager);
			
			
			JLabel id = new JLabel("ID : " + oHwewon.getId());
			JLabel pw = new JLabel("Password : ");
			JLabel name = new JLabel("이름 : ");
			JLabel address = new JLabel("주소 : ");
			JLabel hakgwa = new JLabel("학과 : ");
			JTextField txtPW = new JTextField(10);
			JTextField txtName = new JTextField(10);
			JTextField txtAddress = new JTextField(10);
			JTextField txtHakgwa = new JTextField(10);
			JButton changeBtn = new JButton("수정");
		
			addGrid(layoutManager, gbc, id,0,0,2,1,0,0);
			this.add(id);
			addGrid(layoutManager, gbc, pw,0,1,1,1,0,0);
			this.add(pw);
			addGrid(layoutManager, gbc, txtPW,1,1,1,1,0,0);
			this.add(txtPW);
			addGrid(layoutManager, gbc, name,0,2,1,1,0,0);
			this.add(name);
			addGrid(layoutManager, gbc, txtName,1,2,1,1,0,0);
			this.add(txtName);
			addGrid(layoutManager, gbc, address,0,3,1,1,0,0);
			this.add(address);
			addGrid(layoutManager, gbc, txtAddress,1,3,1,1,0,0);
			this.add(txtAddress);
			addGrid(layoutManager, gbc, hakgwa,0,4,1,1,0,0);
			this.add(hakgwa);
			addGrid(layoutManager, gbc, txtHakgwa,1,4,1,1,0,0);
			this.add(txtHakgwa);
			addGrid(layoutManager, gbc, changeBtn,0,5,2,1,0,0);
			this.add(changeBtn);
			
			changeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int count = 0;
					if (!txtPW.getText().equals("")) {
						count++;
						cHwewon.changeHwewon(oHwewon, selectNum.ONE, txtPW.getText());
					}
					if (!txtName.getText().equals("")) {
						count++;
						cHwewon.changeHwewon(oHwewon, selectNum.TWO, txtName.getText());
					}
					if (!txtAddress.getText().equals("")) {
						count++;
						cHwewon.changeHwewon(oHwewon, selectNum.THREE, txtAddress.getText());
					}
					if (!txtHakgwa.getText().equals("")) {
						count++;
						cHwewon.changeHwewon(oHwewon, selectNum.FOUR, txtHakgwa.getText());
					}
					JOptionPane.showMessageDialog(null, count + "개의 정보를 수정했습니다.");

					setVisible(false);
				}
			});
	}
	public void addGrid(GridBagLayout layoutManager, GridBagConstraints gbc, Component c, 
            int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      layoutManager.setConstraints(c, gbc);
      
}



}
