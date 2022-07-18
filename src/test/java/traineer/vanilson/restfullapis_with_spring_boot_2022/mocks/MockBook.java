package traineer.vanilson.restfullapis_with_spring_boot_2022.mocks;

import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    List<Book> people = new ArrayList<>();
    LocalDateTime now = LocalDateTime.of(2022, 07, 18, 19, 34, 00);

    public Book mockEntity() {
        return mockEntity(0);
    }

    public List<Book> mockEntityList() {

        for (int i = 0; i < 14; i++) {
            people.add(mockEntity(i));
        }
        return people;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setBook_id(number);
        book.setAuthor("First Author Test" + number);
        book.setLaunchDate(now);
        book.setPrice(92.00);
        book.setTitle("First Title Test" + number);
        return book;
    }
}
