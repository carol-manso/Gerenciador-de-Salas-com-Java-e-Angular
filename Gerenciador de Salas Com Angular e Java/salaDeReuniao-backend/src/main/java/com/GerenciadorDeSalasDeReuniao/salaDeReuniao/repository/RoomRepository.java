package com.GerenciadorDeSalasDeReuniao.salaDeReuniao.repository;


import com.GerenciadorDeSalasDeReuniao.salaDeReuniao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
