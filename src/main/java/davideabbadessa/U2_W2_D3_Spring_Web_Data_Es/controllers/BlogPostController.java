package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.controllers;


import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.Autore;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.BlogPost;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.exceptions.ResourceNotFoundException;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.payloadRequest.BlogPostPayloadRequest;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.service.AutoreService;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {


    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return this.blogPostService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable UUID id) {
        Optional<BlogPost> blogPost = blogPostService.findById(id);
        return blogPost.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPostPayloadRequest blogPostPayloadRequest) {
        return blogPostService.save2(blogPostPayloadRequest);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable UUID id, @RequestBody BlogPostPayloadRequest blogPostPayloadRequest) {
        BlogPost updatedBlogPost = blogPostService.updateBlogPost(id, blogPostPayloadRequest);
        return ResponseEntity.ok(updatedBlogPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable UUID id) {
        if (!blogPostService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        blogPostService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
