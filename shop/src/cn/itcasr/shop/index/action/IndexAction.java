package cn.itcasr.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcasr.shop.category.service.CategoryService;
import cn.itcasr.shop.category.vo.Category;
import cn.itcasr.shop.product.service.ProductService;
import cn.itcasr.shop.product.vo.Product;

public class IndexAction extends ActionSupport{
	private CategoryService categoryService;
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//������ҳ
	public String execute() {
		//��ѯ����һ������
		List<Category> cList=categoryService.findAll();
		//��һ��������뵽session��Χ
		ActionContext.getContext().getSession().put("cList", cList);
		List<Product> hList=productService.findHot();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
