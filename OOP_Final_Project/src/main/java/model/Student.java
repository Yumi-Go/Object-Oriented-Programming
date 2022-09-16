package model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;


@Entity
@Table(name="Student")
public class Student implements Serializable, Comparable<Student> {
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	private Name name; // set Column in Name.java
	
	@Column(name = "Email", unique = false, nullable = true)
	private String email;
	
	@Column(name = "PhoneNumber", unique = false, nullable = true)
	private String phoneNumber;

	@Column(name = "DateOfBirth", unique = false, nullable = true)
	private LocalDate dob;
	
	
	
	@MapKeyColumn(name = "Module")
	@Column(name = "Grade", nullable = true)
	private LinkedHashMap<String, Integer> moduleAndGradeHashMapForEachStudent = new LinkedHashMap<String, Integer>();

	
	public Student (Integer id, Name name, String email, String phoneNumber, 
         LocalDate dob, LinkedHashMap<String, Integer> moduleAndGradeHashMapForEachStudent)
	{
		this.setID(id);
		this.setName(name);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setDOB(dob);

	}

	public Student(){
		Name name = new Name();
	}
	
	@Access(AccessType.FIELD)
	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	@Access(AccessType.FIELD)
	public Name getName() {
		return name;
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	
	@Access(AccessType.FIELD)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Access(AccessType.FIELD)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Access(AccessType.FIELD)
	public LocalDate getDOB() {
		return dob;
	}
	
	public void setDOB(LocalDate dob) {
		this.dob = dob;
	}
	

	@Access(AccessType.FIELD)
	public LinkedHashMap<String, Integer> getModuleAndGradeHashMapForEachStudent() {
		return moduleAndGradeHashMapForEachStudent;
	}
	
	
	public void setModuleAndGradeHashMapForEachStudent(LinkedHashMap<String, Integer> ModuleAndGradeHashMapForEachStudent) {
		this.moduleAndGradeHashMapForEachStudent = ModuleAndGradeHashMapForEachStudent;
	}
	
//	public void setModuleAndGradeHashMapForEachStudent(String module, Integer grade) {
//		this.moduleAndGradeHashMapForEachStudent.put(module, grade);
//	}
	
	
	public String toString() {
		
		String string = "ID: " + id
				+ "\nName: " + name.getFullNameString()
				+ "\nEmail: " + email
				+ "\nPhone Number: " + phoneNumber
				+ "\nDate of Birth: " + dob + "\n";
		if (moduleAndGradeHashMapForEachStudent != null) {
			StringBuffer moduleGradeForEachStudentToString = new StringBuffer();
			moduleAndGradeHashMapForEachStudent.entrySet().forEach(entry -> {
				if (entry.getValue() != null) {
					moduleGradeForEachStudentToString.append("\tModule: " + entry.getKey());
					moduleGradeForEachStudentToString.append("\n\tGrade: " + entry.getValue() + "\n");
				}
			});
			string = string + moduleGradeForEachStudentToString;
		}
		return string;
		
	}
	
	
	public int compareTo(Student s) {
		
		String this_IDtoString = Integer.toString(this.id);
		String s_IDtoString = Integer.toString(s.getID());
      
		int lastCmp = this_IDtoString.compareTo(s_IDtoString);
		if (lastCmp != 0)
			return lastCmp;
		else
			return this_IDtoString.compareTo(s_IDtoString);
	}
   
}