package webscraperapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 *
 * @author Tshepang Chou
 */
public class WebScraper 
{
    // Method to scrape and store data from a website
    public static void scrapeAndStoreData(String url)
    {
        try 
        {
            // Connect to the website and fetch the HTML
            Document doc = Jsoup.connect(url).get();
            
            // Extract relevant elements (e.g., title and content)
            Elements titles = doc.select("h1");  // Example: scrape all <h1> tags
            Elements paragraphs = doc.select("p");  // Example: scrape all <p> tags
            
            // Assuming we're interested in the first title and first paragraph for simplicity
            String title = titles.isEmpty() ? "No title" : titles.first().text();
            String content = paragraphs.isEmpty() ? "No content" : paragraphs.first().text();

            // Clean the data (trim whitespaces)
            title = title.trim();
            content = content.trim();
            
            // Store the scraped data into the database
            DatabaseHelper.storeData(url, title, content);
        }
        catch (Exception e) 
        {
            System.out.println("Error scraping website: " + e.getMessage());
        }
    }
}