package com.crick.apis.repositers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crick.apis.entites.Match;

public interface MatchRepo extends JpaRepository <Match,Integer>{
	//match fetch karna chahta hoon
	
	Optional<Match> findByTeamHeading(String teamHeading);
}
