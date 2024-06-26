package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.service;


import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.BlogPost;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> findById(UUID id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost save(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public void deleteById(UUID id) {
        blogPostRepository.deleteById(id);
    }
}

