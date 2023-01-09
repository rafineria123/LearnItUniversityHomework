package stream_api_exercises.class_exercise;

import java.util.Arrays;

public class BookService {
    public Book[] filterBooksByAuthor(Author author, Book[] books) {
        return (Book[])Arrays.stream(books).filter(b -> equals(author)).toArray();
    }

    public Book[] filterBooksByPublisher(Publisher publisher, Book[] books) {
        return (Book[])Arrays.stream(books).filter(b -> equals(publisher)).toArray();
    }

    // methods keeps books with publishing year inclusively.
    public Book[] filterBooksAfterSpecifiedYear(int yearFromInclusively, Book[] books) {
        return (Book[])Arrays.stream(books).filter(b -> b.getPublishingYear()>=yearFromInclusively).toArray();
    }

}
