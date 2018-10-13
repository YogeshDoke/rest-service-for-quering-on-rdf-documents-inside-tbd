
package com.endpointrestservice.endpointrestservice;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.springframework.stereotype.Component;


@Component
public class QueryprocessorService {

    static final String inputFileName = "/home/yogesh/Videos/uni.ttl";
    static final String oformart = "ttl";

    

    private interface Format {

        String RDF = "RDF/XML-ABBREV";
        String TTL = "TTL";
    }

    
    public String Tbdqueryprocessor(String University) {

        String inputFile = inputFileName;
        String outputFormat = oformart;

        String directory = "/home/yogesh/Videos/JavaApplication8/src/javaapplication8/tbd";
        Dataset dataset = TDBFactory.createDataset(directory);

        Model tdb = dataset.getDefaultModel();

        InputStream in = FileManager.get().open(inputFile);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFile + " not found");
        }

        String sparqlQueryString = "PREFIX  dc:  <http://purl.org/dc/elements/1.1/>\n"
                + "\n"
                + "SELECT  ?name ?university \n"
                + "\n"
                + "WHERE   {   \n"
                + "\n"
                + "			?x dc:university ?university .\n"
                + "\n"
                + "          FILTER regex(?university,\""+University+"\")\n"
                + "\n"
                + "          ?x dc:name ?name .\n"
                + "\n"
                + "			 \n"
                + "\n"
                + "        }";

        Query query = QueryFactory.create(sparqlQueryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, dataset);
        ResultSet results = qexec.execSelect();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results); // and turn that into a String String json = new String(outputStream.toByteArray())
        String json = new String(outputStream.toByteArray());
        dataset.close();
        return json;

    }

}
