package co.com.ias.anm.graphqlapi.services;

import co.com.ias.anm.graphqlapi.repositories.BookRepository;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
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

    public Map<String, String> saveBook(Map<String, Object> bookArg) {
        Map<String, String> transformArgs = bookArg
                .entrySet()
                .stream()
                .map(entry -> Maps.immutableEntry(entry.getKey(), Objects.toString(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        ImmutableMap<String, String> bookCopy = ImmutableMap
                .<String, String>builder()
                .putAll(transformArgs)
                .put("id", UUID.randomUUID().toString())
                .build();
        return repository.saveBook(bookCopy);
    }
}
