import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.sound.midi.Soundbank;

import Model.Chromosome;
import Model.Node;



public class GenAl {
	Node [][] orjMap;
	Node [][] map;
	int mapSize;
	 Random randomizer = new Random(); 
	private int moveLimit;
	private int popSize;
	private double mutationChance=0.5;
	
	public int getMoveLimit() {
		return moveLimit;
	}


	public void setMoveLimit(int moveLimit) {
		this.moveLimit = moveLimit;
	}


	ArrayList< Chromosome> population=new ArrayList<Chromosome>();
	
	
	
	public GenAl(Node [][] map,int size, int popSize, int moveLimit) {
		this.map=map;
		this.orjMap=map;
		this.mapSize=size;
		this.popSize = popSize;
		this.moveLimit = moveLimit;
		initializePop();
		
	}


	public void initializePop() {
		
		for(int i =0 ;i<popSize;i++) {
			int array []=new int[moveLimit];
			
			for(int j=0;j<moveLimit;j++) {
				array[j]=randomizer.nextInt(4)+1;
				
			}
			Chromosome temp = new Chromosome(array);
			population.add(temp);
		}
		
	}

	public int[] startSearch() {
		while(true) {
			fitness();
			System.out.println("Fitness");
			sortPopulation();
			//if(Double.compare(population.get(0).getScore(), 100000.0-moveLimit) >  0) {
				
			//}
//			PathFinding.drawPath(population.get(0).getGens());
			System.out.println("Sort");
			crossover();
			System.out.println("crossover");
			mutateAll();
			System.out.println("mutateAll");
			return population.get(0).getGens();

		}
	}
	
	
	/*
	 * 
	   
		1 = sol,
		2 = yukarı,
		3 = sağ,
		4 = aşağı
	 */
	public void fitness() {//13421234	
		System.out.println("NEW GENERETION");
		System.out.println(population.size());
		int finishX=mapSize-2;
		for (Chromosome c : population) {
			int currentX=1;
			int currentY=1;
			int array[]=c.getGens();
			for (int i =0 ;i<moveLimit;i++) {
				
				switch(array[i]) {
					case 1:
						currentY--;
						break;
					case 2:
						currentX--;
						break;
					case 3:
						currentY++;
						break;
					case 4:
						currentX++;
						break;
				}
				
				Node currentNode=map[currentX][currentY];
//				System.out.println("Type:"+currentNode.getType());
				if(currentNode.getType()==2) {
//					double score=  100.0/(calculateDistanceBetweenPoints( currentX, currentY)*(Math.sqrt((double) (i+1))));
					double score = 100 * (calculateDistanceBetweenPoints( 1, 1, currentX, currentY)/ calculateDistanceBetweenPoints( currentX, currentY, mapSize-1, mapSize-1));
//					double score = 100 * (currentX+currentY-2)/ (mapSize+mapSize-2-currentX-currentY);
					c.setScore(score);
//					System.out.println("X:"+currentX+ " Y:"+currentY+"Score :"+score);
					break;
					
				}
				else if (currentNode.getType()==1) {
					
					double score=100000-i;
					c.setScore(score);
					
//					System.out.println("X:"+currentX+ " Y:"+currentY+"Score :"+score);
					break;
				}
						
			}
			
		}
		
	}
	
	public double calculateDistanceBetweenPoints(int x1, int y1 , int x2, int y2) {    
		
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	
	

	public void  sortPopulation() {
		
//		for (int i = 0; i < population.size(); i++) {
//			System.out.println("NOT SORTED "+ population.get(i).getScore());
//		}
//		
		Collections.sort(population, new Comparator<Chromosome>() {
		    @Override
		    public int compare(Chromosome c1, Chromosome c2) {
		        return Double.compare(c1.getScore(), c2.getScore());
		    }
		});
		
		Collections.reverse(population);
		
		ArrayList<Chromosome> temp = new ArrayList<>();

		for (int i = 0; i < population.size()/2; i++) {
			temp.add(population.get(i));
		}
		
		population.clear();
		population.addAll(temp);
		System.out.println("AFTER SORT"+population.size());
		for (int i = 0; i < population.size()/2; i++) {
			System.out.println("SORTED "+ population.get(i).getScore());
		}
	}
	
	//val offSpring = mutate(Array.concat(firstParent.slice(0,crossOverPoint),secondParent.slice(crossOverPoint,secondParent.length)))
	
	public void crossover() {
		int count = population.size();
		for(int i=0;i<count;i++) {
			Chromosome firstParent =population.get(randomizer.nextInt(population.size()));
			Chromosome secondParent =population.get(randomizer.nextInt(population.size()));
			int crossOverPoint=randomizer.nextInt(moveLimit);
			
			int offSpring[]=new int[moveLimit];
			for(int j=0;j<crossOverPoint;j++) {
			
				offSpring[j]=firstParent.getGens()[j];	
				
			}
			
			for(int j=crossOverPoint;j<moveLimit;j++) {
						
						offSpring[j]=secondParent.getGens()[j];
						
						
					}
			
			Chromosome temp= new Chromosome(offSpring);
			population.add(temp);
			
			
		}
	}
	
	
	
	public void mutateAll() {
		for(Chromosome c : population) {
			c.setGens(mutate(c.getGens()));
		}
	}

	
	public int [] mutate(int chromosome[]) {
		if(randomizer.nextInt(2) == 1) {
			chromosome[randomizer.nextInt(chromosome.length)]=randomizer.nextInt(4)+1;
		}
		return chromosome;
		
	}
	


}
