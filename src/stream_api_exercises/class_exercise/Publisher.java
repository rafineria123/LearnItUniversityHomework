package stream_api_exercises.class_exercise;

public class Publisher {
    private int id;
    private String publisherName;

    public Publisher(int id, String publisherName) {
        this.id = id;
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                '}';
    }
}
