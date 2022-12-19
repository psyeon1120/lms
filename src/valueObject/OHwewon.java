package valueObject;

import model.MHwewon;

public class OHwewon {
	private String id;
	private String password;
	private String name;
	private String address;
	private String hakgwa;
	private String sincheongCredit;
	private String miriCredit;

	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getHakgwa() {return hakgwa;}
	public void setHakgwa(String hakgwa) {this.hakgwa = hakgwa;}
	public String getSincheongCredit() {return sincheongCredit;}
	public void setSincheongCredit(String sincheongCredit) {this.sincheongCredit = sincheongCredit;}
	public String getMiriCredit() {return miriCredit;}
	public void setMiriCredit(String miriCredit) {this.miriCredit = miriCredit;}
	
	public void set(MHwewon mHwewon) {
		this.id=mHwewon.getId();
		this.password=mHwewon.getPassword();
		this.name=mHwewon.getName();
		this.address=mHwewon.getAddress();
		this.hakgwa=mHwewon.getHakgwa();
		this.sincheongCredit = mHwewon.getSincheongCredit();
		this.miriCredit=mHwewon.getMiriCredit();
	}

}
