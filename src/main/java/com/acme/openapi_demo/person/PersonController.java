package com.acme.openapi_demo.person;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(
   value = "/api/v1/person",
   consumes = MediaType.APPLICATION_JSON_VALUE,
   produces = MediaType.APPLICATION_JSON_VALUE
)
class PersonController {

   private final AtomicInteger id = new AtomicInteger();
   private final Map<String, Person> persons = new ConcurrentHashMap<>();

   @PostMapping("/add")
   void addPersons(@RequestBody NewPerson newPerson) {
      int id = this.id.getAndIncrement();
      persons.put(
         Integer.toString(id),
         new Person(id, newPerson.name(), newPerson.age())
      );
   }

   @GetMapping(value = "/getAll",
      consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
   )
   List<Person> getAllPersons() {
      return new ArrayList<>(persons.values());
   }

   @GetMapping(value = "/get/{id}")
   Person getPersons(@PathVariable int id) {
      return persons.get(Integer.toString(id));
   }
}

record NewPerson(String name, int age) {
}

record Person(int id, String name, int age) {
}
