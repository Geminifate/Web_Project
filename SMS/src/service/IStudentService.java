package service;
import beans.Student;

public interface IStudentService {
	//���û�������֤
	Student checkUser(String num, String password);
	//��DB�����student
	Integer saveStudent(Student student);
}