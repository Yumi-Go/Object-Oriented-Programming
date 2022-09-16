package model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;

public class DateOfBirth {
	
	@Column(name = "date", unique = false, nullable = true)
	private int date;
	
	@Column(name = "date", unique = false, nullable = true)
	private int month;
	
	@Column(name = "date", unique = false, nullable = true)
	private int year;
	
//	private int month = student.getStudentDOB().getMonthValue();
//	private int year = student.getStudentDOB().getYear();
	
	public DateOfBirth(int date, int month, int year) {
		this.date = date;
		this.month = month;
		this.year = year;
	}
	
	public DateOfBirth(){}

	@Access(AccessType.FIELD)
	public int getDate() {
		return date;
	}
	
	public void setDate(int date) {
		this.date = date;
	}
	
	@Access(AccessType.FIELD)
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	@Access(AccessType.FIELD)
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
