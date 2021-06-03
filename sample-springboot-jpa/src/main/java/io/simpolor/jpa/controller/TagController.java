package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.tag.TagResponse;
import io.simpolor.jpa.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tag")
@RestController
public class TagController {

	@Autowired
	private TagService tagService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<TagResponse> getList() {

		return TagResponse.of(tagService.getAll());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public TagResponse get(@PathVariable long seq) {

		return TagResponse.of(tagService.get(seq));
	}

}
