package com.bbq.db.project.action;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.BookCategory;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.BookCategoryService;
import com.bbq.db.project.service.BookService;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-4
 * Time: 涓婂崍12:01
 * To change this template use File | Settings | File Templates.
 */
@Namespace("/book")
public class BookAction extends BaseAction {

	@Autowired
    private BookService bookService;
	@Autowired
    private BookCategoryService bookCategoryService;

    private int bookId;
	private int categoryId;
    private Book book;
    private List<BookCategory> categories;


    public BookCategoryService getBookCategoryService() {
		return bookCategoryService;
	}

	public void setBookCategoryService(BookCategoryService bookCategoryService) {
		this.bookCategoryService = bookCategoryService;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

    public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<BookCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<BookCategory> categories) {
		this.categories = categories;
	}

	private File uploadify;
    private String uploadifyFileName;

    public File getUploadify() {
        return uploadify;
    }

    public void setUploadify(File uploadify) {
        this.uploadify = uploadify;
    }

    public String getUploadifyFileName() {
        return uploadifyFileName;
    }

    public void setUploadifyFileName(String uploadifyFileName) {
        this.uploadifyFileName = uploadifyFileName;
    }

    @Action(value = "viewBook", results = { @Result(name = "success", location = "viewBook.jsp") })
    public String viewBook(){
        try {
            book = bookService.getBookById(bookId);
        } catch (Exception e) {
            logger.error("error: [module:BookAction][action:listMyBooks][][error:{}]", e);
        }

        return SUCCESS;
    }

    @Action(value = "preAddBook", results = { @Result(name = "success", location = "addBook.jsp"),
                                              @Result(name = "error", type = "redirectAction",
                                                      params = {"namespace", "/book", "bookId", "${bookId}", "actionName", "viewBook"})
                                                })
    public String preAddBook(){
        try {
            if(bookId > 0) {
                book = bookService.getBookById(bookId);
                Map<String, Object> session = ActionContext.getContext().getSession();
                User user = (User)session.get("user");
                if(user == null || (user.getUserId() != book.getUser().getUserId()
                                    && user.getUserType() != Constants.ADMIN)) {
                    return ERROR;
                }
            }
            categories = bookCategoryService.getAllCategory();
            
        } catch (Exception e) {
            logger.error("error: [module:BookAction][action:preAddBook][][error:{}]", e);
        }

        return SUCCESS;
    }

    @Action(value = "uploadImg")
    public String uploadImg(){
        try {
            String extName = "";
            String newFileName = "";
            String nowTimeStr = "";
            SimpleDateFormat sDateFormat;
            Random r = new Random();

            String savePath = ServletActionContext.getServletContext().getRealPath(""); //鑾峰彇椤圭洰鏍硅矾寰�
            savePath = savePath + "/uploads/";

            HttpServletResponse response  = ServletActionContext.getResponse();
            response.setCharacterEncoding("utf-8");
            int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
            sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            nowTimeStr = sDateFormat.format(new Date());

            if (uploadifyFileName.lastIndexOf(".") >= 0){
                extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
            }

            newFileName = nowTimeStr + rannum + extName;
            uploadify.renameTo(new File(savePath + newFileName));

            response.getWriter().print(newFileName);
            return null;
        } catch (Exception e) {
            logger.error("error: [module:BookAction][action:uploadImg][][error:{}]", e);
        }

        return null;
    }

    @Action(value = "addBook")
    public String addBook(){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> session = ActionContext.getContext().getSession();
            User user = (User)session.get("user");
            if(user == null) {
                map.put("code", Constants.NO_DATA);
            } else {
            	BookCategory category = bookCategoryService.getCategoryById(categoryId);
            	Book newbook = new Book();
            	newbook.setAuthor(book.getAuthor());
            	newbook.setBookId(book.getBookId());
            	newbook.setIsbn(book.getIsbn());
            	newbook.setPic(book.getPic());
            	newbook.setPrice(book.getPrice());
            	newbook.setPublisher(book.getPublisher());
            	newbook.setPublishTime(book.getPublishTime());
            	newbook.setQuantity(book.getQuantity());
            	newbook.setTitle(book.getTitle());
            	newbook.setUser(book.getUser());
            	newbook.setCategory(category);
                String code = bookService.insertOrUpdateBook(newbook, user);
                map.put("code", code);
                map.put("bookId", book.getBookId());
            }
        } catch (Exception e) {
            logger.error("error: [module:BookAction][action:addBook][][error:{}]", e);
        }

        StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
        return null;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
