package command.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import resources.ConfigurationManager;

/**
 * This controller is responsible for displaying the menu admin and user
 * @author Ilya
 *
 */
@Controller

public class AdminMenuController {

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String mainMenu() {
		return ConfigurationManager.getProperty("path.page.menu");
				
	}

	@RequestMapping(value = "/adminLoginForm", method = RequestMethod.GET)
	public String displayAdminLoginForm() {
		return ConfigurationManager.getProperty("path.page.adminLogin");
				
	}
	
	@RequestMapping(value = "/adminMenu", method = RequestMethod.GET)
	public String adminLogin() {
		return ConfigurationManager.getProperty("path.page.adminMenu");
				
	}
	
	@RequestMapping(value = "/userMenu", method = RequestMethod.GET)
	public String userMenu() {
		return ConfigurationManager.getProperty("path.page.userMenu");
				
	}

}
