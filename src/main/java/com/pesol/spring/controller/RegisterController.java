package com.pesol.spring.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pesol.spring.entity.User;
import com.pesol.spring.model.UserModel;
import com.pesol.spring.service.UserService;

@Controller
public class RegisterController {
	
	private Logger logger = Logger.getLogger(RegisterController.class.getName());

	@Autowired
	private UserService userService;
	
	@InitBinder
	public void init(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/register")
	public String renderRegister(Model model) {
		model.addAttribute("userModel", new UserModel());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute UserModel userModel, 
			BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			logger.info(result.toString());
			return "register";
		}
		
		User user = userService.findByUsername(userModel.getUsername());
		if (user != null) {
			model.addAttribute("errorMsg", "Username is already exists");
			return "register";
		}
		
		userService.save(userModel);
		return "redirect:/register";
	}
}
