package com.gigsync.syncrmAPI.Models;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;


public record Employee(@Id Long id, String name) {
}
