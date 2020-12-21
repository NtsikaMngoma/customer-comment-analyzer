package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.ikhokha.techcheck.matcher.contract.IMatcherContract;

public class CommentAnalyzer implements Callable<Map<String, Integer>> {
	
	private File file;
	private List<IMatcherContract> matcher;
	
	public CommentAnalyzer(File file, List<IMatcherContract> matcher) {
		this.file = file;
		this.matcher = matcher;
	}
	
	/**
	 * @return {@link Map}
	 */
	public Map<String, Integer> analyze() {
		Map<String, Integer> resultsMap = new HashMap<>();		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {			
			String line = null;
			while ((line = reader.readLine()) != null) {
				for (IMatcherContract matcher:  matcher) {
					int count = matcher.count(line);
					boolean validCount = count > 0;
					if (validCount) {
						incOccurrence(resultsMap, count, matcher.getReportKey());
					}
				}
			}		
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}	
		return resultsMap;
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, int wordCount, String key) {
		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + wordCount);
	}

	/**
	 * return {@link Map}
	 */
	@Override
	public Map<String, Integer> call() throws Exception {
		// TODO Auto-generated method stub
		return analyze();
	}

}
