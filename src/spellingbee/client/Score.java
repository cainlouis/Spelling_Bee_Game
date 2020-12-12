package spellingbee.client;

public class Score implements Comparable<Score> {
	private String player;
	private int score;
	
	public Score(String player, int score){
		this.player = player;
		this.score = score;
	}
	
	public String getPlayer() {
		return this.player;
	}
	
	public int getScore() {
		return this.score;
	}
	
	 public int compareTo(Score newScore) {
	        int compareScore= ((Score)newScore).getScore();
	        return compareScore - this.score;
	    }
}
