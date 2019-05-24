package fortnitereplayreader.model;

public class MatchStats {

    private float accuracy;private int assists;
    private int eliminations;
    private int weaponDamage;
    private int otherDamage;
    private int revives;
    private int damageTaken;
    private int damageToStructures;
    private int materialsGathered;
    private int materialsUsed;
    private int totalTraveled;


    public MatchStats(float accuracy, int assists, int eliminations, int weaponDamage, int otherDamage, int revives, int damageTaken, int damageToStructures, int materialsGathered, int materialsUsed, int totalTraveled) {
        this.accuracy = accuracy;
        this.assists = assists;
        this.eliminations = eliminations;
        this.weaponDamage = weaponDamage;
        this.otherDamage = otherDamage;
        this.revives = revives;
        this.damageTaken = damageTaken;
        this.damageToStructures = damageToStructures;
        this.materialsGathered = materialsGathered;
        this.materialsUsed = materialsUsed;
        this.totalTraveled = totalTraveled;
    }


    public float getAccuracy() {
        return accuracy;
    }

    public int getAssists() {
        return assists;
    }

    public int getEliminations() {
        return eliminations;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getOtherDamage() {
        return otherDamage;
    }

    public int getRevives() {
        return revives;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public int getDamageToStructures() {
        return damageToStructures;
    }

    public int getMaterialsGathered() {
        return materialsGathered;
    }

    public int getMaterialsUsed() {
        return materialsUsed;
    }

    public int getTotalTraveled() {
        return totalTraveled;
    }
}
