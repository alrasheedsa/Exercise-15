package org.example.newsarticlemanagementsystem.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class NewsModel {

    @NotEmpty(message = "ID must not be empty")
    private String id;

    @NotEmpty(message = "Title must not be empty")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotEmpty(message = "Author must not be empty")
    @Size(min = 4, max = 20, message = "Author must be between 4 and 20 characters")
    private String author;

    @NotEmpty(message = "Content must not be empty")
    @Size(min = 200, message = "Content must be at least 200 characters")
    private String content;

    @NotEmpty(message = "Category must not be empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "Category must be either politics, sports, or technology")
    private String category;

    @NotEmpty(message = "Image URL must not be empty")
    private String imageUrl;

    private boolean isPublished;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
