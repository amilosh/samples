package pl.amilosh.java;

public sealed interface Bird permits Duck, Penguin {

    static Bird getBird() {
        return new Mallard();
    }

    static Bird getNullBird() {
        return null;
    }

    String getName();
}

sealed interface Duck extends Bird {

}

final class Mallard implements Duck {
    public String quack() {
        return "quack";
    }

    @Override
    public String getName() {
        return "Mallard";
    }
}

non-sealed class Drake implements Duck {

    @Override
    public String getName() {
        return "Drake";
    }
}
