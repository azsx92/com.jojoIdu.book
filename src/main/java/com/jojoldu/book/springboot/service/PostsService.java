package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository repository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        //return repository.findById(requestDto.toEntity().getId()).orElse(null);
        return repository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id , PostsUpdateRequestDto requestDto) {
       Posts posts = repository.findById(id).orElseThrow(() ->
              new IllegalArgumentException("해당 게시글이 없습니다. id=" + id ));
       posts.update(requestDto.getTitle(), requestDto.getContent());

       return id;
    }
    public PostsResponseDto findById(Long id) {

        Posts entity = repository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("해당 게시글이 없습니다. id=" + id ));

        return new PostsResponseDto(entity);
    }
}
