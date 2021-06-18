package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.TagDto;
import io.simpolor.jpa.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tags")
@RestController
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;

	@GetMapping
	public List<TagDto> list() {

		return TagDto.of(tagService.getAll());
	}

	@GetMapping(value="/{seq}")
	public TagDto detail(@PathVariable Long seq) {

		return TagDto.of(tagService.get(seq));
	}

}
