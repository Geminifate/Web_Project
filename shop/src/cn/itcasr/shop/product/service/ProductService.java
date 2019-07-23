package cn.itcasr.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcasr.shop.product.dao.ProductDao;
import cn.itcasr.shop.product.vo.Product;
import cn.itcasr.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);//设置每页显示8个
		int totalCount=0;
		totalCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);//总记录数
		int totalPage=0;
		totalPage=(int) Math.ceil(totalCount/limit);//总页数，totalCount/limit向上取整
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
//二级分类查询
	public PageBean<Product> findByCsid(int csid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);//设置每页显示8个
		int totalCount=0;
		totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);//总记录数
		int totalPage=0;
		totalPage=(int) Math.ceil(1.0*totalCount/limit);//总页数，totalCount/limit向上取整
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findAll(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit=10;
		pageBean.setLimit(limit);
		int totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=(int) Math.ceil(1.0*totalCount/limit);
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findAll(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
	}

	public void update(Product product) {
		productDao.update(product);
	}
}
