package co.edu.usa.demomongdb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.edu.usa.demomongdb.documents.Book;

public interface BookCrudRespository extends MongoRepository<Book,Integer>{

    //SELECT * FROM BOOKS WHERE id = ? -> SQL
    @Query("{id:?0}")
    Optional<Book> findBookById(Integer id);

    //SQL -> select * from books where pages < ?
    @Query("{pages:{$lt:?0}}") // db.books.find({pages:{$lt:0?}})
    //@Query("{pages:{$gte:0?}}") ////SQL -> select * from books where pages >= ?
    ///@Query("{pages:0?}") ////SQL -> select * from books where pages = ?
    List<Book> getBooksByPages(Integer pages);

    @Query("{author: ?0}") //SQL -> select * from books where author = ?
    List<Book> getBooksByAuthor(String author);

    @Query("{author:?0,cost:?0}") //SQL -> select * from books where author = ? and cost = ?
    //@Query("{$and:[{author:0?},{cost:1?}]}")
    List<Book> getBooksByAuthorAndCost(String author, Double cost);

    @Query("{$or:[{author:?0},{name:?1}]}") //SQL -> select * from books where author = ? or name = ?
    List<Book> getBooksByAuthorOrName(String author, String name);

    @Query(value = "{author:?0}", sort = "{name:1}") //SQL -> select * from books where author = ? order by asc
    List<Book> getBooksByAuthorSortByName(String author);

    @Query(value = "{author:?0}", count = true) //SQL -> Select Count(*) from books wher author = ?
    List<Book> getBookCountByAuthor(String author);

    @Query(value = "{author:?0}", fields = "{name:1,author:1}") //SQL-> select name, author from  books where pages = ?
    List<Book> getBooksNameAndAuthorByPages(Integer pages);

    //SQL-> SELECT * FROM BOOKS WHERE name like '%Core'
    @Query("{author:{$regex:?0}}")
    List<Book> getBoosByAuthorRegEx(String author);

}


