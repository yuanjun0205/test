package cn.cloudbae.gson.demo7;

import java.util.List;
import java.util.Map;

public class CClass {
	private String classNo;
	private String className;
	private Teacher teacher;
	private Map<String,Teacher> otherTeachers;
	private List<Student> students;
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	@Override
	public String toString() {
		return "CClass [classNo=" + classNo + ", className=" + className + ", teacher=" + teacher + ",students=" + students + ",otherTeachers=" + otherTeachers
				+ "]";
	}
	public Map<String,Teacher> getOtherTeachers() {
		return otherTeachers;
	}
	public void setOtherTeachers(Map<String,Teacher> otherTeachers) {
		this.otherTeachers = otherTeachers;
	}
	
}
