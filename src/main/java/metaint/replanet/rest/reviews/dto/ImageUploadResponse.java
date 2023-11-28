package metaint.replanet.rest.reviews.dto;

public class ImageUploadResponse {
    private String imageUrl;
    private boolean status;
    private String message;

    // Constructors, getters, and setters

    // Constructor for success
    public ImageUploadResponse(String imageUrl, boolean status) {
        this.imageUrl = imageUrl;
        this.status = status;
    }

    // Constructor for failure
    public ImageUploadResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
