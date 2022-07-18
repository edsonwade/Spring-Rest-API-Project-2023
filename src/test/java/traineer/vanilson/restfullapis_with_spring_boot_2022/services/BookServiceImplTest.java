package traineer.vanilson.restfullapis_with_spring_boot_2022.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.RequiredObjectIsNullException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.mocks.MockBook;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    private final BookRepository bookRepository = mock(BookRepository.class);
    private final BookServiceImpl bookService = new BookServiceImpl(bookRepository);

    LocalDateTime now = LocalDateTime.of(2022, 07, 18, 19, 34, 00);

    private static final String EXPECTED_MESSAGE = "It is not allowed to persist a null object!";


    MockBook book;

//    @InjectMocks
//    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        book = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllBooksExistentInDatabase() {

        var book1 = book.mockEntityList();
        when(bookRepository.findAll()).thenReturn(book1);

        var book = bookService.findAllBooks();

        assertNotNull(book);
        assertEquals(14, book.size());

        var bookOne = book.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getBook_id());
        assertNotNull(bookOne.getLinks());
        assertEquals("First Author Test1", bookOne.getAuthor());
        assertEquals(now, bookOne.getLaunchDate());
        assertEquals(92.00, bookOne.getPrice());
        assertEquals("First Title Test1", bookOne.getTitle());


        var bookFour = book.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookOne.getBook_id());
        assertNotNull(bookOne.getLinks());
        assertEquals("First Author Test1", bookOne.getAuthor());
        assertEquals(now, bookOne.getLaunchDate());
        assertEquals(92.00, bookOne.getPrice());
        assertEquals("First Title Test1", bookOne.getTitle());


    }

    @Test
    void findBookByIdAndReturnTheBook() {
        var book1 = book.mockEntity(1);
        book1.setBook_id(1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));

        var book = bookService.findById(1);

        assertNotNull(book);
        assertNotNull(book.getBook_id());
        assertNotNull(book.getLinks());
        assertEquals("First Author Test1", book.getAuthor());
        assertEquals(now, book.getLaunchDate());
        assertEquals(92.00, book.getPrice());
        assertEquals("First Title Test1", book.getTitle());


    }

    @Test
    void createNewBook() {
        var book2 = book.mockEntity(1);

        book2.setBook_id(1);

        when(bookRepository.save(book2)).thenReturn(book2);

        var book = bookService.createNewBook(book2);

        assertNotNull(book);
        assertNotNull(book.getBook_id());
        assertNotNull(book.getLinks());
        assertEquals("First Author Test1", book.getAuthor());
        assertEquals(now, book.getLaunchDate());
        assertEquals(92.00, book.getPrice());
        assertEquals("First Title Test1", book.getTitle());


    }

    @Test
    void createWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    bookService.createNewBook(null);
                });
        final String ACTUAL_MESSAGE = exception.getMessage();

        assertTrue(ACTUAL_MESSAGE.contains(EXPECTED_MESSAGE));


    }


    @Test
    void deleteBook() {
        var book1 = book.mockEntity(1);
        book1.setBook_id(1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));
        bookService.deleteBook(1);
    }
}