package com.GerenciadorDeSalasDeReuniao.salaDeReuniao.controller;


import com.GerenciadorDeSalasDeReuniao.salaDeReuniao.exceptions.ResourceNotFoundException;
import com.GerenciadorDeSalasDeReuniao.salaDeReuniao.model.Room;
import com.GerenciadorDeSalasDeReuniao.salaDeReuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200") //Front vai poder consumir
@RequestMapping("/api/v1")

public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId)
            throws ResourceNotFoundException {{
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(()-> new ResourceNotFoundException("Room not find : " + roomId));
            return ResponseEntity.ok().body(room);
        }
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long roomId,
                                           @Valid @RequestBody Room roomDetails)
        throws  ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId ));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updatedRoom = roomRepository.save(room);
        return ResponseEntity.ok(updatedRoom);


    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id" ) Long roomId)
        throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Rooom not foud with id ::" + roomId));
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put ("deleted", Boolean.TRUE);
        return  response;
    }
}
