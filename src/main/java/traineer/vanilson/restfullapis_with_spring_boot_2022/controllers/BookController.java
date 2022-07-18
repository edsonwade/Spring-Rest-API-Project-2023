package traineer.vanilson.restfullapis_with_spring_boot_2022.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import traineer.vanilson.restfullapis_with_spring_boot_2022.exceptions.ObjectNotFoundException;
import traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model.Book;
import traineer.vanilson.restfullapis_with_spring_boot_2022.services.BookService;
import traineer.vanilson.restfullapis_with_spring_boot_2022.utils.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {

    private final BookService bookService;


    @GetMapping(value = "/book",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })
    @Operation(summary = "Find All Book", description = "Find All Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Book.class))
                            )}),

                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<List<Book>> listAllBooks() throws IllegalAccessException {
        return ResponseEntity.ok(Optional.of(bookService.findAllBooks())
                .orElseThrow(IllegalAccessException::new));
    }

    @GetMapping(value = "/book/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            }
    )
    @Operation(summary = "Find Book By Id", description = "Find  Book By Id",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = Book.class), mediaType = "application/json")),
                    @ApiResponse(description = "No Content"
                            , responseCode = "204"
                            , content = @Content),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Book> listAllBooksById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(bookService.findById(id));


    }

    @PostMapping(value = "/book/createNewBook",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML}
    )

    @Operation(summary = "Add  a new  Book by passing" +
            "in Json ,XML, OR YAML representation of Book ",
            description = "Add  a new  Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "201"
                            , content = @Content(schema = @Schema(implementation = Book.class), mediaType = "application/json")),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Book> createNewBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createNewBook(book));
    }

    @PutMapping(value = "/book/update/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update   a Book by passing" +
            "in Json ,XML, OR YAML representation of Book ",
            description = "Updated",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = Book.class), mediaType = "application/json")),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Book> updateBook(
            @PathVariable(value = "id") Integer id,
            @RequestBody Book book) {

        if (!book.getBook_id().equals(id)) {
            throw new ObjectNotFoundException("Book With Id " +
                    id + " was not found in list");
        }
        return ResponseEntity.ok().body(bookService.updateBook(book, id));

    }

    @DeleteMapping(value = "/book/delete/{id}")
    @Operation(summary = "Delete a Book by Id",
            description = "Delete",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "No Content"
                            , responseCode = "204"
                            , content = @Content),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") int id) {
        Book book = bookService.findById(id);
        if (book.getBook_id().equals(id)) {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body(" Book id deleted with successfully ");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book  with id " + id + " was  not found");
    }


}