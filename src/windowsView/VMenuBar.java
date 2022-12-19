package windowsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import control.CHwewon;
import valueObject.OHwewon;

public class VMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private OHwewon oHwewon;
	private CHwewon cHwewon;
	private VChangeHwewon vChangeHwewon;

	public VMenuBar(OHwewon inputHwewon) {
		this.cHwewon=new CHwewon();
		this.oHwewon = inputHwewon;
		JMenu menu = new JMenu("메뉴");
		String[] itemTitle = { "회원정보수정", "회원탈퇴", "종료" };
		JMenuItem[] menuItem = new JMenuItem[itemTitle.length];

		MenuActionListener listener = new MenuActionListener();

		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			menu.add(menuItem[i]);
			menu.addSeparator();
		}

		this.add(menu);
	}

	public class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch (cmd) {
			case "회원정보수정":
				vChangeHwewon=new VChangeHwewon(oHwewon);
				vChangeHwewon.setVisible(true);
				break;
			case "회원탈퇴":
				int result1 = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "회원탈퇴",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (result1 == JOptionPane.YES_OPTION) {
					cHwewon = new CHwewon();
					cHwewon.leaveHwewon(oHwewon);
					JOptionPane.showMessageDialog(null, "회원탈퇴를 완료했습니다.");
					System.exit(0);
				}
				break;
			case "종료":
				int result2 = JOptionPane.showConfirmDialog(null, "종료 하시겠습니까?", "종료",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if(result2==JOptionPane.YES_OPTION)
					System.exit(0);
				break;
			}
		}
	}
	
	

}
