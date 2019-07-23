package cn.itcasr.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.categorysecond.service.CategorySecondService;
import cn.itcasr.shop.categorysecond.vo.CategorySecond;
import cn.itcasr.shop.product.service.ProductService;
import cn.itcasr.shop.product.vo.Product;
import cn.itcasr.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	private ProductService productService;
	@Override
	public Product getModel() {
		return product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//接收到page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public String findAll() {
		PageBean<Product> pageBean=productService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public String addPage() {
		List<CategorySecond> cslist=categorySecondService.finAll();
		ActionContext.getContext().getValueStack().set("csList", cslist);
		return "addPageSuccess";
	}
	//文件上传需要的参数
	private File upload;//上传文件
	private String uploadFileName;//接收文件上传的文件名
	private String uploadContextType;//接收文件上传的文件的MIME类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	public String save() throws IOException {
		product.setPdate(new Date());
		if (upload!=null) {
			//获得文件上传的磁盘绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	public String delete(){
		product =productService.findByPid(product.getPid());
		//删除上传的图片
		String path =product.getImage();
		if (path!=null) {
			String realPath =ServletActionContext.getServletContext().getRealPath("/"+path);
			File file =new File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	public String edit(){
		product =productService.findByPid(product.getPid());
		List<CategorySecond> csList=categorySecondService.finAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	public String update() throws IOException{
		product.setPdate(new Date());
		if (upload!=null) {
			//删除原图片
			String path =product.getImage();
			File file =new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			//上传新图片
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
