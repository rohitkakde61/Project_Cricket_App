package com.crick.apis.service;

import java.util.List;
import java.util.Map;

import com.crick.apis.entites.Match;

//lose coupling 
public interface MatchService {
	
	List<Match> getAllMatches();
	List<Match> getLiveMatches();
	
	
	
	
	List<Map<String,String>> getPointTable();
	
	
	
	//get all match 
	//get live matches 
	//get cwc2023 point table
	

}
