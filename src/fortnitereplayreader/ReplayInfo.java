package fortnitereplayreader;

public class ReplayInfo {

    private int magicNumber;
    private int fileVersion;
    private int lengthInMs;
    private int networkVersion;
    private int changeList;
    private String friendlyName;
    private boolean isLive;
    private int kills;
    private int position;
    private int totalPlayers;

    public ReplayInfo(int magicNumber, int fileVersion, int lengthInMs, int networkVersion, int changeList, String friendlyName, boolean isLive, int kills, int position, int totalPlayers) {
        this.magicNumber = magicNumber;
        this.fileVersion = fileVersion;
        this.lengthInMs = lengthInMs;
        this.networkVersion = networkVersion;
        this.changeList = changeList;
        this.friendlyName = friendlyName;
        this.isLive = isLive;
        this.kills = kills;
        this.position = position;
        this.totalPlayers = totalPlayers;
    }

    /**
     * Each replay file starts with the same number (0x1CA2E27F) called "magic Number". If this method doesn't returns a value equal to 0x1CA2E27F it's not a Unreal Engine Replay file.
     * @return Magic number of the replay
     */
    public int getMagicNumber() {
        return magicNumber;
    }

    /**
     * Returns the file-version of the replay. The default file-version for replays is 5.
     * @return File-version of the replay
     */
    public int getFileVersion() {
        return fileVersion;
    }

    /**
     * Returns the length of the replay in milliseconds
     * @return Length of the replay in ms
     */
    public int getLengthInMs() {
        return lengthInMs;
    }

    public int getNetworkVersion() {
        return networkVersion;
    }

    public int getChangeList() {
        return changeList;
    }

    /**
     * Returns the name of the Replay. For example "Unsaved Replay" (if the replay wasn't renamed and saved yet).
     * @return Friendly name of the replay
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    public boolean isLive() {
        return isLive;
    }

    /**
     * Returns the number of kills that you (the owner of the replay) made in the replay.
     * @return Number of kills you made in that game.
     */
    public int getKills() {
        return kills;
    }

    /**
     * Returns your (the owner of the replay) position at the end of the game. For example "1" if you won the game.
     * @return Your Position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the Number of Players which were participating at the game. (Max. 100)
     * @return Number of total players of that game.
     */
    public int getTotalPlayers() {
        return totalPlayers;
    }

}
