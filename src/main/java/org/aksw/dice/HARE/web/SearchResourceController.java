package org.aksw.dice.HARE.web;

import org.aksw.dice.HARE.input.RDFDumpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@Controller
@RequestMapping("/search")
public class SearchResourceController {

	String currentDataset;

	public SearchResourceController() {
		this.currentDataset = null;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@Autowired
	RDFDumpHandler entityRepo;

	@RequestMapping(value = "/rankResults/{className}")
	public String getRank(Model model, @PathVariable String className) {
		model.addAttribute("entities", new Gson().toJson(entityRepo.getResults(this.currentDataset, className)));
		return "results";
	}

	@RequestMapping(value = "/getAllRankedResources/{datasetName}")
	public String getAllResult(Model model, @PathVariable String datasetName) {
		model.addAttribute("entities", new Gson().toJson(entityRepo.getAllResults(datasetName)));
		return "results";
	}

	@RequestMapping(value = "/getClass/{datasetName}")
	public String getAllClass(Model model, @PathVariable String datasetName) {
		model.addAttribute("entities", new Gson().toJson(entityRepo.getAllClassName(datasetName)));
		return "results";
	}

}
