package pl.amilosh.java;

public sealed interface Penguin extends Bird {
}

final class ImperialPenguin implements Penguin {
    public String shh() {
        return "shh";
    }

    @Override
    public String getName() {
        return "ImperialPenguin";
    }
}
