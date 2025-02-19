package com.netflix.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User_Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userSubId")
	private Integer id;
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonProperty(access = Access.WRITE_ONLY)
	private User user;
	@Enumerated(EnumType.STRING)
	private Subscription subscription;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt = LocalDateTime.now();
}
