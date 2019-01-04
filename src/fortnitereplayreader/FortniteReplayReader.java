package fortnitereplayreader;

import fortnitereplayreader.util.BinaryReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is inspired an was made with the help of https://github.com/fredimachado/FortniteReplayReader by Fredi Machado.
 * @author EweLoHD
 */
public class FortniteReplayReader {

    private File replayFile;
    private BinaryReader reader;

    private int magicNumber;
    private int fileVersion;
    private int lengthInMs;
    private int networkVersion;
    private int changeList;
    private String friendlyName;
    private boolean isLive;
    private List<Elimination> eliminations = new ArrayList<Elimination>();
    private int kills;
    private int position;
    private int totalPlayers;

    /**
     * Represents a Fortnite .replay file. Contains all methods to read the replay file.
     * @param replayFile Fortnite .replay file to read.
     * @throws Exception
     */
    public FortniteReplayReader(File replayFile) throws Exception {
        this.replayFile = replayFile;
        this.reader = new BinaryReader(new FileInputStream(replayFile));

        magicNumber = (int) reader.readUInt32();
        fileVersion = reader.readInt32();

        if(magicNumber != 0x1CA2E27F || fileVersion != 5) {
            throw new Exception("This is a invalid Fortnite replay file!");
        }

        lengthInMs = reader.readInt32();
        networkVersion = reader.readInt32();
        changeList = reader.readInt32();
        friendlyName = reader.readFString();
        isLive = reader.readInt32() != 0;

        reader.skip(12);

        while(0 < reader.available()) {
            int chunkType = (int) reader.readInt32();
            int sizeInBytes = reader.readInt32();
            long offset = reader.getPosition();

            if (chunkType == 3) {
                String id = reader.readFString();
                String group = reader.readFString();
                String metadata = reader.readFString();
                int time = reader.readInt32();
                int time2 = reader.readInt32();
                int eventSize = reader.readInt32();

                if (group.equals("playerElim")) {
                    reader.skip(45);

                    String victim = reader.readFString();
                    String killer = reader.readFString();
                    int gunType = reader.readInt32();
                    boolean isKnocked = reader.readInt32() == 1;

                    eliminations.add(new Elimination(time, victim, killer, isKnocked));
                }

                if (metadata.equals("AthenaMatchStats")) {
                    reader.skip(12);

                    kills = reader.readInt32();
                }

                if (metadata.equals("AthenaMatchTeamStats")) {
                    reader.skip(4);

                    position = reader.readInt32();
                    totalPlayers = reader.readInt32();
                }
            }
            reader.skip(sizeInBytes - (reader.getPosition() - offset));
        }
    }

    /**
     * Returns the ReplayInfo of the replay file which contains information about the replay, like the time, kills, position, ...
     * @see ReplayInfo
     * @return All information about the replay
     */
    public ReplayInfo getReplayInfo() {
        return new ReplayInfo(magicNumber, fileVersion, lengthInMs, networkVersion, changeList, friendlyName, isLive, kills, position, totalPlayers);
    }

    /**
     * Returns a list with all eliminations. Each one contains information like who killed who with which gun and more.
     * @see Elimination
     * @return List with all Eliminations
     */
    public List<Elimination> getEliminations() {
        return eliminations;
    }

    public File getReplayFile() {
        return replayFile;
    }

    public BinaryReader getReader() {
        return reader;
    }
}
