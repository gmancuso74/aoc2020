package org.mancuso.aoc2020;

import java.util.*;
import java.util.stream.Collectors;

public enum Field {
    byr ("byr","Birth Year",true),
    iyr ("iyr","Issue Year",true),
    eyr ("eyr","Expiration Year",true),
    hgt ("hgt","Height",true),
    hcl ("hcl","Hair Color",true),
    ecl ("ecl","Eye Color",true),
    pid ("pid","Passport ID",true),
    cid ("cid","Country ID",false);

    private static final Map<String,Field> map;
    static {
        map = new HashMap<>();
        for (Field v : Field.values()) {
            map.put(v.code, v);
        }
    }

    public static Field byCode(String code) {
        return map.get(code);
    }

    public static Collection<Field>getRequired() {
        return Arrays.stream(Field.values()).filter(x->x.required).collect(Collectors.toCollection(HashSet::new));
    }

    private final String name;
    private final String code;
    private final boolean required;
    private Field(String code,String Name,boolean required) {
        this.code=code;
        this.name=Name;
        this.required=required;
    }

}
