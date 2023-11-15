package com.crick.apis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.util.Elements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.crick.apis.entites.Match;
import com.crick.apis.repositers.MatchRepo;


@Service

public class MatchServiceImpl  implements MatchService{
	
	private MatchRepo matchrepo;
	
	
	

	public MatchServiceImpl(MatchRepo matchrepo) {
		
		this.matchrepo = matchrepo;
	}


	@Override
	
	public List<Match> getAllMatches(){
		return this.matchrepo.findAll();
	}
	
	//match history all matches in our database
	
	
	@Override
	
	public List<Match> getLiveMatches(){
		 
		        List<Match> matches = new ArrayList()<>();
		        try {
		            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
		            Document document = Jsoup.connect(url).get();
		            Element liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
		            for (Element match : liveScoreElements) {
		                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
		                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
		                String matchNumberVenue = match.select("span").text();
		                Element matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
		                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
		                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
		                Element bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
		                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
		                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
		                String textLive = match.select("div.cb-text-live").text();
		                String textComplete = match.select("div.cb-text-complete").text();
		                //getting match link
		                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

		                Match match1 = new Match();
		                match1.setTeamHeading(teamsHeading);
		                match1.setMatchNumberVenue(matchNumberVenue);
		                match1.setBattingTeam(battingTeam);
		                match1.setBattingTeamScore(score);
		                match1.setBowlTeam(bowlTeam);
		                match1.setBowlTeamScore(bowlTeamScore);
		                match1.setLiveText(textLive);
		                match1.setMatchLink(matchLink);
		                match1.setTextComplete(textComplete);
		                match1.setMatchStatus();


		                matches.add(match1);

//		                update the match in database


		                updateMatch(match1);


		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        return matches;
		    }
	
	
	@Override
	public List<Map<String,String>> getPointTable() {
		return null;
	}

}
