package com.example.a2dz4.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(com.example.to_dolistproject.data.NoteModel... noteModels);

    @Update
    void updateNotes(com.example.to_dolistproject.data.NoteModel noteModel);

    @Delete
    void delete(com.example.to_dolistproject.data.NoteModel noteModel);

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    LiveData<List<com.example.to_dolistproject.data.NoteModel>> getAllNotes();

    @Query("DELETE  FROM note_table")
    void deleteAll();


}
