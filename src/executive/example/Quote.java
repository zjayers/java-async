package executive.example;

public class Quote {
    private final String site;
    private final int quote;

    public Quote(String site, int quote) {
        this.site = site;
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "site='" + site + '\'' +
                ", quote=" + quote +
                '}';
    }
}
