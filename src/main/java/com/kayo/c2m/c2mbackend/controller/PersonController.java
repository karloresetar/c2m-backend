package com.kayo.c2m.c2mbackend.controller;


import com.kayo.c2m.c2mbackend.entity.Person;
import com.kayo.c2m.c2mbackend.service.PersonService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.kayo.c2m.c2mbackend.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;


@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return ResponseEntity.created(URI.create("/persons/userID")).body(personService.createPerson(person));
    }

    @GetMapping
    public ResponseEntity<Page<Person>> getPerson(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(personService.getAllPersons(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable(value = "id") String id){
        return ResponseEntity.ok().body(personService.getPerson(id));
    }


    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file")MultipartFile file){
        return ResponseEntity.ok().body(personService.uploadPhoto(id, file));
    }

    @GetMapping(path ="/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException{
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
