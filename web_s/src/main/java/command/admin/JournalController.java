package command.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.by.library.entity.Journal;
import it.by.library.services.IJournalService;
import it.by.library.services.exception.ServiceException;

/**
 * displays Journal list where books were not returned
 * 
 * @author NotePad.by
 *
 */
@Controller
public class JournalController {

	private static Logger log = Logger.getLogger(JournalController.class);
	@Autowired
	private IJournalService journalServices;

	@RequestMapping(value = "/journal", method = RequestMethod.POST)
	public String execute(ModelMap model) {
		String page = null;
		display(model);

		// page = ConfigurationManager.getProperty("path.page.orderList");
		page = "adminPages/orderList";
		return page;
	}

	@RequestMapping(value = "/bookWasReturened", method = RequestMethod.GET)
	public String bookWasReturened(@RequestParam("id") String a, ModelMap model) {
		Long id = Long.parseLong(a);

		try {
			journalServices.updateById(id);

		} catch (ServiceException e) {
			e.printStackTrace();
			log.error("error in BookWasReturnedCommand/execute: " + e);
		}

		List<Journal> list2 = null;
		try {
			list2 = journalServices.findByTimeReturn();
		} catch (ServiceException e) {
			e.printStackTrace();
			log.error("error in BookWasReturnedCommand/execute: " + e);
		}

		for (Journal j : list2) {
			File file = new File("C:/Users/NotePad.by/workspace/MyLibraryHiber/ArchiveJornal");
			PrintWriter pr = null;
			try {
				pr = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				pr.println(j.toString());
			} catch (IOException e) {
				log.error("error in BookWasReturnedCommand/execute: " + e);
				e.printStackTrace();
			} finally {
				pr.flush();
				pr.close();
			}
		}
		try {
			journalServices.remove(id);
		} catch (ServiceException e) {
			log.error("error in BookWasReturnedCommand/execute: " + e);
			e.printStackTrace();
		}

		display(model);

		
		// ConfigurationManager.getProperty("path.page.orderList");
		String page = "adminPages/orderList";
		return page;

	}

	public void display(ModelMap model) {

		List<Journal> list = null;
		try {
			list = journalServices.findByTime();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		model.put("journal", list);

	}

}
