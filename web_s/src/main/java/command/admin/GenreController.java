package command.admin;

import java.util.List;

import javax.persistence.SequenceGenerator;
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

import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Genres;
import it.by.library.services.IGenreService;
import it.by.library.services.IJournalService;
import it.by.library.services.exception.ServiceException;
import it.by.library.services.impl.GenreServices;
import it.by.library.services.impl.JournalServices;
import resources.MessageManager;

/**
 * Display all genres
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

	@RequestMapping(value = "/genreList", method = RequestMethod.POST)
	public String displayGenre(ModelMap model) {
		display(model);
		return "adminPages/genreList";
	}

	@RequestMapping(value = "/addGenre", method = RequestMethod.POST)
	@Secured(value = { "USER" })
	public String addGenre(ModelMap model, @ModelAttribute("genre") @Valid Genres genre, BindingResult br) {
		String page = null;
		if (!br.hasErrors()) {
			try {
				genreServices.add(genre);
			} catch (ServiceException e) {
				e.printStackTrace();
				model.put("genre", genre);
			}
			// display(model);
			List<Genres> genres = null;
			try {
				genres = genreServices.getAll();
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
			}

			model.put("genres", genres);

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
		return page = "adminPages/genreList";
	}

	@RequestMapping(value = "/deleteGenre", method = RequestMethod.GET)
	public String removeGenre(@RequestParam("id") String id, ModelMap model) {
		String page = null;
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

			page = "adminPages/genreList";

		} else {

			display(model);

			model.put("errorCantRemoveMessage", MessageManager.getProperty("message.wrongremove"));
			page = "adminPages/genreList";
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
