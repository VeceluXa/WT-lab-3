package com.danilov.dao;

import com.danilov.entity.rooms.Rooms;

import java.util.List;

public interface RoomsDAO {
    Rooms findRoomByID(Long ID);
    void deleteRoom(long ID);
    void addRoom(Rooms rooms);
    void updateRoom(Rooms rooms, long ID);

    List<Rooms> findAllRooms();
}
