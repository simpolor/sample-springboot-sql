package io.simpolor.jpa.controller;

import io.simpolor.jpa.domain.parent.ParentRequest;
import io.simpolor.jpa.domain.parent.ParentResponse;
import io.simpolor.jpa.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parent")
@RestController
public class ParentController {

	@Autowired
	private ParentService parentService;

	@RequestMapping(value="/totalCount", method=RequestMethod.GET)
	public long totalCount() {

		return parentService.getTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<ParentResponse> getList() {

		return ParentResponse.of(parentService.getAll());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public ParentResponse get(@PathVariable long seq) {

		return ParentResponse.of(parentService.get(seq));
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public void post(@RequestBody ParentRequest request) {

		parentService.register(request.toInsert());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void put(@PathVariable int seq,
					@RequestBody ParentRequest request) {

		parentService.modify(request.toUpdate(seq));
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public void delete(@PathVariable long seq) {

		parentService.delete(seq);
	}

}
