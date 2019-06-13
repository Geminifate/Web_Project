package dao;

import beans.Student;

public interface IStudentDao {

	Student selectStudentLogin(String num, String password);

	Integer insertStudent(Student student);

}
