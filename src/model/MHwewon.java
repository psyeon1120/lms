package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import valueObject.OHwewon;

public class MHwewon {

	private String id;
	private String password;
	private String name;
	private String address;
	private String hakgwa;
	private String sincheongCredit;
	private String miriCredit;
	
	private void set(OHwewon oHwewon) {
		this.id=oHwewon.getId();
		this.password=oHwewon.getPassword();
		this.name=oHwewon.getName();
		this.address=oHwewon.getAddress();
		this.hakgwa=oHwewon.getHakgwa();
		this.sincheongCredit=oHwewon.getSincheongCredit();
		this.miriCredit=oHwewon.getMiriCredit();
	}

	public boolean read(Scanner scanner) {
		if (scanner.hasNext()) {
			this.id = scanner.next();
			this.password = scanner.next();
			this.name = scanner.next();
			this.address = scanner.next();
			this.hakgwa = scanner.next();
			this.sincheongCredit=scanner.next();
			this.miriCredit=scanner.next();
			return true;
		}
		return false;
	}

	public void save(FileWriter filewriter, OHwewon oHwewon) {
		this.set(oHwewon);
		
		try {
			filewriter.write(this.id+'\n');
			filewriter.write(this.password+'\n');
			filewriter.write(this.name+'\n');
			filewriter.write(this.address+'\n');
			filewriter.write(this.hakgwa+'\n');
			filewriter.write(this.sincheongCredit+'\n');
			filewriter.write(this.miriCredit);
			filewriter.flush();
		} catch (IOException e1) {
//			e1.printStackTrace();
		}
	}

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

}
