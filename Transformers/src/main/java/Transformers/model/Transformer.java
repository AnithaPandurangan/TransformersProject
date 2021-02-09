package Transformers.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


	@Entity
	public class Transformer {

		//Optimus Prime or Predaking wins his fight automatically

		private @Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long id;
		private String name;
		private String team;
		private int strength;
		private int intelligence;
		private int speed;
		private int endurance;
		private int rank;
		private int courage;
		private int firepower;
		private int skill;
		//private static int overAllRating;
	  
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getCourage() {
		return courage;
	}

	public void setCourage(int courage) {
		this.courage = courage;
	}

	public int getFirepower() {
		return firepower;
	}

	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}


	public int getOverAllRating() {
		return strength + intelligence + speed + endurance + firepower;
	}

/*	public void setOverAllRating(int overAllRating) {
		this.overAllRating = strength + intelligence + speed + endurance + firepower;
	}*/

	  
	  public Transformer() {}

	  public Transformer(String name, String team, int strength, int intelligence, int speed, int endurance, int rank, int courage, int firepower, int skill ) {
	    this.name = name;
	    this.team = team;
	    this.strength = strength;
	    this.intelligence = intelligence;
	    this.speed = speed;
	    this.endurance = endurance;
	    this.rank = rank;
	    this.courage = courage;
	    this.firepower = firepower;
	    this.skill = skill;
	    //this.overAllRating = strength + intelligence + speed + endurance + firepower;
	  }



/*	  @Override
	public boolean equals(Object obj) {
		    if (this == obj)
		      return true;
		    if (!(obj instanceof Transformer))
		      return false;
		    Transformer transformer = (Transformer) obj;
		    return Objects.equals(this.id, transformer.id) && Objects.equals(this.name, transformer.name)
		        && Objects.equals(this.team, transformer.team);
	}

	  @Override
	public int hashCode() {
		  return Objects.hash(this.id, this.name, this.team);
	} */

		@Override
		public String toString() {
			return "Transformer [id=" + id + ", name=" + name + ", team=" + team + ", strength=" + strength
					+ ", intelligence=" + intelligence + ", speed=" + speed + ", endurance=" + endurance + ", rank="
					+ rank + ", courage=" + courage + ", firepower=" + firepower + ", skill=" + skill + "]";
		}
}
