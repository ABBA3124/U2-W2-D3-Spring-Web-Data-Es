package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.service;


import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.Autore;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.BlogPost;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.exceptions.ResourceNotFoundException;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.payloadRequest.BlogPostPayloadRequest;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    public Page<BlogPost> findAll(int pageNumber, int pageSize) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return blogPostRepository.findAll(pageable);

    }

    public Optional<BlogPost> findById(UUID id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost save(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }


    public BlogPost save2(BlogPostPayloadRequest blogPostPayloadRequest) {
        Autore autore = autoreService.findById(blogPostPayloadRequest.getAutoreId()).orElseThrow(()-> new ResourceNotFoundException("Id autore inserito non esistente " + blogPostPayloadRequest.getAutoreId()));
        BlogPost blogPost = new BlogPost();
        blogPost.setCategoria(blogPostPayloadRequest.getCategoria());
        blogPost.setTitolo(blogPostPayloadRequest.getTitolo());
        blogPost.setCover(blogPostPayloadRequest.getCover());
        blogPost.setContenuto(blogPostPayloadRequest.getContenuto());
        blogPost.setTempoDiLettura(blogPostPayloadRequest.getTempoDiLettura());
        blogPost.setAutore(autore);
        return blogPostRepository.save(blogPost);
    }



    public BlogPost updateBlogPost(UUID id, BlogPostPayloadRequest blogPostPayloadRequest) {
        BlogPost existingBlogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost nessun  ID trovato con: " + id));

        Autore autore = autoreService.findById(blogPostPayloadRequest.getAutoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Id autore inserito non esistente " + blogPostPayloadRequest.getAutoreId()));

        existingBlogPost.setCategoria(blogPostPayloadRequest.getCategoria());
        existingBlogPost.setTitolo(blogPostPayloadRequest.getTitolo());
        existingBlogPost.setCover(blogPostPayloadRequest.getCover());
        existingBlogPost.setContenuto(blogPostPayloadRequest.getContenuto());
        existingBlogPost.setTempoDiLettura(blogPostPayloadRequest.getTempoDiLettura());
        existingBlogPost.setAutore(autore);

        return blogPostRepository.save(existingBlogPost);
    }


    public void deleteById(UUID id) {
        blogPostRepository.deleteById(id);
    }



}

