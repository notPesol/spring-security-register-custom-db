package com.pesol.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pesol.spring.entity.User;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@GetMapping
	public String renderProfile(Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			return "redirect:/custom-login";
		}
		
		model.addAttribute("theUser", user);
		
		return "profile";
	}
}
