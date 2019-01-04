package fortnitereplayreader.test;

import fortnitereplayreader.Elimination;
import fortnitereplayreader.FortniteReplayReader;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        FortniteReplayReader replay = new FortniteReplayReader(new File("C:\\Users\\EweLo\\AppData\\Local\\FortniteGame\\Saved\\Demos\\UnsavedReplay-2019.01.02-21.05.27.replay"));

        for(Elimination elimination : replay.getEliminations()) {
            System.out.println(elimination.getKiller() + " killed " + elimination.getVictim());
        }

        System.out.println("Replay Name: " + replay.getReplayInfo().getFriendlyName());
        System.out.println("Your Position: " + replay.getReplayInfo().getPosition() + " / " + replay.getReplayInfo().getTotalPlayers());
    }

}
