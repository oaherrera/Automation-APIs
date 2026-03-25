package com.projectaicopilot.screenplay.utils;

public class ApiEndpoints {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    
    public static final String POSTS = "/posts";
    public static final String USERS = "/users";
    public static final String COMMENTS = "/comments";
    public static final String ALBUMS = "/albums";
    public static final String PHOTOS = "/photos";
    public static final String TODOS = "/todos";

    public static String getPostById(int id) {
        return POSTS + "/" + id;
    }

    public static String getUserById(int id) {
        return USERS + "/" + id;
    }

    public static String getUserPosts(int userId) {
        return POSTS + "?userId=" + userId;
    }

    public static String getPostComments(int postId) {
        return POSTS + "/" + postId + "/comments";
    }
}
