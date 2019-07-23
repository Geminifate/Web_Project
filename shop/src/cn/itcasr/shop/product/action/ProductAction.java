package cn.itcasr.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.category.service.CategoryService;
import cn.itcasr.shop.category.vo.Category;
import cn.itcasr.shop.product.service.ProductService;
import cn.itcasr.shop.product.vo.Product;
import cn.itcasr.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	private ProductService productService;
	//���ܷ���cid
	private Integer cid;
	//ע��һ�������service
	private CategoryService categoryService;
	//���յ�ǰҳ
	private int page;
	//���ն�������csid
	private int csid;
	public int getCsid() {
		return csid;
	}
	public void setCsid(int csid) {
		this.csid = csid;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@Override
	public Product getModel() {
		return product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String findByPid() {
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	public String findByCid() {
		PageBean<Product> pageBean=productService.findByCid(cid,page);//����һ�������ѯ��Ʒ������ҳ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	public String findByCsid() {
		PageBean<Product> pageBean=productService.findByCsid(csid,page);//����һ�������ѯ��Ʒ������ҳ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
