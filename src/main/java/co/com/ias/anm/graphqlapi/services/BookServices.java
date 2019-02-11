package co.com.ias.anm.graphqlapi.services;

import co.com.ias.anm.graphqlapi.repositories.BookRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServices {

    private final BookRepository repository;

    @Autowired
    public BookServices(BookRepository repository) {
        this.repository = repository;
    }

    public List<Map<String, String>> allBooks(){
        return repository.allBooks();
    }

    public Map<String, String> getBookById(String id){
        return repository.allBooks()
                .stream()
                .filter(map -> Objects.equals(map.get("id"), id))
                .findFirst()
                .orElse(null);
    }
}
