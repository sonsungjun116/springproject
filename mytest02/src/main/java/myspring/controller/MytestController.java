package myspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import myspring.service.MytestService;

@Controller
public class MytestController {

	@Autowired
	private MytestService service;
}
