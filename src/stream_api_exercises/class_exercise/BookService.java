package stream_api_exercises.class_exercise;

import java.util.Arrays;
import java.util.List;

public class BookService {
    public Book[] filterBooksByAuthor(Author author, Book[] books) {
        List<Book> filteredBookList = Arrays.stream(books).filter(b -> b.getAuthors()[0].equals(author)).toList();
        return filteredBookList.toArray(new Book[filteredBookList.size()]);
    }

    public Book[] filterBooksByPublisher(Publisher publisher, Book[] books) {
        List<Book> filteredBookList = Arrays.stream(books).filter(b -> b.getPublisher().equals(publisher)).toList();
        return filteredBookList.toArray(new Book[filteredBookList.size()]);
    }

    // methods keeps books with publishing year inclusively.
    public Book[] filterBooksAfterSpecifiedYear(int yearFromInclusively, Book[] books) {
        List<Book> filteredBookList = Arrays.stream(books).filter(b -> b.getPublishingYear()>=yearFromInclusively).toList();
        return filteredBookList.toArray(new Book[filteredBookList.size()]);
    }

}
