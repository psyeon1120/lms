package valueObject;

import model.MBackpack;

public class OBackpack {
	private String id;
	
	public void set(MBackpack mBackpack) {
		this.id = mBackpack.getId();
	}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
}
