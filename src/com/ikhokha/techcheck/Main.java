package com.ikhokha.techcheck;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ikhokha.techcheck.matcher.contract.IPatternMatcher;
import com.ikhokha.techcheck.matcher.service.CommonWordService;
import com.ikhokha.techcheck.matcher.service.WordLengthService;
import com.ikhokha.techcheck.matcher.service.WordMatcherService;
import com.ikhokha.techcheck.utils.Constants;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Map<String, Integer> totalResults = new HashMap<>();
		Set<Future<Map<String, Integer>>> fileResults = new HashSet<>();		
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		generateReport(commentFiles, fileResults);
		findFileReport(fileResults, totalResults);
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			target.putIfAbsent(entry.getKey(), 0);
			target.put(entry.getKey(), target.get(entry.getKey()) + entry.getValue());
		}
		
	}
	
	private static void generateReport(File[] files, Set<Future<Map<String, Integer>>> results) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(Constants.THREAD_POOL_COUNT);
		List<IPatternMatcher> matcherList = createReportMatcher();
		for (File file : files) {
			@SuppressWarnings("unchecked")
			Callable<Map<String, Integer>> callable = new CommentAnalyzer(file, matcherList);
			Future<Map<String, Integer>> future = service.submit(callable);
			results.add(future);
		}
	}

	private static List<IPatternMatcher> createReportMatcher() throws Exception {
		// TODO Auto-generated method stub
		List<IPatternMatcher> matchers = Arrays.asList(
				new CommonWordService(Constants.CommentIdentifiers.MOVER_MENTIONS, "Mover"),
				new CommonWordService(Constants.CommentIdentifiers.SHAKER_MENTIONS, "Shaker"),
				new WordLengthService(Constants.CommentIdentifiers.SHORTER_THAN_15, 15, WordLengthService.Condition.LessThan),
				new WordMatcherService(Constants.CommentEnhancementIdentifers.SPAM, Constants.UrlRegexRules.URL_REGEX),
				new WordMatcherService(Constants.CommentEnhancementIdentifers.QUESTIONS, "[?]")
		);
		return matchers;
	}

	public static void findFileReport(Set<Future<Map<String, Integer>>> fileResults, Map<String, Integer> totalResults) throws InterruptedException, ExecutionException {
		for (Future<Map<String, Integer>> future : fileResults) {
			addReportResults(future.get(), totalResults);
		}
	}
}
