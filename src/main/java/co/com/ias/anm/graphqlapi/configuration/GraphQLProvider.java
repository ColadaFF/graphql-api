package co.com.ias.anm.graphqlapi.configuration;

import co.com.ias.anm.graphqlapi.datafetcher.AuthorDataFetcher;
import co.com.ias.anm.graphqlapi.datafetcher.BookDataFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.net.URL;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private final BookDataFetcher bookDataFetcher;
    private final AuthorDataFetcher authorDataFetcher;
    private GraphQL graphQL;

    @Autowired
    public GraphQLProvider(BookDataFetcher bookDataFetcher, AuthorDataFetcher authorDataFetcher) {
        this.bookDataFetcher = bookDataFetcher;
        this.authorDataFetcher = authorDataFetcher;
    }


    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", bookDataFetcher.getBookByIdDataFetcher())
                        .dataFetcher("allBooks", bookDataFetcher.getAllBooksDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", authorDataFetcher.getAuthorByBookDataFetcher())
                        .dataFetcher("pageCount", bookDataFetcher.getBookPageCount())
                )
                .build();
    }
}