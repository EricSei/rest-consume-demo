package com.example.rest.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class SpringWebClientController {
	
	@Autowired
	WebClient createWebClient;
	
	
	@GetMapping("/test/post/{id}")
	public ResponseEntity<Mono<Post>> getPostTest(@PathVariable String id) {
	
		Mono<Post> postMono = createWebClient.get()
				.uri("/posts/" + id)
				.retrieve()
				.bodyToMono(Post.class);
		
		return new ResponseEntity<Mono<Post>>(postMono, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/v1/posts/{id}")
	public ResponseEntity< Post> getPost(@PathVariable String id){
		System.out.println("id -------------- " + id);
		
		Post postMono = createWebClient
				.get()
				.uri("/posts/"+id)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
		System.out.println(postMono.toString());
//		postMono.subscribe(System.out::println);
		return new ResponseEntity<Post> (postMono, HttpStatus.OK);
		
	}
	
	@PostMapping(path="/v1/posts", consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Mono<Post> createPost(@RequestBody Post post) {
		System.out.println(post.toString());
		return createWebClient.post()
				 .uri("/posts")
				 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .body(BodyInserters.fromValue(post))
				 .retrieve()
				 .bodyToMono(Post.class);
	}
	
	@PutMapping(path="/v1/posts")
	public Mono<Post> updatePost(@RequestBody Post post){
		
		return createWebClient
				.put()
				.uri("/posts/"+post.getId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(post))
				.retrieve()
				.bodyToMono(Post.class);
		
	}
	
}
