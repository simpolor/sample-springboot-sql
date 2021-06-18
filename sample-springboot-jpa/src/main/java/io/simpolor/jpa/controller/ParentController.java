package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.ParentDto;
import io.simpolor.jpa.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parents")
@RestController
@RequiredArgsConstructor
public class ParentController {

	private final ParentService parentService;

	@GetMapping(value="/total-count")
	public long totalCount() {

		return parentService.getTotalCount();
	}

	@GetMapping
	public List<ParentDto> list() {

		return ParentDto.of(parentService.getAll());
	}

	@GetMapping(value="/{seq}")
	public ParentDto detail(@PathVariable Long seq) {

		return ParentDto.of(parentService.get(seq));
	}

	@PostMapping
	public void register(@RequestBody ParentDto request) {

		parentService.create(request.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody ParentDto request) {

		request.setSeq(seq);
		parentService.update(request.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		parentService.delete(seq);
	}

}
