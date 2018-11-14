package com.kali.services.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Initialize dashboard controller.
 * 
 * @author kali
 *
 */
@Controller
public class Home {

	/**
	 * Redirect to the hystrix dashboard.
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}

}
