package br.com.avila.controller;


import br.com.avila.model.Book;
import br.com.avila.proxy.CambioProxy;
import br.com.avila.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book End Point")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @Operation(summary = "Find specific book by ID")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,  @PathVariable("currency") String currency){
        var port = environment.getProperty("local.server.port");

        var book = repository.getById(id);

        var cambio = proxy.getCambio(book.getPrice(),"USD",currency);

         if(book == null){  throw new RuntimeException("Book not Found"); }
         book.setEnvironment(port);
         book.setPrice(cambio.getConvertedValue());
       return book;
    }


  /*  @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,  @PathVariable("currency") String currency){
        var port = environment.getProperty("local.server.port");

        var book = repository.getById(id);

        HashMap<String,String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to",currency);


        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);

        var cambio = response.getBody();

        if(book == null){  throw new RuntimeException("Book not Found"); }
        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());
        return book;
    }*/




}
