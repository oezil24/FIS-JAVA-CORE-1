package com.model;

import lombok.Data;

import java.util.Set;
@Data
public class Storage extends AbstractEntity{
    private String name;
    private String location;
    private Set<Evidence> evidenceSet;
}
