package org.aksw.dice.HARE.input;

import java.util.Set;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RSIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

public class RDFDumpHandler {
	static Set<Resource> listofResources;

	public String getResults(String datasetName, String classname) {
		String results = null;
		FileManager.get().addLocatorClassLoader(RDFDumpHandler.class.getClassLoader());

		Model model = FileManager.get().loadModel("dataset/" + datasetName + ".ttl");

		String queryString = "SELECT ?entity\n" + "WHERE {\n" + "?entity rdf:type ? <http://dbpedia.org/class/ "
				+ classname + ">\n" + "}";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				Resource res = soln.getResource("entity");
				Literal rank = soln.getLiteral("r");
				System.out.println(res + " " + rank);
			}
		} finally {
			qexec.close();
		}

		return results;
	}

	public String getAllResults(String datasetName) {
		String results = null;
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
				Resource res = soln.getResource("entity");
				Literal rank = soln.getLiteral("r");
				System.out.println(res + " " + rank);
			}
		} finally {
			qexec.close();
		}

		return results;
	}

	public String getAllClassName(String datasetName) {
		String results = null;
		FileManager.get().addLocatorClassLoader(RDFDumpHandler.class.getClassLoader());

		Model dataset = FileManager.get().loadModel("data/" + datasetName + ".ttl");
		String queryString = "SELECT distinct ?class { ?class a owl:Class }";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, dataset);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				Resource name = soln.getResource("class");
				System.out.println(name);
			}
		} finally {
			qexec.close();
		}

		return results;
	}

}
