package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import traineer.vanilson.restfullapis_with_spring_boot_2022.controllers.BookController;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.ObjectNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.RequiredObjectIsNullException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Book;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);


    @Override
    public List<Book> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        books
                .forEach(p -> p.add(linkTo(methodOn(BookController.class)
                        .listAllBooksById(p.getBook_id()))
                        .withSelfRel()));
        logger.info("List all Books {} ", books);
        return books;
    }


    @Override
    public Book findById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Book With Id " + id + " was not found in list"));

        book.add(linkTo(methodOn(BookController.class).listAllBooksById(id)).withSelfRel());
        logger.info(" found the book with id {}", id);
        return book;

    }

    @Override
    public Book createNewBook(Book book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info(" new Book created with success {}", book);
        book.add(linkTo(methodOn(BookController.class)
                .listAllBooksById(book.getBook_id()))
                .withSelfRel());

        return bookRepository.save(book);


    }

    @Override
    public Book updateBook(Book book, Integer id) {
        if (book == null) throw new RequiredObjectIsNullException();

        if (!bookRepository.existsById(id)) {
            logger.error("The Book with id {} ", id + " doesn't exist");
            throw new ObjectNotFoundException("Book With Id " + id + " was not found in list");

        }

        Book saved = bookRepository.save(
                new Book(
                        id,
                        book.getAuthor(),
                        book.getLaunchDate(),
                        book.getPrice(),
                        book.getTitle()
                ));
        logger.info("Book Updated With Success");
        return saved;


    }

    @Override
    public void deleteBook(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
        logger.info("Book with Id {}", id + " deleted with success");
    }
}