package Model;

public class Chromosome {
int gens []=new int [30];
double score;





public Chromosome(int[] gens) {
	super();
	this.gens = gens;
	this.score = 0;
}


public int[] getGens() {
	return gens;
}
public void setGens(int[] gens) {
	this.gens = gens;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}

}
