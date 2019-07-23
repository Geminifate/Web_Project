package cn.itcasr.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.category.service.CategoryService;
import cn.itcasr.shop.category.vo.Category;
import cn.itcasr.shop.categorysecond.service.CategorySecondService;
import cn.itcasr.shop.categorysecond.vo.CategorySecond;
import cn.itcasr.shop.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecond categorySecond= new CategorySecond();
	private CategorySecondService categorySecondService;
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	public String findAll() {
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//注入一级分类service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String addPage() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	public String delete() {
		//如果级联删除，先查询再删除，配置cascade
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	public String edit() {
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	public String update() {
		categorySecondService.update(categorySecond);
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "updateSuccess";
	}
}
