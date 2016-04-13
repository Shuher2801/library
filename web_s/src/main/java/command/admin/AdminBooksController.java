package command.admin;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.by.library.entity.Books;
import it.by.library.entity.Genres;
import it.by.library.services.IBookService;
import it.by.library.services.IGenreService;
import it.by.library.services.IJournalService;
import it.by.library.services.exception.ServiceException;
import resources.ConfigurationManager;
import resources.MessageManager;
/**
 * This controller is responsible for adding and deleting books
 * @author Ilya
 *
 */
@Controller
@RequestMapping("/adminPages")
public class AdminBooksController {
	private static Logger log = Logger.getLogger(AdminBooksController.class);

	@Autowired
	private IJournalService journalServices;

	@Autowired
	private IGenreService genreServices;

	@Autowired
	private IBookService booksServices;
/**
 * Method for displaying all books
 * @param model
 * @param page
 * @return logical name of view
 */
	@RequestMapping(value = "/adminPage/{page}", method = { RequestMethod.POST, RequestMethod.GET })
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

		List<Genres> genres = null;
		try {
			genres = genreServices.getAll();
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
		}
		model.put("genre", genres);

		return  ConfigurationManager.getProperty("path.page.adminPage");
	}
/**
 * Method for addition book to library with validation parameters from form
 * @param book - entity from form
 * @param br -  error check
 * @param model
 * @param g
 * @return
 */

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") @Valid Books book, BindingResult br, ModelMap model,
			@RequestParam("g") String g) {
		String page;

		if (!br.hasErrors()) {
			try {
				book.setGenres((Genres) genreServices.getByGenre(g));
			} catch (ServiceException e1) {
				e1.printStackTrace();
				log.error("error in AddCommand/execute: " + e1);
			}

			try {
				booksServices.add(book);
			} catch (ServiceException e) {
				log.error("error in AddCommand/execute: " + e);
				e.printStackTrace();
			}
			display(model);
		}
		else {
			int pageB = 1;
			int recordsPerPage = 5;

			List<Books> books = booksServices.list((pageB - 1) * recordsPerPage, recordsPerPage);

			long noOfRecords = booksServices.count();

			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

			model.addAttribute("noOfPages", noOfPages);
			model.addAttribute("currentPage", pageB);

			model.put("books", books);
			List<Genres> genres = null;
			try {
				genres = genreServices.getAll();
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
			}
			model.put("genre", genres);
		}
		

		return page = ConfigurationManager.getProperty("path.page.adminPage");
	}
	
	
	
	
	
/**
 * Method for removal book
 * @param a
 * @param model
 * @return logical name of view
 */
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String execute(@RequestParam("id") String a, ModelMap model) {
		String page;
		Long id = Long.parseLong(a);
		boolean flag = false;

		try {
			flag = journalServices.findByBook(id);

		} catch (ServiceException e1) {
			e1.printStackTrace();
			log.error("error in RemoveCommand/execute: " + e1);
		}

		if (flag == true) {
			try {
				booksServices.remove(id);
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
			}
			display(model);

			 page = ConfigurationManager.getProperty("path.page.adminPage");
			return page;
		} else {
			display(model);
			model.put("errorCantRemoveMessage", MessageManager.getProperty("message.wrongremove_book"));
			 page = ConfigurationManager.getProperty("path.page.adminPage");
		}
		return page;
	}
/**
 * Method for pagination
 * @param model
 */
	public void display(ModelMap model) {

		int pageB = 1;
		int recordsPerPage = 5;

		List<Books> books = booksServices.list((pageB - 1) * recordsPerPage, recordsPerPage);

		long noOfRecords = booksServices.count();

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("currentPage", pageB);

		model.put("books", books);
		Books book1 = new Books();
		model.put("book", book1);

		List<Genres> genres = null;
		try {
			genres = genreServices.getAll();
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
		}
		model.put("genre", genres);

	}

}