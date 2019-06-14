package fortnitereplayreader.model;

public class TeamStats {

    private int position;
    private int totalPlayers;

    public TeamStats(int position, int totalPlayers) {
        this.position = position;
        this.totalPlayers = totalPlayers;
    }

    public int getPosition() {
        return position;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }
}
