package control;

import dataAccessObject.DHwewon;
import valueObject.OHwewon;

public class CHwewon {
	private DHwewon dHwewon;

	public CHwewon() {
		this.dHwewon=new DHwewon();
	}

	// 회원 아이디로 회원정보 읽어오기
	public OHwewon readHwewon(String id) {
		OHwewon oHwewon= this.dHwewon.read(id);
		return oHwewon;
	}
	
	// 로그인할 때 아이디와 비밀번호 일치하는지 확인
	public OHwewon validate(OHwewon inputHwewon) {
		OHwewon oHwewon = this.dHwewon.read(inputHwewon.getId());
		if (oHwewon != null) {
			if (oHwewon.getPassword().equals(inputHwewon.getPassword())) {
				return oHwewon;
			}
		}
		return null;
	}
	
	// 회원가입하면 회원정보 저장하기
	public boolean saveHwewon(OHwewon oHwewon) {
		boolean suc=this.dHwewon.save(oHwewon);
		return suc;
	}

	// 회원정보 바꾸기
	public void changeHwewon(OHwewon oHwewon, String changeInfo, String inputInfo) {
		this.dHwewon.change(oHwewon, changeInfo, inputInfo);
	}

	// 회원탈퇴하기
	public void leaveHwewon(OHwewon oHwewon) {
		this.dHwewon.delete(oHwewon);
	}

}
