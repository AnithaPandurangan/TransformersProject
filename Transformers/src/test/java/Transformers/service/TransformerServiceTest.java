package Transformers.service;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import Transformers.model.Transformer;
import Transformers.service.TransformerServiceImpl;
@SpringBootTest
public class TransformerServiceTest {

	    @Autowired
	    TranformerService tranService = new TransformerServiceImpl();
	    
	    @Test 
	    void testSpecialWin() {
	    	System.out.println("Optimus Prime vs Predaking");
	    	 List<Transformer> allTransformer = new ArrayList<Transformer>();
	    	  	allTransformer.add(new Transformer("Optimus Prime", "A", 8,9,2,6,7,5,6,10));
	    	  	allTransformer.add(new Transformer( "Predaking", "D", 6,6,7,9,7,2,9,7));
	    		System.out.println("Optimus Prime vs Predaking : All players destroyed! destroy method calling Repository, throws Nullpointer for test" + null);
	    		 assertThrows(NullPointerException.class,
	    		            ()->{
	    		            	tranService.findWinningTeamList(allTransformer);
	    		            });
	    }
	    
	    /*If any fighter is down 4 or more points of courage and 3 or more points of
	    strength compared to their opponent, the opponent automatically wins the
	    face-off regardless of overall rating (opponent has ran away) */
	    
	    @Test
	    void testStrenghAndCourage() {
	    	System.out.println("WinnerByStrenghAndCourage");
	    	  	Transformer player1 = new Transformer("Player1", "A", 8,9,2,6,7,5,6,10);
	    	  	Transformer player2 = new Transformer( "Player2", "D", 4,6,7,9,7,2,9,7);
	    	  	System.out.println("Player1.getStrength() " + player1.getStrength());
	    	  	System.out.println("Player1.getCourage() " + player1.getCourage());
	    	  	System.out.println("Player2.getStrength()" + player2.getStrength());
	    	  	System.out.println("player2.getCourage() " + player2.getCourage());
	    	  	System.out.println("player1.getOverAllRating() " +player1.getOverAllRating());
	    	  	System.out.println("player2.getOverAllRating() "+ player2.getOverAllRating());
	    	  	System.out.println("WinnerByStrenghAndCourage : player1 " + player1);
	    	  	assertEquals(player1, tranService.compareTransformers(player1, player2));
	    }
	    
	    /* if one of the fighters is 3 or more points of skill above their opponent,
	    they win the fight regardless of overall rating */
	    
	    @Test
	    void testSkill() {
	    		System.out.println("WinnerBySkill");
	    	  	Transformer player1 = new Transformer("Player1", "A", 8,9,2,4,7,5,6,6);
	    	  	Transformer player2 = new Transformer( "Player2", "D", 6,6,0,6,7,2,9,9);
	    	  	System.out.println("player1.getSkill() " + player1.getSkill());
	    	  	System.out.println("player1.getSkill() " + player1.getSkill());
	    	  	System.out.println("player1.getOverAllRating() " + player1.getOverAllRating());
	    	  	System.out.println("player2.getOverAllRating() "+ player2.getOverAllRating());
	    	  	System.out.println("WinnerBySkill : player2 " + player2);
	    	  	assertEquals(player2, tranService.compareTransformers(player1, player2));
	    }
	    
	    /*The winner is the Transformer with the highest overall rating*/
	    @Test
	    void testWinnerByOverallRating() {
	    	System.out.println("WinnerByOverallRating");
	    	Transformer player1 = new Transformer("Player1", "A", 8,9,2,4,7,5,6,6);
    	  	Transformer player2 = new Transformer( "Player2", "D", 6,6,7,6,7,2,9,9);
    	  	System.out.println(player1.getOverAllRating());
    	  	System.out.println(player2.getOverAllRating());
    	  	System.out.println("WinnerByOverallRating : player2 " + player2);
    	  	assertEquals(player2, tranService.compareTransformers(player1, player2));
	    }
	    
	    /* In the event of a tie, both Transformers are considered destroyed*/
	    @Test
	    void testTie() {
	    	System.out.println("Tie");
	    	Transformer player1 = new Transformer("Player1", "A", 6,6,7,6,7,2,9,9);
    	  	Transformer player2 = new Transformer( "Player2", "D", 6,6,7,6,7,2,9,9);
    	  	System.out.println(player1.getOverAllRating());
    	  	System.out.println(player2.getOverAllRating());
    	  	System.out.println("Tie: Considered Destroyed " + null);
    	  	assertEquals(null, tranService.compareTransformers(player1, player2));
	    }

	    
	    @Test 
	    void testOneSpecialTransformerWin() {
	    	System.out.println("OneSpecialTransformer");
	    	String expected ="1 battle  Winning team (Decepticons): Predaking Survivors from the losing team (Autobots): Player " ;
	    	 List<Transformer> allTransformer = new ArrayList<Transformer>();
	    	  	allTransformer.add(new Transformer("Test", "A", 8,9,2,6,7,5,6,10));
	    	  	allTransformer.add(new Transformer( "Predaking", "D", 6,6,7,9,7,2,9,7));
	    	  	allTransformer.add(new Transformer( "Player", "A", 6,6,7,9,7,2,9,7));
	    	  	System.out.println(allTransformer);
	    		String result = tranService.findWinningTeamList(allTransformer);
	    		System.out.println(expected);
	    		assertEquals(expected, result);
	    }

	    
		@Test
	    void testWinningTeam() {
			System.out.println("WinningTeam");
			String expected = "1 battle  Winning team (Decepticons): Soundwave Survivors from the losing team (Autobots): Hubcap ";
	    	List<Transformer> allTransformer = new ArrayList<Transformer>();
	    	 	allTransformer.add(new Transformer("Soundwave", "D", 8,9,2,6,7,5,6,10) );
	    	 	allTransformer.add(new Transformer( "Bluestreak", "A", 6,6,7,9,5,2,9,7));
	    	 	allTransformer.add(new Transformer( "Hubcap", "A", 4,4,4,4,4,4,4,4));
	    	  	String result = tranService.findWinningTeamList(allTransformer);
	    	  	System.out.println(result);
	    	  	assertEquals(expected, result);
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransformerServiceTest test = new TransformerServiceTest();
		test.testSpecialWin();
		test.testOneSpecialTransformerWin();
		test.testStrenghAndCourage();
		test.testSkill();
		test.testWinnerByOverallRating();
		test.testTie();
		test.testWinningTeam();
	}
	
}
