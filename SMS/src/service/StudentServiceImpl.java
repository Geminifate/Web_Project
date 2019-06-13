package service;

import beans.Student;
import dao.IStudentDao;
import dao.StudentDaoImpl;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao dao;
	public StudentServiceImpl() {
		dao=new StudentDaoImpl();
	}
	@Override
	public Student checkUser(String num, String password) {
		Student student= dao.selectStudentLogin(num,password);
		return student;
//		return dao.selectStudentLogin(num,password);
	}
	@Override
	public Integer saveStudent(Student student) {
		return dao.insertStudent(student);
	}

}
