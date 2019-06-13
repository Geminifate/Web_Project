package service;
import beans.Student;

public interface IStudentService {
	//对用户进行验证
	Student checkUser(String num, String password);
	//向DB中添加student
	Integer saveStudent(Student student);
}