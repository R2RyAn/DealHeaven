package com.dealheaven.services;

import com.dealheaven.models.Post;
import com.dealheaven.models.User;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {

    private static final String POSTS_COLLECTION = "posts";
    private static final String USERS_COLLECTION = "users";

    // Method to add a new post (product)
    public void addPost(Post post) throws ExecutionException, InterruptedException {
        if (post.getSellerId() == null || post.getSellerId().trim().isEmpty()) {
            throw new IllegalArgumentException("'sellerId' must be a non-empty string");
        }

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference userDoc = db.collection(USERS_COLLECTION).document(post.getSellerId());

        // Ensure the user exists
        if (!userDoc.get().get().exists()) {
            throw new IllegalArgumentException("User with sellerId '" + post.getSellerId() + "' does not exist");
        }

        CollectionReference posts = db.collection(POSTS_COLLECTION);

        // Add the post and retrieve the document reference
        DocumentReference documentReference = posts.add(post).get();
        String generatedId = documentReference.getId();
        post.setId(generatedId);

        // Update the post with the generated ID
        documentReference.set(post).get();

        // Update the user's post list
        updateUserPosts(post.getSellerId(), generatedId);
    }

    // Method to update the user's post list
    private void updateUserPosts(String sellerId, String postId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference userDoc = db.collection(USERS_COLLECTION).document(sellerId);

        User user = userDoc.get().get().toObject(User.class);

        // If user exists but has no post list, initialize it
        if (user.getPostIds() == null) {
            user.setPostIds(new ArrayList<>());
        }

        // Add the post ID to the user's list of posts
        user.getPostIds().add(postId);

        // Update the user document in Firestore
        userDoc.set(user).get();
    }

    // Method to delete a post
    public void deletePost(String postId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(POSTS_COLLECTION).document(postId).delete().get();
    }

    // Method to get all posts
    public List<Post> getAllPosts() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference postsCollection = db.collection(POSTS_COLLECTION);

        List<QueryDocumentSnapshot> documents = postsCollection.get().get().getDocuments();

        List<Post> posts = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Post post = document.toObject(Post.class);
            posts.add(post);
        }
        return posts;
    }

    public Post getPostById(String postId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference postDoc = db.collection(POSTS_COLLECTION).document(postId);
        return postDoc.get().get().toObject(Post.class);
    }

    public List<Post> getAllPostsFromUser(String sellerId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference postsCollection = db.collection(POSTS_COLLECTION);

        List<QueryDocumentSnapshot> documents = postsCollection.whereEqualTo("sellerId", sellerId).get().get().getDocuments();
        List<Post> posts = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Post post = document.toObject(Post.class);
            posts.add(post);
        }
        return posts;
    }
}
