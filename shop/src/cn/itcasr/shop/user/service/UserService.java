package cn.itcasr.shop.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import cn.itcasr.shop.user.dao.UserDao;
import cn.itcasr.shop.user.vo.User;
import cn.itcasr.shop.utils.MailUitls;
import cn.itcasr.shop.utils.UUIDUtils;
@Transactional
public class UserService {
	//×¢ÈëuserDAO
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	public void save(User user) {
		user.setState(0);
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		MailUitls.sendMail(user.getEmail(), code);
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	public void update(User existuser) {
		userDao.update(existuser);
	}
	public User login(User user) {
		return userDao.login(user);
	}
}
