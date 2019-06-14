package fortnitereplayreader;

import fortnitereplayreader.model.*;
import fortnitereplayreader.type.ChunkType;
import fortnitereplayreader.type.EventType;
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

    private Meta meta;
    private MatchStats matchStats;
    private TeamStats teamStats;
    private ReplayData replayData;

    private List<Elimination> eliminations = new ArrayList<Elimination>();

    /**
     * Represents a Fortnite .replay file. Contains all methods to read the replay file.
     * @param replayFile Fortnite .replay file to read.
     * @throws Exception
     */
    public FortniteReplayReader(File replayFile) throws Exception {
        this.replayFile = replayFile;
        this.reader = new BinaryReader(new FileInputStream(replayFile));

        parseMeta();

        parseChunks();
    }

    protected void parseMeta() throws Exception {
        int magicNumber = (int) reader.readUInt32();
        int fileVersion = reader.readInt32();

        if(magicNumber != 0x1CA2E27F || fileVersion != 5) {
            throw new Exception("This is a invalid Fortnite replay file!");
        }

        int lengthInMs = reader.readInt32();
        int networkVersion = reader.readInt32();
        int changeList = reader.readInt32();
        String friendlyName = reader.readFString();
        boolean isLive = reader.readInt32() != 0;

        reader.skip(12);

        this.meta = new Meta(magicNumber, fileVersion, lengthInMs, networkVersion, changeList, friendlyName, isLive);
    }

    protected void parseChunks() throws Exception {
        while(0 < reader.available()) {
            int chunkType = (int) reader.readInt32();
            int sizeInBytes = reader.readInt32();
            long offset = reader.getPosition();

            if (chunkType == ChunkType.EVENT) {
                parseEvent();
            } else if (chunkType == ChunkType.CHECKPOINT) {
                parseCheckpoint();
            } else if (chunkType == ChunkType.REPLAYDATA) {
                parseReplayData();
            } else if (chunkType == ChunkType.HEADER) {
                parseHeader();
            }

            reader.skip(sizeInBytes - (reader.getPosition() - offset));
        }
    }

    protected void parseCheckpoint() throws Exception {
        String checkpointId = reader.readFString();
        String checkpoint = reader.readFString();
    }

    protected void parseEvent() throws Exception {
        String id = reader.readFString();
        String group = reader.readFString();
        String metadata = reader.readFString();
        int time1 = reader.readInt32();
        int time2 = reader.readInt32();
        int sizeInBytes = reader.readInt32();

        if (group.equals(EventType.PLAYER_ELIMINATION)) {
            parseElimination(time1);
        } else if (metadata.equals(EventType.MATCH_STATS)) {
            parseMatchStats();
        } else if (metadata.equals(EventType.TEAM_STATS)) {
            parseTeamStats();
        }
    }

    protected void parseElimination(int time1) throws Exception {
        reader.skip(87);

        String victimId = stringFromBytes(reader.readBytes(16));
        reader.skip(2);
        String killerId = stringFromBytes(reader.readBytes(16));
        int gunType = reader.readBytes(1)[0];
        int knocked = reader.readInt32();
        boolean isKnocked = knocked == 1;
        //System.out.println(gunType + " - " + knocked);

        eliminations.add(new Elimination(time1, victimId, killerId, gunType, isKnocked));
    }

    protected void parseMatchStats() throws Exception {
        reader.skip(4);

        float accuracy = reader.readSingle();
        int assists = reader.readInt32();
        int eliminations = reader.readInt32();
        int weaponDamage = reader.readInt32();
        int otherDamage = reader.readInt32();
        int revives = reader.readInt32();
        int damageTaken = reader.readInt32();
        int damageToStructures = reader.readInt32();
        int materialsGathered = reader.readInt32();
        int materialsUsed = reader.readInt32();
        int totalTraveled = reader.readInt32();

        matchStats = new MatchStats(accuracy, assists, eliminations, weaponDamage, otherDamage, revives, damageTaken, damageToStructures, materialsGathered, materialsUsed, totalTraveled);
    }

    protected void parseTeamStats() throws Exception {
        reader.skip(4);

        int position = reader.readInt32();
        int totalPlayers = reader.readInt32();

        teamStats = new TeamStats(position, totalPlayers);
    }

    protected void parseReplayData() throws Exception {
        int start = reader.readInt32();
        int end = reader.readInt32();
        int length = reader.readInt32();
        int unknown = reader.readInt32();
        length = reader.readInt32();

        replayData = new ReplayData(start, end, length, unknown);
    }

    public void parseHeader() throws Exception {
        reader.skip(4);

        int headerVersion = reader.readInt32();
        int serverSideVersion = reader.readInt32();
        int season = reader.readInt32();
        int unknown1 = reader.readInt32();

        reader.skip(16);

        int unknown2 = reader.readInt16();
        int replayVersion = reader.readInt32();
        int fortniteVersion = reader.readInt32();
        String release = reader.readFString();
        /*String map;

        if (reader.readBoolean()) {
            map = reader.readString();
        }

        int unknown3 = reader.readInt32();
        int unknown4 = reader.readInt32();
        String subGame;

        if(reader.readBoolean()) {
            subGame = reader.readFString();
        }*/
    }


    public static String stringFromBytes(byte bytes[]) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Returns a list with all eliminations. Each one contains information like who killed who with which gun and more.
     * @see Elimination
     * @return List with all Eliminations
     */
    public List<Elimination> getEliminations() {
        return eliminations;
    }

    public Meta getMeta() {
        return meta;
    }

    public MatchStats getMatchStats() {
        return matchStats;
    }

    public TeamStats getTeamStats() {
        return teamStats;
    }

    public ReplayData getReplayData() {
        return replayData;
    }

    public File getReplayFile() {
        return replayFile;
    }

    public BinaryReader getReader() {
        return reader;
    }
}
