package com.iict.buet.betterreadsdataloader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("author_by_id")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @Id
    @PrimaryKeyColumn(name = "author_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @JsonProperty("key")
    private String id;
    @Column("author_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;
    @Column("personal_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    @JsonProperty("personal_name")
    private String personalName;
}
