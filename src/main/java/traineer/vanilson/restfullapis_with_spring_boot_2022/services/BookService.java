package traineer.vanilson.restfullapis_with_spring_boot_2022.services;


import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book findById(Integer id);

    Book createNewBook(Book book);

    Book updateBook(Book book, Integer id);

    void deleteBook(Integer id);

}
