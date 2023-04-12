package com.example.lostitfoundit;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "uid",
        childColumns = "creator",
        onDelete = ForeignKey.CASCADE)
})
public class Post {

    enum STATUS {
        LOST,
        FOUND,
        PENDING
    }

    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "creator")
    public int creator;
    @ColumnInfo(name = "item_name")
    public String itemName;
    public String itemDescription;
    public String location;
    public String status;
    public String reportedDate;


    public Post(int creator, String itemName, String itemDescription, String location, String status, String reportedDate) {
        this.creator = creator;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.location = location;
        this.status = status;
        this.reportedDate = reportedDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "{ " + this.pid + " " + this.itemName + " was reported by UserID " + this.creator + " on " + this.reportedDate + " }";
    }
}
