package com.example.lostitfoundit;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;

@Entity(tableName = "post",
        foreignKeys = {@ForeignKey(
                entity = User.class,
                parentColumns = "uid",
                childColumns = "creator",
                onDelete = ForeignKey.CASCADE)
        })
public class Post {

    enum STATUS {
        LOST,
        FOUND,
        CLAIMED,
        PENDING
    }

    @PrimaryKey(autoGenerate = true)
    public int pid;
    public int creator;
    @NonNull
    @ColumnInfo(name = "item_name")
    public String itemName;
    public String itemDescription;
    public String location;
    @NonNull
    public STATUS status;
    public String reportedDate;
    public String image;


    public Post(int creator, @NonNull String itemName, String itemDescription, String location, @NonNull STATUS status, String reportedDate, String image) {
        this.creator = creator;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.location = location;
        this.status = status;
        this.reportedDate = reportedDate;
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "{ " + this.pid + " " + this.itemName + " was reported by UserID " + this.creator + " on " + this.reportedDate + " }";
    }
}
