package command.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.by.library.entity.Books;
import it.by.library.entity.Journal;
import it.by.library.entity.Users;
import it.by.library.services.IBookService;
import it.by.library.services.IJournalService;
import it.by.library.services.IUserService;
import it.by.library.services.exception.ServiceException;
import resources.ConfigurationManager;
/**
 * this controller is responsible for displaying a list of books the user and a selection of books  
 * @author Ilya
 *
 */
@Controller
@RequestMapping("/userPages")
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IJournalService journalServices;

	@Autowired
	private IBookService booksServices;

	@Autowired
	private IUserService userServices;
/**
 * Method for a selection of books 
 * @param model
 * @param s - Long[]
 * @return logical name of view
 */
	@RequestMapping(value = "/selectBooks", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectBook(ModelMap model, @RequestParam("idBook") Long[] s) {

		String page = null;

		long curTime = System.currentTimeMillis();
		Date curDate = new Date(curTime);

		Users user = null;
		try {
			user = userServices.getUserByName(getPrincipal());
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}

		List<Books> bookList = new ArrayList<>();
		for (Long id : s) {
			try {
				bookList.add(booksServices.getById(id));
			} catch (NumberFormatException | ServiceException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < bookList.size(); i++) {
			try {
				journalServices.add(new Journal(bookList.get(i), user, curDate, null));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		List<Journal> list = null;
		try {
			list = journalServices.findByUser(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		model.put("journal", list);

	    page = ConfigurationManager.getProperty("path.page.userBooks");
		return page;
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
/**
 * Method for displaying a list of books the user
 * @param model
 * @return logical name of view
 */
	@RequestMapping(value = "/userBooks", method = { RequestMethod.POST, RequestMethod.GET })
	public String displayBook(ModelMap model) {

		Users user = null;
		try {
			user = userServices.getUserByName(getPrincipal());
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		List<Journal> list = null;
		try {
			list = journalServices.findByUser(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		model.put("journal", list);

		String page = ConfigurationManager.getProperty("path.page.userBooks");
		return page;
	}
/**
 * Method for pagination
 * @param model
 * @param page
 * @return logical name of view
 */
	@RequestMapping(value = "/userPage/{page}", method = { RequestMethod.POST, RequestMethod.GET })
	public String execute(ModelMap model, @PathVariable Integer page) {

		int pageB = 1;
		int recordsPerPage = 5;

		if (page != null)
			pageB = page;

		List<Books> books = booksServices.list((pageB - 1) * recordsPerPage, recordsPerPage);

		long noOfRecords = booksServices.count();

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("currentPage", pageB);

		model.put("books", books);
		Books book = new Books();
		model.put("book", book);
		return "userPages/userPage";

	}
}