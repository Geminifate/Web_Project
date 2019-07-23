package cn.itcasr.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcasr.shop.categorysecond.dao.CategorySecondDao;
import cn.itcasr.shop.categorysecond.vo.CategorySecond;
import cn.itcasr.shop.utils.PageBean;

@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean<>();
		pageBean.setPage(page);
		int limit =10;
		pageBean.setLimit(limit);
		int totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=(int) Math.ceil(1.0*totalCount/limit);
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> finAll() {
		return categorySecondDao.findAll();	
	}
}
