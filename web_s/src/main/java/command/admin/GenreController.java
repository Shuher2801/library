package command.admin;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.by.library.entity.Genres;
import it.by.library.services.IGenreService;
import it.by.library.services.IJournalService;
import it.by.library.services.exception.ServiceException;
import resources.ConfigurationManager;
import resources.MessageManager;

/**
 * This controller is responsible for displaying, adding and deleting genres
 * 
 * @author NotePad.by
 *
 */
@Controller
@RequestMapping("/genre")
public class GenreController {

	private static Logger log = Logger.getLogger(GenreController.class);
	@Autowired
	private IGenreService genreServices;

	@Autowired
	private IJournalService journalServices;
/**
 * Display all genres
 * @param model
 * @return logical name of view
 */
	@RequestMapping(value = "/genreList", method = RequestMethod.POST)
	public String displayGenre(ModelMap model) {
		display(model);
		return ConfigurationManager.getProperty("path.page.genreList");
	}
/**
 * Method for addition genres
 * @param model
 * @param genre - entity from form
 * @param br - error check
 * @return logical name of view
 */
	
	@RequestMapping(value = "/addGenre", method = RequestMethod.POST)
	@Secured(value = { "USER" })
	public String addGenre(ModelMap model, @ModelAttribute("genre") @Valid Genres genre, BindingResult br) {
		String page ;
		if (!br.hasErrors()) {
			try {
				genreServices.add(genre);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			List<Genres> genres = null;
			try {
				genres = genreServices.getAll();
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
			}

			model.put("genres", genres);
			Genres genre1 = new Genres();

			model.put("genre", genre1);

		} else {
			List<Genres> genres = null;
			try {
				genres = genreServices.getAll();
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
			}

			model.put("genres", genres);
	
		}
		return page = ConfigurationManager.getProperty("path.page.genreList");
	}
	
	
	
/**
 * Method for deleting genres
 * @param id
 * @param model
 * @return logical name of view
 */
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.GET)
	public String removeGenre(@RequestParam("id") String id, ModelMap model) {
		String page;
		Long n = Long.parseLong(id);
		boolean flag = false;

		try {
			flag = journalServices.findByGenre(n);

		} catch (ServiceException e1) {
			e1.printStackTrace();
			log.error("error in RemoveGenreCommand/execute: " + e1);
		}

		if (flag == true) {
			try {
				genreServices.remove(n);
			} catch (ServiceException e) {
				e.printStackTrace();
				log.error("error in RemoveGenreCommand/execute: " + e);
			}

			display(model);

			page = ConfigurationManager.getProperty("path.page.genreList");

		} else {

			display(model);

			model.put("errorCantRemoveMessage", MessageManager.getProperty("message.wrongremove"));
			page = ConfigurationManager.getProperty("path.page.genreList");
		}

		return page;
	}

	public void display(ModelMap model) {

		List<Genres> genres = null;
		try {
			genres = genreServices.getAll();
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
		}
		model.put("genres", genres);
		Genres genre = new Genres();

		model.put("genre", genre);
	}
}
