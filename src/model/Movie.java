package model;

import java.util.Date;

/**
 * Created by Wouter on 3/1/2016.
 * Dont mind this class i was having fun
 */
public class Movie {
    public static final int UNKNOWN = 1 << 18;
    public static final int GENRE_ACTION = 1 << 17;
    public static final int GENRE_ADVENTURE = 1 << 16;
    public static final int GENRE_ANIMATION = 1 << 15;
    public static final int GENRE_CHILDREN = 1 << 14;
    public static final int GENRE_COMEDY = 1 << 13;
    public static final int GENRE_CRIME = 1 << 12;
    public static final int GENRE_DOCUMENTARY = 1 << 11;
    public static final int GENRE_DRAMA = 1 << 10;
    public static final int GENRE_FANTASY = 1 << 9;
    public static final int GENRE_FILM_NOIR = 1 << 8;
    public static final int GENRE_HORROR = 1 << 7;
    public static final int GENRE_MUSICAL = 1 << 6;
    public static final int GENRE_MYSTERY = 1 << 5;
    public static final int GENRE_ROMANCE = 1 << 4;
    public static final int GENRE_SCIFI = 1 << 3;
    public static final int GENRE_THRILLER = 1 << 2;
    public static final int GENRE_WAR = 1 << 1;
    public static final int GENRE_WESTERN = 1 << 0;

    private long id;
    private String title;
    private Date releaseDate;
    private Date videoReleaseDate;
    private String imdb;
    private int genres;

    public Movie(long id, String title, Date releaseDate, Date videoReleaseDate, String imdb, int genres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.videoReleaseDate = videoReleaseDate;
        this.imdb = imdb;
        this.genres = genres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getVideoReleaseDate() {
        return videoReleaseDate;
    }

    public void setVideoReleaseDate(Date videoReleaseDate) {
        this.videoReleaseDate = videoReleaseDate;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getGenres() {
        return genres;
    }

    public void setGenres(int genres) {
        this.genres = genres;
    }

    // param genre is a constant movie genre
    public boolean isGenre(int genre){
        return (this.genres & genre) != 0;
    }

    public void addGenre(int genre){
        this.genres |= genre;
    }
    public void removeGenre(int genre){
        this.genres ^= genre;
    }

}
