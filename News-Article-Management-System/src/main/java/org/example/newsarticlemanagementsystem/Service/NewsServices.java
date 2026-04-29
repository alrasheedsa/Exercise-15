package org.example.newsarticlemanagementsystem.Service;
import org.example.newsarticlemanagementsystem.Model.NewsModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class NewsServices {
    ArrayList<NewsModel> newsArray = new ArrayList<>();

    // Get all news
    public ArrayList<NewsModel> getNewsArray() {
        return newsArray;
    }

    // Add new news
    public void addNews(NewsModel newsModel) {
        newsArray.add(newsModel);
    }

    // Update news
    public boolean updateNews(String id, NewsModel newsModel) {
        for (int i = 0; i < newsArray.size(); i++) {
            if (newsArray.get(i).getId().equals(id)) {
                newsArray.set(i, newsModel);
                return true;
            }
        }
        return false;
    }

    // Delete news
    public boolean deleteNews(String id) {
        for (NewsModel n : newsArray) {
            if (n.getId().equals(id)) {
                newsArray.remove(n);
                return true;
            }
        }
        return false;
    }

    public boolean publishNews(String id) {
        for (NewsModel n : newsArray) {
            if (n.getId().equals(id)) {
                n.setPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsModel> getAllPublishedNews() {
        ArrayList<NewsModel> publishedList = new ArrayList<>();
        for (NewsModel n : newsArray) {
            if (n.isPublished()) {
                publishedList.add(n);
            }
        }
        return publishedList;
    }

    public ArrayList<NewsModel> getAllNewsByCategory(String category) {
        ArrayList<NewsModel> categoryList = new ArrayList<>();
        for (NewsModel n : newsArray) {
            if (n.getCategory().equalsIgnoreCase(category)) {
                categoryList.add(n);
            }
        }
        return categoryList;
    }
}