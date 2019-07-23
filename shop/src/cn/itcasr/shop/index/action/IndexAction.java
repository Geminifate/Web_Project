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
	//访问首页
	public String execute() {
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		//将一级分类放入到session范围
		ActionContext.getContext().getSession().put("cList", cList);
		List<Product> hList=productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
