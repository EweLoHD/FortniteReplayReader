package fortnitereplayreader.examples;

import fortnitereplayreader.FortniteReplayReader;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        FortniteReplayReader replayReader = new FortniteReplayReader(new File("C:/Users/Louis/AppData/Local/FortniteGame/Saved/Demos/UnsavedReplay-2018.12.23-15.13.28.replay"));

        System.out.println("Replay Name: " + replayReader.getReplayInfo().getFriendlyName());
        System.out.println("Your Position: " + replayReader.getReplayInfo().getPosition() + " / " + replayReader.getReplayInfo().getTotalPlayers());

    }

}
