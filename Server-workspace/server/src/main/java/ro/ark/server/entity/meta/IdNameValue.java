package ro.ark.server.entity.meta;

public class IdNameValue {
	private long id;
	private String name;
	private int value;
	
	public IdNameValue(){}
	public IdNameValue(long id, String name, int value){
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
