package com.tech.search.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tech.spring.utils.Constants;

public class QueryBuilder {
	public static String buildQueryParam(List<String> words) throws UnsupportedEncodingException {
		if(words==null || words.isEmpty()) {
			return "";
		}
		String encodedTownName = URLEncoder.encode(Constants.TOWN_NAME, StandardCharsets.UTF_8.toString());
		String encodedState = URLEncoder.encode(Constants.STATE, StandardCharsets.UTF_8.toString());
		String encodedOr = URLEncoder.encode(Constants.OR, StandardCharsets.UTF_8.toString());
		String encodedDistrict = URLEncoder.encode(Constants.DISTRICT, StandardCharsets.UTF_8.toString());
		String encodedCategory = URLEncoder.encode(Constants.CATEGORY, StandardCharsets.UTF_8.toString());

		String encodedWord = URLEncoder.encode(words.get(0), StandardCharsets.UTF_8.toString());

		return String.format(Constants.QUERY_STRUCTURE, 
				Constants.Q, encodedTownName, encodedWord, 
				encodedOr, encodedState, encodedWord, 
				encodedOr,encodedDistrict, encodedWord, 
				encodedOr,encodedCategory, encodedWord);
	}

	public static String buildFilterdQuery(List<String> words) throws UnsupportedEncodingException {
		if (words == null || words.size() < 2) {
			return "";
		}
		String encodedTownName = URLEncoder.encode(Constants.TOWN_NAME, StandardCharsets.UTF_8.toString());
		String encodedState = URLEncoder.encode(Constants.STATE, StandardCharsets.UTF_8.toString());
		String encodedOr = URLEncoder.encode(Constants.OR, StandardCharsets.UTF_8.toString());
		String encodedDistrict = URLEncoder.encode(Constants.DISTRICT, StandardCharsets.UTF_8.toString());
		String encodedCategory = URLEncoder.encode(Constants.CATEGORY, StandardCharsets.UTF_8.toString());
		String encodedFq = URLEncoder.encode(Constants.FQ, StandardCharsets.UTF_8.toString());

		return words.stream().skip(1).map(word -> {
			try {
				String encodedWord = URLEncoder.encode(word, StandardCharsets.UTF_8.toString());
				return String.format(Constants.QUERY_STRUCTURE,
						encodedFq, encodedTownName, encodedWord,
						encodedOr, encodedState, encodedWord,
						encodedOr, encodedDistrict, encodedWord,
						encodedOr, encodedCategory, encodedWord);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.joining("&"));
	}

	public static String getRefinedWord() {
		return "";

	} 

	public static List<String> filterWords(String finalStringParam, List<String> wordsToRemove) {
		List<String> words = new ArrayList<>(Arrays.asList(finalStringParam.split(" ")));
		int character = 0;
		while (character < words.size()) {
			if (wordsToRemove.contains(words.get(character))) {
				words.remove(character);
			} else {
				character++;
			}
		}
		return words;
	}
}
