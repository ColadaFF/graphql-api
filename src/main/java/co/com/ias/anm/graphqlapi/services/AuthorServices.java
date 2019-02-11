package co.com.ias.anm.graphqlapi.services;

import co.com.ias.anm.graphqlapi.repositories.AuthorRepository;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServices {

    private final AuthorRepository repository;

    @Autowired
    public AuthorServices(AuthorRepository repository) {
        this.repository = repository;
    }

    public Map<String, String> getAuthorById(String id){
        return repository.allAuthors()
                .stream()
                .filter(map -> Objects.equals(map.get("id"), id))
                .findFirst()
                .orElse(null);
    }
}
