package org.aksw.simba.HARE.input;

import java.util.Set;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

public class RDFDumpHandler {
	static Set<Resource> listofResources;

	public String getResults(String datasetName, String classname) {
		String results = null;
		FileManager.get().addLocatorClassLoader(RDFDumpHandler.class.getClassLoader());

		Model dataset = FileManager.get().loadModel("data/" + datasetName + ".ttl");
		String queryString = "SELECT ?entity\n" + "WHERE {\n" + "?entity rdf:type ? <http://dbpedia.org/class/ "
				+ classname + ">\n" + "}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, dataset);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				Literal name = soln.getLiteral("name");
				System.out.println(name);
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
		String queryString = "SELECT distinct ?class { ?class a owl:Class }" ;
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, dataset);
		try {
			ResultSet resultQuery = qexec.execSelect();
			while (resultQuery.hasNext()) {
				QuerySolution soln = resultQuery.nextSolution();
				Literal name = soln.getLiteral("name");
				System.out.println(name);
			}
		} finally {
			qexec.close();
		}

		return results;
	}
	
}
