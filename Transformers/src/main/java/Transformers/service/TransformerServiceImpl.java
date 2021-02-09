package Transformers.service;

import Transformers.model.*;
import Transformers.repository.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.OrderBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TransformerServiceImpl implements TranformerService {
	@Autowired
	private TransformerRepository repository;
	

	@Override
	public List<Transformer> getAllTransformers() {
		return repository.findAll();
	}
	
	@Override
	public void addTransformer(Transformer transformer) {
		repository.save(transformer);
	}
	
	@Override
	public void deleteAllTransformer() {
		// TODO Auto-generated method stub
		repository.deleteAll();
	}
	
	@Override
	public Transformer getById(Long id) {
		// TODO Auto-generated method stub
		return ((repository.findById(id).isPresent()) ? repository.findById(id).get() : null);
	}
	
	@Override
	public void deleteTransformer(Long id) {
		// TODO Auto-generated method stub
		if(getById(id) !=null)
			repository.deleteById(id);
	}
	
	@Override
	public List<Transformer> getAllTransformersbyTeam(List<Transformer> allTransformer, String team){
		if(allTransformer != null) {
			List<Transformer> TransformerList = allTransformer.stream().filter(trans -> trans.getTeam().equals(team))
					.sorted(Comparator.comparingInt(Transformer::getRank).reversed())
					.collect(Collectors.toList());
	
			return (List<Transformer>) TransformerList;
		} 
		return null;
	}
	
	boolean isSpecialTransformer(Transformer transformer) {
		String[] specialName = {"OPTIMUS PRIME","PREDAKING"};
		//System.out.println(Arrays.stream(specialName).anyMatch(transformer.getName().toUpperCase() :: equals));
		return  Arrays.stream(specialName).anyMatch(transformer.getName().toUpperCase() :: equals);
	}
	
	int getOverallRating(Transformer transformer) {
		return transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed() + transformer.getEndurance() + transformer.getFirepower() ;
	}
	
	@Override
	public Transformer compareTransformers(Transformer autobots, Transformer decepticons) {

		Transformer winningTransformer = ((autobots.getCourage() - decepticons.getCourage() >= 4) && 
											(autobots.getStrength() - decepticons.getStrength() >=3 )) ?  autobots :
										 ((decepticons.getCourage() - autobots.getCourage() >= 4) && 
										    (decepticons.getStrength() - autobots.getStrength() >= 3)) ? 	decepticons :
										 ((autobots.getSkill() - decepticons.getSkill()) >=3) ? autobots : 
											 ((decepticons.getSkill() - autobots.getSkill()) >=3)  ? decepticons :   null;
		
		if(winningTransformer == null) {
			return winningTransformer = (getOverallRating(autobots) == getOverallRating(decepticons)) ? null : (getOverallRating(autobots) > getOverallRating(decepticons)) ? autobots : decepticons;
		} else return winningTransformer;
	}
	
	public String getResultString(List<Transformer> winningTransformers,List<Transformer> skippedTransformers, int battle ) {
		int size, aSize, dSize = 0;
 		List<Transformer> autobots = (getAllTransformersbyTeam(winningTransformers, "A"));
 		List<Transformer> decepticons = getAllTransformersbyTeam(winningTransformers, "D");
 		aSize = (autobots != null) ? autobots.size() : 0;
 		dSize= (decepticons != null) ? decepticons.size() : 0;
		String result =  battle + " battle  Winning team";
 		result += (aSize > dSize) ? " (Autobots): "+ autobots.stream().map(
 				autobot->autobot.getName() + " ").collect(Collectors.joining(", ")) : (battle > 0) ?
 				" (Decepticons): "+ decepticons.stream().map(
 						decepticon->decepticon.getName() + " ").collect(Collectors.joining(",")) : " ";
 		String survivors = "No Survivors";
 		if(skippedTransformers != null && !skippedTransformers.isEmpty()) {
 		survivors = (aSize < dSize) ? 
 					skippedTransformers.stream().map(a->a.getName() + " ").collect(Collectors.joining(", "))
 				: 	skippedTransformers.stream().map(d->d.getName() + " ").collect(Collectors.joining(", "));
	 }
 		result +=  "Survivors from the losing team (" ;
 		result +=  (aSize < dSize) ?  "Autobots): " + survivors : (battle > 0) ? "Decepticons): " + survivors : "";

		return result ;
	}
	
	@Override
	public String findWinningTeamList(List<Transformer> allTransformerList) {
		
		String result =" ";
		int battle =0;
		if( allTransformerList == null )
			return "Player is not exist";
		List<Transformer> AutobotsTransformers = (ArrayList<Transformer>) getAllTransformersbyTeam(allTransformerList, "A");
 		List<Transformer> DecepticonsTransformers = (ArrayList<Transformer>)getAllTransformersbyTeam(allTransformerList, "D");
		
 		List<Transformer> skippedTransformers = new ArrayList<Transformer>();
 		List<Transformer> winningTransformers = new ArrayList<Transformer>();
 		int size, aSize, dSize = 0;
 		aSize = (AutobotsTransformers != null) ? AutobotsTransformers.size() : 0;
 		dSize= (DecepticonsTransformers != null) ? DecepticonsTransformers.size() : 0;
 		
 		size = aSize < dSize ? aSize : dSize;
 		if(AutobotsTransformers.size() < dSize) {
 			for(int i=size; i < dSize ;i++)
 				skippedTransformers.add(DecepticonsTransformers.get(i));
 		} else {
 			for(int i=size; i < aSize ;i++)
 				skippedTransformers.add(AutobotsTransformers.get(i));
 		}
 		
 		for(int i=0; i<size; i++) {
 			 battle++;
 			 if(isSpecialTransformer(AutobotsTransformers.get(i)) && isSpecialTransformer(DecepticonsTransformers.get(i))) { 
 				deleteAllTransformer();
 				return "The Game Ends! All players are destroyed";
 			 }
 			 else if(isSpecialTransformer(AutobotsTransformers.get(i))) winningTransformers.add(AutobotsTransformers.get(i));
 			 else if(isSpecialTransformer(DecepticonsTransformers.get(i))) winningTransformers.add(DecepticonsTransformers.get(i));
 			 else {
 				if((compareTransformers(AutobotsTransformers.get(i), DecepticonsTransformers.get(i))) == null) {
 					repository.deleteById(AutobotsTransformers.get(i).getId());
 					repository.deleteById(DecepticonsTransformers.get(i).getId());
 				} else
 				 winningTransformers.add(compareTransformers(AutobotsTransformers.get(i), DecepticonsTransformers.get(i)));
 			 }
 		}
 		
 		
 		if(battle > 0)
 			return  getResultString(winningTransformers,skippedTransformers, battle ) ;
 		else 
 			return  "0 battle, Not enough players or the player does not exist!";
	}
	
	
	
	@Override
	public String findWinningTeam(long[] tansformerIdList) {
		List<Transformer> allTransformerList = new ArrayList<Transformer>();
		if(tansformerIdList != null && tansformerIdList.length >= 1) {
			for(long id : tansformerIdList) {
				if(getById(id) != null)
					allTransformerList.add(getById(id));
			}
			if(((allTransformerList != null ) && !allTransformerList.isEmpty()))
				return findWinningTeamList(allTransformerList);
		} 
		
		return (((allTransformerList != null ) && allTransformerList.isEmpty()) ? "The player does not exist!" :"0 battle, Not enough player or the player does not exist! ");
	}
}
