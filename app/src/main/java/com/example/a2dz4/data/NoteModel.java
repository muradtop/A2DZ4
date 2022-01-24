package com.example.to_dolistproject.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "note_table")
public class NoteModel {

    @PrimaryKey(autoGenerate = true)
    protected int id;

    @ColumnInfo(name = "task_name")
    protected String taskName;

    @ColumnInfo(name = "date")
    protected String date;

    @ColumnInfo(name = "frequency")
    protected String frequency;

    @ColumnInfo(name = "lid")
    protected int languageId;

    public NoteModel(String taskName, String date, String frequency) {
        this.taskName = taskName;
        this.date = date;
        this.frequency = frequency;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDate() {
        return date;
    }

    public String getFrequency() {
        return frequency;
    }
}
