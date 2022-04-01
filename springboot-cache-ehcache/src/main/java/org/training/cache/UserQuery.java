package org.training.cache;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.management.Query;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends Query {
    private int id;

    private String code;

    private String name;
}
