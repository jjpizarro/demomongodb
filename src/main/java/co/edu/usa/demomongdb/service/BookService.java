package co.edu.usa.demomongdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.demomongdb.documents.Book;
import co.edu.usa.demomongdb.exceptions.ResourceNotFoundException;
import co.edu.usa.demomongdb.repositories.BookCrudRespository;

@Service
public class BookService {
    @Autowired private BookCrudRespository bookRepository;

    public Book create(Book book){
        return bookRepository.save(book);
    }

    public Book update(Book book){
        return update(book.getId(),book);
    }
    public Book update(Integer id, Book book){
        Optional<Book> bookInDB = bookRepository.findById(id);
        if(bookInDB.isPresent()){
            Book bookUpdate = bookInDB.get();
            bookUpdate.setId(book.getId());
            bookUpdate.setName(book.getName());
            bookUpdate.setAuthor(book.getAuthor());
            bookUpdate.setCost(book.getCost());
            bookUpdate.setPages(book.getPages());
            return bookRepository.save(bookUpdate);
        }else{
            throw new ResourceNotFoundException("Book with id: "+book.getId()+" NotFound");
        }
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBookById(Integer id){
        Optional<Book> bookInDb = bookRepository.findById(id);
        if(bookInDb.isPresent()){
            return bookInDb.get();
        }else{
            throw new ResourceNotFoundException("Book with id: "+id+" NotFound");
        }
    }

    public List<Book> getBookByAuthor(String author){
        return bookRepository.getBooksByAuthor(author);
    }

    public List<Book> getBookContainAuthorName(String author){
        return bookRepository.getBoosByAuthorRegEx(author);
    }

    public void deleteBook(Integer id){
        Optional<Book> bookInDb = bookRepository.findById(id);
        if(bookInDb.isPresent()){
            bookRepository.delete(bookInDb.get());
        }else{
            throw new ResourceNotFoundException("Book with id: "+id+" NotFound");
        }
    }
}
