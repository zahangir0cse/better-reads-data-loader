package com.iict.buet.betterreadsdataloader.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iict.buet.betterreadsdataloader.model.Author;
import com.iict.buet.betterreadsdataloader.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class DbInit {

    private final AuthorRepository authorRepository;

    @Value("${datadumps.location.authors}")
    private String authorDumpLocation;
    @Value("${datadumps.location.works}")
    private String workDumpLocation;

    public DbInit(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    private void initAuthors(){
        Path path = Paths.get(authorDumpLocation);
        try (Stream<String> lines = Files.lines(path)) {
            lines.limit(10).forEach(line -> {
                String jsonString = line.substring(line.indexOf("{"));
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Author author = mapper.readValue(jsonString, Author.class);
                    author.setId(author.getId().replace("/authors/", ""));
                    authorRepository.save(author);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (IOException e){

        }
    }
    private void initWorks(){

    }

    @PostConstruct
    public void start(){
        /*Author author = new Author();
        author.setId(UUID.randomUUID().toString());
        author.setName("Zahangir");
        author.setPersonalName("Alam");
        author = authorRepository.save(author);*/
        initAuthors();
        initWorks();
    }
}
