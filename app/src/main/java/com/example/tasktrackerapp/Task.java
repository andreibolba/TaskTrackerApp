package com.example.tasktrackerapp;

public class Task {
        private String description;
        private String name;
        private String username;

        public Task(String username, String name, String description) {
                this.description = description;
                this.name = name;
                this.username = username;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }
}
