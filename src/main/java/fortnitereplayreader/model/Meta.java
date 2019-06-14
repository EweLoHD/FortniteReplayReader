package fortnitereplayreader.model;

public class Meta {

    private int magicNumber;
    private int fileVersion;
    private int lengthInMs;
    private int networkVersion;
    private int changeList;
    private String friendlyName;
    private boolean isLive;


    public Meta(int magicNumber, int fileVersion, int lengthInMs, int networkVersion, int changeList, String friendlyName, boolean isLive) {
        this.magicNumber = magicNumber;
        this.fileVersion = fileVersion;
        this.lengthInMs = lengthInMs;
        this.networkVersion = networkVersion;
        this.changeList = changeList;
        this.friendlyName = friendlyName;
        this.isLive = isLive;
    }


    public int getMagicNumber() {
        return magicNumber;
    }

    public int getFileVersion() {
        return fileVersion;
    }

    public int getLengthInMs() {
        return lengthInMs;
    }

    public int getNetworkVersion() {
        return networkVersion;
    }

    public int getChangeList() {
        return changeList;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public boolean isLive() {
        return isLive;
    }
}
