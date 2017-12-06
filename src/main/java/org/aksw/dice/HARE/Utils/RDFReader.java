package org.aksw.dice.HARE.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResourceFactory;

public class RDFReader {

	
	/*if (triple.getObject().isLiteral()) {
		model.add(ResourceFactory.createStatement(
				ResourceFactory.createResource(triple.getSubject().toString()),
				ResourceFactory.createProperty(triple.getPredicate().toString()),
				ResourceFactory.createPlainLiteral(triple.getObject().toString())));
	} else {
		model.add(ResourceFactory.createStatement(
				ResourceFactory.createResource(triple.getSubject().toString()),
				ResourceFactory.createProperty(triple.getPredicate().toString()),
				ResourceFactory.createResource(triple.getObject().toString())));
	}
*/
	public void readRankFiles() {
		Model model = ModelFactory.createDefaultModel();
		Property hare = ResourceFactory.createProperty("http://aksw.org/property/hareRank");
		Property pageRank = ResourceFactory.createProperty("http://aksw.org/property/pageRank");
		

			
			
	//	RDFDataMgr.parse(destination, "/Users/Kunal/Desktop/KnowledgeBases/dbpedia.ttl");
		try (Stream<String> stream = Files.lines(Paths.get("/Users/Kunal/Desktop/Results/resources.txt"))) {
			stream.forEach(element -> {
				String[] resource = element.split(" ");
				model.addLiteral(ResourceFactory.createResource(resource[0]), hare, resource[1]);

			});
		} catch (IOException e) {

			e.printStackTrace();
		}
		try (Stream<String> triple = Files.lines(Paths.get("/Users/Kunal/Desktop/Results/triples.txt"))) {
			triple.forEach(element -> {
				String[] resource = element.split(" ");

			});
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
