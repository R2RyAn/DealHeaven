package com.dealheaven.services;

import com.dealheaven.models.Post;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {

    private static final String POSTS_COLLECTION = "posts";

    // Method to add a new post (product)
    public void addPost(Post post) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference posts = db.collection(POSTS_COLLECTION);
        posts.add(post).get();
    }

    // Method to delete a post
    public void deletePost(String postId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(POSTS_COLLECTION).document(postId).delete().get();
    }

    // Method to get all posts
    public List<Post> getAllPosts() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(POSTS_COLLECTION).get().get().toObjects(Post.class);
    }
}
