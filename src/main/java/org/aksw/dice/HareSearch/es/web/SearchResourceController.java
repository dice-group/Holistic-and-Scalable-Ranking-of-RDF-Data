package org.aksw.dice.HareSearch.es.web;

import org.aksw.simba.HARE.input.RDFDumpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
public class SearchResourceController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@Autowired
	RDFDumpHandler entityRepo;

	@RequestMapping(value = "/query/rank")
	public String getRank(Model model, @PathVariable final String datasetName, String classname) {
		model.addAttribute("entities", new Gson().toJson(entityRepo.getResults(datasetName, classname)));
		return "results";
	}

	@RequestMapping(value = "/query/class")
	public String getAllClass(Model model, @PathVariable final String datasetName) {
		model.addAttribute("entities", new Gson().toJson(entityRepo.getAllClassName(datasetName)));
		return "results";
	}

}
