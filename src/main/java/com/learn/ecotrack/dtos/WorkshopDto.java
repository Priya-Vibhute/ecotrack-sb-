package com.learn.ecotrack.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkshopDto {
	
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer duration;//days
	private String image;
	private String venue;
	private LocalDateTime startDate;

}
