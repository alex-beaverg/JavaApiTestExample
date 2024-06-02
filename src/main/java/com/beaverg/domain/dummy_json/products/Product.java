package com.beaverg.domain.dummy_json.products;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Product {
    private int id;
    private String title;
    private String description;
    private String category;
    private int price;
    private double discountPercentage;
    private double rating;
    private int stock;
    private List<String> tags;
    private String brand;
    private String sku;
    private int weight;
    private Map<String, Double> dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Map<String, String>> reviews;
    private String returnPolicy;
    private int minimumOrderQuantity;
    private Map<String, String> meta;
    private String thumbnail;
    private List<String> images;

    public Product() { }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Map<String, Double> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, Double> dimensions) {
        this.dimensions = dimensions;
    }

    public String getWarrantyInformation() {
        return warrantyInformation;
    }

    public void setWarrantyInformation(String warrantyInformation) {
        this.warrantyInformation = warrantyInformation;
    }

    public String getShippingInformation() {
        return shippingInformation;
    }

    public void setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public List<Map<String, String>> getReviews() {
        return reviews;
    }

    public void setReviews(List<Map<String, String>> reviews) {
        this.reviews = reviews;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    public int getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(int minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                Double.compare(product.discountPercentage, discountPercentage) == 0 &&
                Double.compare(product.rating, rating) == 0 &&
                stock == product.stock &&
                weight == product.weight &&
                minimumOrderQuantity == product.minimumOrderQuantity &&
                Objects.equals(title, product.title) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category) &&
                Objects.equals(tags, product.tags) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(sku, product.sku) &&
                Objects.equals(dimensions, product.dimensions) &&
                Objects.equals(warrantyInformation, product.warrantyInformation) &&
                Objects.equals(shippingInformation, product.shippingInformation) &&
                Objects.equals(availabilityStatus, product.availabilityStatus) &&
                Objects.equals(reviews, product.reviews) &&
                Objects.equals(returnPolicy, product.returnPolicy) &&
                Objects.equals(meta, product.meta) &&
                Objects.equals(thumbnail, product.thumbnail) &&
                Objects.equals(images, product.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, price, discountPercentage, rating, stock, tags,
                brand, sku, weight, dimensions, warrantyInformation, shippingInformation, availabilityStatus,
                reviews, returnPolicy, minimumOrderQuantity, meta, thumbnail, images);
    }
}