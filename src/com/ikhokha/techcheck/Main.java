package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Map<String, Integer> totalResults = new HashMap<>();
		Set<Future<Map<String, Integer>>> fileResults = new HashSet<>();		
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		ReportGenerator.generateReport(commentFiles, fileResults);
		ReportGenerator.findFileReport(fileResults, totalResults);
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
}
