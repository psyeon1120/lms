package windowsView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import constants.ConfigT.actionType;
import constants.ConfigT.canCredit;
import constants.ConfigT.failType;
import constants.ConfigW.FVGangjwa;
import control.CBackpack;
import valueObject.OHwewon;
import valueObject.OLecture;

public class VSugangSincheongPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private VGangjwaSelectionPanel vGangjwaSelectionPanel;
	private VMiri_Sincheong vMiridamgi;
	private VMiri_Sincheong vSincheong;
	private CBackpack cBackpack;
	private OHwewon oHwewon;
	private OLecture selectLecture;

	private JButton miriBtn;
	private JButton sincheongBtn;
	private JButton deleteBtn;
	JLabel sincheongName;
	JLabel miriName;

	public VSugangSincheongPanel(OHwewon oHwewon) {
		super();
		this.setSize(new Dimension(1300, 450));
		
		this.oHwewon = oHwewon;
		this.cBackpack=new CBackpack();
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);

		this.vGangjwaSelectionPanel = new VGangjwaSelectionPanel(this);
		this.add(this.vGangjwaSelectionPanel, BorderLayout.WEST);

		ActionListener btnListener = new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				String suc;
				String message = "";

				if (a.getSource().equals(deleteBtn)) {
					message = actionType.DELETE;
					return;
				} else if (a.getSource().equals(miriBtn)) {
					message = actionType.MIRI;
				} else if (a.getSource().equals(sincheongBtn)) {
					message = actionType.SINCHEONG;
				}
				suc = cBackpack.save(oHwewon, selectLecture, message);
				sincheongName.setText("수강신청내역    총학점 : " + oHwewon.getSincheongCredit() + "  /  가능 학점 : "
						+ canCredit.CANSINCHEONGCREDIT);
				miriName.setText("미리담기내역    총학점 : " + oHwewon.getMiriCredit() + "  /  가능 학점 : "
						+ (canCredit.CANSINCHEONGCREDIT + canCredit.CANPLUSMIRICREDIT));
				if (suc == failType.CREDITFAIL)
					JOptionPane.showMessageDialog(null, message + " 가능 학점을 초과했습니다.");
				else if (suc == failType.ALREADYFAIL)
					JOptionPane.showMessageDialog(null, "이미 신청된 과목입니다.");
				else if (suc == failType.FULLFAIL)
					JOptionPane.showMessageDialog(null, "신청 인원 초과입니다.");
				else if (suc == failType.MALREADYFAIL)
					JOptionPane.showMessageDialog(null, "이미 미리담기된 과목입니다.");
				else
					JOptionPane.showMessageDialog(null, message + "됐습니다.");

				initialize();
			}

		};

		JPanel btnPanel=new JPanel();
			layoutManager=new BoxLayout(btnPanel, BoxLayout.Y_AXIS);
			btnPanel.setLayout(layoutManager);
			btnPanel.setPreferredSize(new Dimension(85, 20));
//			btnPanel.setBorder(new LineBorder(Color.black));
			this.miriBtn = new JButton("미리담기");
			this.miriBtn.addActionListener(btnListener);
			this.miriBtn.setAlignmentX(CENTER_ALIGNMENT);
			this.sincheongBtn = new JButton("신청");
			this.sincheongBtn.addActionListener(btnListener);
			this.sincheongBtn.setAlignmentX(CENTER_ALIGNMENT);
			this.deleteBtn = new JButton("삭제");
			this.deleteBtn.addActionListener(btnListener);
			this.deleteBtn.setAlignmentX(CENTER_ALIGNMENT);
			btnPanel.add(this.miriBtn);
			btnPanel.add(this.sincheongBtn);
			btnPanel.add(this.deleteBtn);
		this.add(btnPanel,BorderLayout.CENTER);

		JPanel miriSincheongPanel = new JPanel();
		layoutManager = new BoxLayout(miriSincheongPanel, BoxLayout.Y_AXIS);
		miriSincheongPanel.setLayout(layoutManager);

			this.sincheongName = new JLabel(
					"수강신청내역    총학점 : " + this.oHwewon.getSincheongCredit() + "  /  가능 학점 : " + canCredit.CANSINCHEONGCREDIT);
			this.sincheongName.setAlignmentX(CENTER_ALIGNMENT);
			miriSincheongPanel.add(this.sincheongName);
			JScrollPane scrollPane;
			this.vSincheong = new VMiri_Sincheong();
			scrollPane = new JScrollPane(this.vSincheong);
			scrollPane.setPreferredSize(FVGangjwa.size);
			miriSincheongPanel.add(scrollPane);
	
			this.miriName = new JLabel("미리담기내역    총학점 : " + this.oHwewon.getMiriCredit() + "  /  가능 학점 : "
					+ (canCredit.CANSINCHEONGCREDIT + canCredit.CANPLUSMIRICREDIT));
			this.miriName.setAlignmentX(CENTER_ALIGNMENT);
			miriSincheongPanel.add(this.miriName);
			this.vMiridamgi = new VMiri_Sincheong();
			scrollPane = new JScrollPane(this.vMiridamgi);
			scrollPane.setPreferredSize(FVGangjwa.size);
			miriSincheongPanel.add(scrollPane);

		this.add(miriSincheongPanel, BorderLayout.EAST);
	}


	public void initialize() {
		this.vGangjwaSelectionPanel.initialize();
		this.selectLecture = this.vGangjwaSelectionPanel.getData("root");

		this.vMiridamgi.initialize();
		this.vMiridamgi.getData(oHwewon, "m");

		this.vSincheong.initialize();
		this.vSincheong.getData(oHwewon, "s");

	}
}
