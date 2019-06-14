package fortnitereplayreader.model;

public class ReplayData {

    private int start;
    private int end;
    private int length;
    private int unknown;

    public ReplayData(int start, int end, int length, int unknown) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.unknown = unknown;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

    public int getUnknown() {
        return unknown;
    }

}
