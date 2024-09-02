package com.gigsync.syncrm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public record Employee (@Id Long id, String name){
}

