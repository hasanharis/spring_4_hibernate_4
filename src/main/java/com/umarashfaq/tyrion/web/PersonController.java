package com.umarashfaq.tyrion.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.umarashfaq.tyrion.business.PersonManager;
import com.umarashfaq.tyrion.domain.Person;

@Controller
public class PersonController {

	@Autowired
	PersonManager personManager;
	
	@RequestMapping(value="/person", method=RequestMethod.GET)
	public ModelAndView get(){
		
		List<Person> users = personManager.findAll();
		return new ModelAndView("person").addObject("persons", users);
	}
}
