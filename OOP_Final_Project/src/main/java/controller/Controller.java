package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import application.AlertWindows;
import model.Name;
import model.Student;

public class Controller implements Serializable {	
	
	public Controller(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
	
	// tab1 add button
	public void tab1_addStudent(String firstName, String middleName, String lastName, String strID, 
			String email, String phoneNumber, LocalDate dob) {
		
		EntityManager em = null;
		
		Integer id = null;
		if (strID != null) {
			id = Integer.parseInt(strID);
		}
		
		Name name = new Name(firstName, middleName, lastName);
		Student student = new Student(id, name, email, phoneNumber, dob, null);
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
    		em.merge(student);
    		em.getTransaction().commit();
    		System.out.println("added student: " + student.toString()); // for check
		
		} catch (Exception e) {
			
			e.printStackTrace();
			em.getTransaction().rollback();
			AlertWindows.AddFail();
		
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}

	
	// get ListView items for ListView_SelectID.java
	public List<Integer> getListViewItems_ID() {
		
		EntityManager em = null;
		
		List<Integer> listViewIDs = null;
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			
			String jpql = "SELECT id FROM Student ORDER BY id DESC";
			listViewIDs = em.createQuery(jpql, Integer.class).getResultList();
			
			System.out.println("ID list: " + listViewIDs.toString()); // for check
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();

		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return listViewIDs;

	}
	
	
	// get a Student object by its id (which was selected via ListView) to put in textfields in Main.java for update
	public Student tab1_GetStudentToBeUpdated(Integer id) {

		EntityManager em = null;
		Student student = null;
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			if (id != null) {
				System.out.println("selected studentID to be updated: " + id); // for check
				student = em.find(Student.class, id);
				em.getTransaction().commit();
				System.out.println("student to be updated: " + student.toString()); // for check
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return student;

	}
	
	
	// tab1 remove button - remove all DB for a student
	public void tab1_RemoveStudent(Integer id) {
		
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Student student = (Student) em.find(Student.class, id);
			em.remove(student);
			em.getTransaction().commit();
			
		} catch (Exception e) {	
			
			e.printStackTrace();
			em.getTransaction().rollback();
			AlertWindows.RemoveFail();
			
		} finally {
			if (em != null) {
				em.close();
			}
		}
	
	}
	
	
	// tab1 list button
	public String tab1_getStringForTextarea() {
		EntityManager em = null;
		String result = "";
		List<Student> students = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			String jpql = "FROM Student ORDER BY id ASC";
			students = em.createQuery(jpql, Student.class).getResultList();

			if (students.size() == 0) {
				result = "No student database exists.";
			} else {
				for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
					Student student = (Student) iterator.next();
					StringBuffer eachStudentToString = new StringBuffer();
					eachStudentToString.append("Name: ");
					eachStudentToString.append(student.getName().getFullNameString() + "\n");
					eachStudentToString.append("ID: ");
					eachStudentToString.append(student.getID() + "\n");
					eachStudentToString.append("Email: ");
					eachStudentToString.append(student.getEmail() + "\n");
					eachStudentToString.append("Phone Number: ");
					eachStudentToString.append(student.getPhoneNumber() + "\n");
					eachStudentToString.append("Date of Birth: ");
					if (student.getDOB() != null) {
						eachStudentToString.append(student.getDOB() + "\n\n");
					} else {
						eachStudentToString.append("\n\n");
					}
					result = result + eachStudentToString + "\n";
				}
			}
			em.getTransaction().commit();
		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return result;
	}
	
	
	// for check
	public String tab1_LoadAllStudents() {

		EntityManager em = null;
		String result = "";
		List<String> students = null;
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("SELECT ID, fullName, Email, PhoneNumber, DateOfBirth FROM Student ORDER BY id ASC").getResultList();
			
			if (students.size() == 0) {
				result = "No student database exists.";
			} else {
				for (Iterator<String> iterator = students.iterator(); iterator.hasNext();) {		
					String student = iterator.next();
					result = result + student.toString() + "\n";
				}
			}
			em.getTransaction().commit();
		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return result;
	}
	
	
	// tab2 add button
	public void tab2_addModuleGrade(Integer id, String module, String strGrade) {

		EntityManager em = null;
		Integer grade = null;
		if (strGrade != null) {
			grade = Integer.parseInt(strGrade);
		}
				
		Student student = null;
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			student = em.find(Student.class, id);
			student.getModuleAndGradeHashMapForEachStudent().put(module, grade);

			em.getTransaction().commit();
			em.close();

		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	
	}

	
	// get ListView items for ListView_SelectModuleGrade.java
	public LinkedHashMap <String, String> getListViewItems_ModuleGrade(Integer id) {

		EntityManager em = null;
		Student student = null;
		LinkedHashMap <String, String> result = new LinkedHashMap <String, String>();
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			student = (Student) em.find(Student.class, id);					
			student.getModuleAndGradeHashMapForEachStudent().entrySet().forEach(entry -> {
				if (entry.getValue() != null) {
					String row = entry.getKey() + ": " + entry.getValue().toString();
					result.put(entry.getKey(), row);
				}
			});
    		em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return result;
	}	
		
	
	// tab2 list button
	public String tab2_getStringForTextarea() {

		EntityManager em = null;
		List<Student> students = null;
		String result = "";
		StringBuffer eachStudentToString = new StringBuffer();				
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			String jpql = "FROM Student ORDER BY id ASC";
			students = em.createQuery(jpql, Student.class).getResultList();
			if (students.size() == 0) {
				result = "No student database exists.";
			} else {
				for (int i = 0 ; i < students.size(); i++) {
				   if (students.get(i).getModuleAndGradeHashMapForEachStudent().size() != 0) {
					 
					   Set<String> module = students.get(i).getModuleAndGradeHashMapForEachStudent().keySet();
						if (module != null) {
							eachStudentToString.append(students.get(i).getName().getFullNameString() + " [");
							eachStudentToString.append(students.get(i).getID() + "]\n");
							students.get(i).getModuleAndGradeHashMapForEachStudent().entrySet().forEach(entry -> {
								if (entry.getValue() != null) {
									eachStudentToString.append("\t" + entry.getKey());
									eachStudentToString.append(": ");
									eachStudentToString.append(entry.getValue() + "\n");
								}
							});
						}
					}
				}
				eachStudentToString.append("\n");
				result = result + eachStudentToString;
				em.getTransaction().commit();
				System.out.println("student module&grade: " + result); // for check
			}
		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return result;

	}
	
	
	public LinkedHashMap<String, Integer> tab3_mapForTableView(Integer id) {
		
		EntityManager em = null;
		LinkedHashMap<String, Integer> mapForTableView = new LinkedHashMap<String, Integer>();
		try {			
			em = getEntityManager();
			em.getTransaction().begin();
			Student student = (Student) em.find(Student.class, id);			
			mapForTableView = student.getModuleAndGradeHashMapForEachStudent();
			em.getTransaction().commit();
		
		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return mapForTableView;
	}
	
	
	
	public LinkedHashMap<String, Integer> tab3_sortByValueMap(LinkedHashMap<String, Integer> map) { // sort for tableview
		List <Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
		Collections.sort(entryList, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : entryList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
		
	
	public ArrayList<Integer> getStudentIDcomboBoxItems() {

		EntityManager em = null;
		ArrayList<Integer> IDcomboItemList = new ArrayList<Integer>();
		
		try {		
			em = getEntityManager();
			em.getTransaction().begin();
			List<Integer> idList = em.createQuery("SELECT id FROM Student ORDER BY id ASC").getResultList();
			
			if (idList.size() == 0) {
				System.out.println("No student database exists.");
			} else {
				for (int i = 0; i < idList.size(); i++) {
					IDcomboItemList.add(idList.get(i));
				}
			}			
			
			em.getTransaction().commit();

		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return IDcomboItemList;
	}
	
	
	public String tab3_getNameToDisplay(Integer selectedID_ComboBox) {
		
		EntityManager em = null;
		String name = null;
		Student student = new Student();
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			student = em.find(Student.class, selectedID_ComboBox);
			
			name = student.getName().getFullNameString();
			System.out.println("student name for " + selectedID_ComboBox + ": " + name + "\n"); // for check
			
			em.getTransaction().commit();

		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return name;		
	}
	
	
	public String tab3_GetEmailToDisplay(Integer selectedID_ComboBox) {
		
		EntityManager em = null;
		String email = null;
		Student student = new Student();
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			student = em.find(Student.class, selectedID_ComboBox);
			
			email = student.getEmail();
			System.out.println("student email for " + selectedID_ComboBox + ": " + email + "\n"); // for check
			
			em.getTransaction().commit();

		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return email;
	}
	
	
	public String tab3_GetPhoneNumberToDisplay(Integer selectedID_ComboBox) {
		
		EntityManager em = null;
		String phoneNumber = null;
		Student student = new Student();
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			student = em.find(Student.class, selectedID_ComboBox);
			
			phoneNumber = student.getPhoneNumber();
			System.out.println("student phone number for " + selectedID_ComboBox + ": " + phoneNumber + "\n"); // for check
			
			em.getTransaction().commit();

		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return phoneNumber;
	
	}
	
	
	public LocalDate tab3_GetDOBtoDisplay(Integer selectedID_ComboBox) {

		EntityManager em = null;
		LocalDate dob = null;
		Student student = new Student();
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			student = em.find(Student.class, selectedID_ComboBox);
			
			dob = student.getDOB();
			System.out.println("student DOB for " + selectedID_ComboBox + ": " + dob + "\n"); // for check
			
			em.getTransaction().commit();

		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return dob;
			
	}
	
	
	public String tab4_DisplayAllStudents() {

		EntityManager em = null;
		String result = "";
		List<Student> students = null;
		
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("FROM Student ORDER BY id ASC").getResultList();
			
			if (students.size() == 0) {
				result = "No student database exists.";
			} else {
				for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {		
					Student student = (Student) iterator.next();
					result = result + student.toString() + "\n";
				}
			}
			em.getTransaction().commit();
		} catch (Exception e) {	
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return result;		
		
	}
	
	
}