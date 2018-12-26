package fortnitereplayreader;

public class Elimination {

    private int time;
    private String victim;
    private String killer;

    public Elimination(int time, String victim, String killer) {
        this.time = time;
        this.victim = victim;
        this.killer = killer;
    }

    /**
     * Returns the the time of the elimination <b>in Milliseconds</b>.
     * @return Time of the elimination <b>in Milliseconds</b>.
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns the name of the player who was killed.
     * @return Name of the victim.
     */
    public String getVictim() {
        return victim;
    }

    /**
     * Returns the name of the player who killed the victim.
     * @return Name of the Killer.
     */
    public String getKiller() {
        return killer;
    }

}
