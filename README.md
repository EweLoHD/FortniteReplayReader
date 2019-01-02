# FortniteReplayReader (WIP)
A libary to read Fortnite .replay files. 

Inspired by https://github.com/fredimachado/FortniteReplayReader by Fredi Machado.

Made with the help of his video https://youtu.be/m2Twu9k5VQQ.
Thx!

## Installation
You can download the latest build in the [Releases](https://github.com/EweLoHD/FortniteReplayReader/releases) and add it to your project.

## Usage
Some basic examples to show the usage of FortniteReplayReader
### Get some information about the replay
```java
FortniteReplayReader replay = new FortniteReplayReader(new File("UnsavedReplay-2018.12.23-15.13.28.replay"));

System.out.println("Replay Name: " + replay.getReplayInfo().getFriendlyName());
System.out.println("Your Position: " + replay.getReplayInfo().getPosition() + " / " + replay.getReplayInfo().getTotalPlayers());
```
Result:
```
Replay Name: Ungespeicherte Wiederholung
Your Position: 5 / 99
```
### Get all Eliminations 
```java
FortniteReplayReader replay = new FortniteReplayReader(new File("UnsavedReplay-2018.12.23-15.13.28.replay"));

for(Elimination elimination : replay.getEliminations()) {
  System.out.println(elimination.getKiller() + " killed " + elimination.getVictim());
}
```
Result:
```
hypeAlex_ killed MEGA DOBRY 123
xd CatsPower killed Domix121
The_Igel killed paciek1000
Unstoppable_Frkn killed PartyBG
[...]
```

## To-Do
- [ ] GunTypes
- [ ] Knocked?
- [ ] and much more ...
