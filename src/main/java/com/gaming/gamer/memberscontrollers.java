package com.gaming.gamer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class memberscontrollers {

    @Autowired
    private membersservice service;

    // CREATE
    @PostMapping
    public ResponseEntity<MemberDTO> create(@RequestBody MemberDTO memberDTO) {
        MemberDTO created = service.create(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll() {
        List<MemberDTO> members = service.findAll();
        return ResponseEntity.ok(members);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable String id) {
        MemberDTO member = service.findById(id);
        return ResponseEntity.ok(member);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable String id, @RequestBody MemberDTO memberDTO) {
        MemberDTO updated = service.update(id, memberDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // SEARCH BY PHONE
    @GetMapping ("/search")
    public ResponseEntity<MemberDTO> searchMemberByPhone(@RequestBody SearchRequestDTO searchRequest) {
        System.out.println(" ******* inside search member");
    	
    	MemberDTO profile = service.searchByPhone(searchRequest.getPhone());
        return ResponseEntity.ok(profile);
    }
}
