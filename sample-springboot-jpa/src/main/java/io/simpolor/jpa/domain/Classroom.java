package io.simpolor.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	private String roomName;
}
