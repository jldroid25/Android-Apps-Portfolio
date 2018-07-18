package com.jldroid25.android.Nuntium;

public class FinancialNews {

    //For the news Section Name
    private String newsSectionName;
    // For the news Web Title
    private String newsWebTitle;
    // Date of the news publication
    private String newsPublicationDate;
    //url for the news data
    private String newsUrl;
    //For the Author
    private  String newsWriter;
    //For the bodyText
    private  String newsTextArticle;

    /**
     * Construct a new {@link FinancialNews} object
     * @param sectionName is the news section name
     * @param webTitle  is the article web title
     * @param publicationDate  is for the date of article publication
     * @param_writerNames is the news section writer name
     * @param_articleBodyText   for the bodyText news section
     */
    public FinancialNews(String sectionName, String webTitle, String publicationDate,
                         String url,String writerName, String textArticle) {
        newsSectionName = sectionName;
        newsWebTitle = webTitle;
        newsPublicationDate = publicationDate;
        newsUrl = url;
        newsWriter = writerName;
        newsTextArticle = textArticle;
    }

    //let's create public getter method to return their values.
    //Return the Section Name of the article
    public String getSectionName() { return newsSectionName; }
    //Return the Article Web Title
    public String getWebTitle() { return newsWebTitle; }
    //Return the date the article was publish
    public String getPublicationDate() { return "Published on: " + newsPublicationDate; }
    //Return the article url
    public String getUrl() {return newsUrl ;}
    //Return the author's name
    public String getWriterName() { return "Author: \t" + newsWriter; }
    //Return the article's body text
    public String getArticleBodyText() { return newsTextArticle; }

} // end class