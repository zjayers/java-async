package streams.model;

public class Movie {
    private final String title;
    private final int likes;

    public Movie(String name, int likes) {
        this.title = name;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", likes=" + likes +
                '}';
    }
}
