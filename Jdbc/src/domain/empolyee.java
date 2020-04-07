package domain;

import java.sql.Date;

public class empolyee {
      private int id;
      private Date birthday;
      private String name;
     
	public int getId() {
		return id;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	

	public void setBirthday(String birthday) {
		
		Date date=Date.valueOf(birthday);
		this.birthday=date;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
      
}
