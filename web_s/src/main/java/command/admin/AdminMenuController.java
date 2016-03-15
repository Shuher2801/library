package command.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import resources.ConfigurationManager;

@Controller

public class AdminMenuController {

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String mainMenu() {
		return "view/menu";
	}

	@RequestMapping(value = "/adminLoginForm", method = RequestMethod.GET)
	public String displayAdminLoginForm() {
		return "view/adminLogin";
	}
	
	@RequestMapping(value = "/adminMenu", method = RequestMethod.GET)
	public String adminLogin() {
		return "adminPages/adminMenu";
	}
	
	@RequestMapping(value = "/userMenu", method = RequestMethod.GET)
	public String userMenu() {
		return "userPages/userMenu";
	}

}
