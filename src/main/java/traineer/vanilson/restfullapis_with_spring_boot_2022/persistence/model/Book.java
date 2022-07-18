package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_books")
@NoArgsConstructor
@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class Book extends RepresentationModel<Book> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer book_id;
    @Column(nullable = false,
            length = 512)
    private String author;
    @Column(name = "launch_date",
            length = 40)
    private LocalDateTime launchDate;
    @Column(nullable = false,
            precision = 65,
            scale = 2
    )
    private Double price;
    @Column(nullable = false,
            length = 200
    )
    private String title;

    public Book(Integer book_id, String author, LocalDateTime launchDate, Double price, String title) {
        this.book_id = book_id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Book(String author, LocalDateTime launchDate, Double price, String title) {
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(book_id, book.book_id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(launchDate, book.launchDate) &&
                Objects.equals(price, book.price) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), book_id, author, launchDate, price, title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", author='" + author + '\'' +
                ", launchDate=" + launchDate +
                ", price=" + price +
                ", title='" + title + '\'' +
                "} " + super.toString();
    }
}