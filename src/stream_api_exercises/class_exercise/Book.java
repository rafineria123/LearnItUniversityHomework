package stream_api_exercises.class_exercise;

import java.math.BigDecimal;
import java.util.Arrays;

public class Book {

    private int id;
    private String name;
    private Author[] authors;
    private Publisher publisher;
    private int publishingYear;
    private int amountOfPages;
    private BigDecimal price;
    private CoverType coverType;

    public Book(int id, String name, Author[] authors, Publisher publisher, int publishingYear, int amountOfPages, BigDecimal price, CoverType coverType) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.publishingYear = publishingYear;
        this.amountOfPages = amountOfPages;
        this.price = price;
        this.coverType = coverType;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", publisher=" + publisher +
                ", publishingYear=" + publishingYear +
                ", amountOfPages=" + amountOfPages +
                ", price=" + price +
                ", coverType=" + coverType +
                '}';
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
