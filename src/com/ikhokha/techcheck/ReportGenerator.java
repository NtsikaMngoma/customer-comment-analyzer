package com.ikhokha.techcheck;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ikhokha.techcheck.matcher.contract.IMatcherContract;
import com.ikhokha.techcheck.matcher.service.CommonWordService;
import com.ikhokha.techcheck.matcher.service.WordLengthService;
import com.ikhokha.techcheck.matcher.service.WordMatcherService;
import com.ikhokha.techcheck.utils.Constants;
import com.ikhokha.techcheck.utils.ThreadRuntimeValidator;

public class ReportGenerator {

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
	
	/**
	 * @param files
	 * @param results
	 * @throws Exception
	 */
	public static void generateReport(File[] files, Set<Future<Map<String, Integer>>> results) throws Exception {
		int nThreads = ThreadRuntimeValidator.coresInEnv();
		ExecutorService service = Executors.newFixedThreadPool(nThreads);
		List<IMatcherContract> matcherList = createReportMatcher();
		for (File file : files) {
			Callable<Map<String, Integer>> callable = new CommentAnalyzer(file, matcherList);
			Future<Map<String, Integer>> future = service.submit(callable);
			results.add(future);
		}
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private static List<IMatcherContract> createReportMatcher() throws Exception {
		// TODO Auto-generated method stub
		List<IMatcherContract> matchers = Arrays.asList(
				new CommonWordService(Constants.CommentIdentifiers.MOVER_MENTIONS, "Mover"),
				new CommonWordService(Constants.CommentIdentifiers.SHAKER_MENTIONS, "Shaker"),
				new WordLengthService(Constants.CommentIdentifiers.SHORTER_THAN_15, WordLengthService.Condition.LessThan, 15),
				new WordMatcherService(Constants.CommentEnhancementIdentifers.SPAM, Constants.UrlRegexRules.URL_REGEX),
				new WordMatcherService(Constants.CommentEnhancementIdentifers.QUESTIONS, "[?]")
		);
		return matchers;
	}

	/**
	 * @param fileResults
	 * @param totalResults
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void findFileReport(Set<Future<Map<String, Integer>>> fileResults, Map<String, Integer> totalResults) throws InterruptedException, ExecutionException {
		for (Future<Map<String, Integer>> future : fileResults) {
			addReportResults(future.get(), totalResults);
		}
	}
}
