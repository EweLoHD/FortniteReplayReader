package fortnitereplayreader.model;

public class Elimination {

    private int time;
    private String victimId;
    private String killerId;
    private boolean isKnocked;

    public Elimination(int time, String victimId, String killerId, int gunType, boolean isKnocked) {
        this.time = time;
        this.victimId = victimId;
        this.killerId = killerId;
        this.isKnocked = isKnocked;
    }

    /**
     * Returns the the time of the elimination <b>in Milliseconds</b>.
     * @return Time of the elimination <b>in Milliseconds</b>.
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns the account id of the player who was killed.
     * @return AccountID of the victim.
     */
    public String getVictimId() {
        return victimId;
    }

    /**
     * Returns the account id of the player who killed the victimId.
     * @return AccountId of the killer.
     */
    public String getKillerId() {
        return killerId;
    }

    /**
     * Retruns true if the victim got only knocked and false if the player was eliminated and can't get revived.
     * @return if the player is only knocked or got eliminated.
     */
    public boolean isKnocked() { return isKnocked; }

}
