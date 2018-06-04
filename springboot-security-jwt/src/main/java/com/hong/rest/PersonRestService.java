package com.hong.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PersonRestService
 * @Description: (Rest 服务.)
 * @author hong
 * @date 2017/6/16
 * @version v1.1
 */
@RestController
public class PersonRestService {
    private static final List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("Hello", "World"));
        persons.add(new Person("Foo", "Bar"));
    }

    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public static List<Person> getPersons() {
        return persons;
    }

    @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
    public static Person getPerson(@PathVariable("name") String name) {
        return persons.stream()
                .filter(person -> name.equalsIgnoreCase(person.getName()))
                .findAny().orElse(null);
    }
}
