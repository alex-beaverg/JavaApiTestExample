package com.beaverg.domain.json_placeholder.photos;

import java.util.Objects;

public class Photo {
    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo() { }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return albumId == photo.albumId &&
                id == photo.id &&
                Objects.equals(title, photo.title) &&
                Objects.equals(url, photo.url) &&
                Objects.equals(thumbnailUrl, photo.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, id, title, url, thumbnailUrl);
    }
}
