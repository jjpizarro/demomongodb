package co.edu.usa.demomongdb.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.demomongdb.documents.Book;
import co.edu.usa.demomongdb.exceptions.ResourceNotFoundException;
import co.edu.usa.demomongdb.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired private BookService bookService;
    /*@GetMapping("/books/")
    public ResponseEntity<List<Book>> getAllBooks(){
        try {
            List<Book> books = bookService.getAllBooks();
            if(books.isEmpty()){
                return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch(Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
         
    }*/

    @GetMapping("/books/")
    public List<Book> getAll(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Integer id){
        return bookService.getBookById(id);
    }
    @PostMapping("/books/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }
    /*@PostMapping("/books/")
    public ResponseEntity<Book> createB(@RequestBody Book book){
        try {
            Book newBook = bookService.create(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED) ;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book book){
        return bookService.update(id, book);
    }

   /* @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBook(id);
    }*/
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Integer id){
        try {
            bookService.getBookById(id);
            bookService.deleteBook(id);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT) ;
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND) ;
           
        }catch(Exception er){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    

}
