package com.example.taskappkarligach;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
    public class TaskModel implements java.io.Serializable {
        @PrimaryKey(autoGenerate = true)
        private Long id;
        private String title;
        private long createdAt;

        public TaskModel (String title, long createdAt) {
            this.title = title;
            this.createdAt = createdAt;
        }

        public TaskModel() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

}
