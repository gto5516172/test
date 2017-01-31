package com.bety.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("myUpload")
public class MyUploadController {
	@RequestMapping(value = "view", method = RequestMethod.POST)
	public String viewInfo(String inputId,String max,String imgId,Model model) {
		model.addAttribute("inputId", inputId);
		model.addAttribute("max", max);
		model.addAttribute("imgId", imgId);
		return "common/upload1";
	}
}
