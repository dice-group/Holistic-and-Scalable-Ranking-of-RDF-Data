package org.aksw.dice.HARE.web;

import org.aksw.dice.HARE.input.RDFDumpHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@Controller
public class SearchResourceController {

	String currentDataset;

	public SearchResourceController() {

		this.currentDataset = null;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/rankResults", method = RequestMethod.POST)
	public ResponseEntity<?> getRank(@RequestParam String className) {
		RDFDumpHandler entityRepo = new RDFDumpHandler();
		return ResponseEntity.ok(new Gson().toJson(entityRepo.getResults(this.currentDataset, className)));

	}

	@RequestMapping(value = "/getClass", method = RequestMethod.POST)
	public ResponseEntity<?> getAllClass(@RequestParam String datasetName) {
		RDFDumpHandler entityRepo = new RDFDumpHandler();
		this.currentDataset = datasetName;
		return ResponseEntity.ok(new Gson().toJson(entityRepo.getAllClassName(datasetName)));

	}

}
