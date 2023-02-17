package com.iict.buet.betterreadsdataloader.repository;

import com.iict.buet.betterreadsdataloader.model.Author;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AuthorRepository extends CassandraRepository<Author, String> {
}
