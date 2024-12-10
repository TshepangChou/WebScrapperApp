package webscraperapp;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Tshepang Chou
 */
public class WebScraperApp 
{
    public static void main(String[] args) 
    {
        // Accept user input for website URL
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the website URL to scrape: ");
        String url = scanner.nextLine();
        
        // Scrape and store data for the entered URL
        WebScraper.scrapeAndStoreData(url);
        
        // Schedule scraping task to run every 1 hour (example)
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> 
        {
            // List of websites to scrape periodically
            String[] websitesToScrape = {"https://example.com", "https://netbeans.apache.org/front/main/index.html"};
//"https://anotherexample.com"};
            for (String website : websitesToScrape)
            {
                WebScraper.scrapeAndStoreData(website);
            }
        }, 0, 1, TimeUnit.HOURS);  // Scrapes every hour
        
        DatabaseHelper.retrieveData();
    }   
}