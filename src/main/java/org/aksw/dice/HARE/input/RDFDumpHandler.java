package org.aksw.dice.HARE.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.aksw.dice.HARE.model.Entity;
import org.aksw.dice.HARE.model.ResourceClass;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import com.google.gson.Gson;

public class RDFDumpHandler {
	static Set<Resource> listofResources;

	public List<Entity> getResults(String datasetName, String classname) {
		List<Entity> results = new ArrayList<Entity>();
		FileManager.get().addLocatorClassLoader(RDFDumpHandler.class.getClassLoader());

		Model model = FileManager.get().loadModel("dataset/" + datasetName + ".ttl");
		String queryString = null;
		if (classname.contains("#Statement")) {
			queryString = "SELECT ?entity ?r\n" + "WHERE {\n" + "?entity <http://aksw.org/property/hareRank> ?r . \n "
					+ "?entity a  <" + classname + ">.\n }";
		} else if (classname.equals("All")) {
			queryString = "SELECT ?entity ?r\n" + "WHERE {\n" + "?entity <http://aksw.org/property/hareRank> ?r \n"
					+ "FILTER (\n" + "     !EXISTS {?entity a  <http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement>"
					+ "})}";
		} else {
			queryString = "SELECT ?entity ?r\n" + "WHERE {\n" + "?entity <http://aksw.org/property/hareRank> ?r . \n "
					+ "?entity a  <" + classname + ">.\n" + "FILTER (\n"
					+ "     !EXISTS {?entity a  <http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement>" + "})}";
		}
		Query query = QueryFactory.create(queryString);

		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				String rank = soln.getLiteral("r").toString();
				results.add(new Entity(soln.getResource("entity").toString(), rank.substring(0, rank.indexOf("^"))));

			}
		} finally {
			qexec.close();
		}
		System.out.println(new Gson().toJson(results));
		return results;
	}

	public List<Entity> getAllResults(String datasetName) {
		List<Entity> results = new ArrayList<Entity>();
		FileManager.get().addLocatorClassLoader(RDFDumpHandler.class.getClassLoader());

		Model model = FileManager.get().loadModel("dataset/" + datasetName + ".ttl");

		String queryString = "SELECT ?entity ?r\n" + "WHERE {\n" + "?entity <http://aksw.org/property/hareRank> ?r \n"
				+ "FILTER (\n" + "     !EXISTS {?entity a  <http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement>"
				+ "})}";
		Query query = QueryFactory.create(queryString);

		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				String rank = soln.getLiteral("r").toString();
				results.add(new Entity(soln.getResource("entity").toString(), rank.substring(0, rank.indexOf("^"))));

			}
		} finally {
			qexec.close();
		}
		System.out.println(new Gson().toJson(results));
		return results;
	}

	public List<ResourceClass> getAllClassName(String datasetName) {
		List<ResourceClass> results = new ArrayList<ResourceClass>();
		FileManager.get().addLocatorClassLoader(RDFDumpHandler.class.getClassLoader());
		Model dataset = FileManager.get().loadModel("dataset/" + datasetName + ".ttl");
		String queryString = "SELECT distinct ?class { ?x a ?class }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, dataset);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				results.add(new ResourceClass(soln.getResource("class").toString()));

			}
		} finally {
			qexec.close();
		}
		System.out.println(new Gson().toJson(results));
		return results;
	}

}
