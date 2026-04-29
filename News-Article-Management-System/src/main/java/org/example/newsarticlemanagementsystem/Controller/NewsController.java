package org.example.newsarticlemanagementsystem.Controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsarticlemanagementsystem.Api.ApiResponse;
import org.example.newsarticlemanagementsystem.Model.NewsModel;
import org.example.newsarticlemanagementsystem.Service.NewsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsServices newsServices;

    @GetMapping("/get")
    public ResponseEntity<?> getNews() {
        return ResponseEntity.status(200).body(newsServices.getNewsArray());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNews(@RequestBody @Valid NewsModel newsModel, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        newsServices.addNews(newsModel);
        return ResponseEntity.status(200).body(new ApiResponse("News added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNews(@PathVariable String id, @RequestBody @Valid NewsModel newsModel, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = newsServices.updateNews(id, newsModel);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("News updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable String id) {
        boolean isDeleted = newsServices.deleteNews(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("News deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishNews(@PathVariable String id) {
        boolean isPublished = newsServices.publishNews(id);
        if (isPublished) {
            return ResponseEntity.status(200).body(new ApiResponse("News published successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @GetMapping("/all-published-news")
    public ResponseEntity<?> getPublishedNews() {
        return ResponseEntity.status(200).body(newsServices.getAllPublishedNews());
    }

    @GetMapping("/get-news-by-category/{category}")
    public ResponseEntity<?> getAllNewsByCategory(@PathVariable String category) {
        return ResponseEntity.status(200).body(newsServices.getAllNewsByCategory(category));
    }
}