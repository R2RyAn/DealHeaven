package com.dealheaven.services;
import com.dealheaven.models.Post;
import com.dealheaven.models.User;
import com.dealheaven.services.UserService;
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
    private final UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    // Method to add a new post (product)
    public void addPost(Post post) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
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

        // Get the user document
        User user = userDoc.get().get().toObject(User.class);

        // Add the post ID to the user's list of posts
        user.addPostId(postId);

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

        // Fetch all documents in the "posts" collection
        List<QueryDocumentSnapshot> documents = postsCollection.get().get().getDocuments();

        // Convert documents to Post objects
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < documents.size(); i++) {
            QueryDocumentSnapshot document = documents.get(i);
            Post post = document.toObject(Post.class);
            posts.add(post);
        }


        return posts;
    }


    public Post getPostById(String postId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference userDoc = db.collection(POSTS_COLLECTION).document(postId);
        return userDoc.get().get().toObject(Post.class);
    }

    public List<Post> getAllPostsFromUser(String email) throws ExecutionException, InterruptedException {


        UserService userService = new UserService();
        String userId = userService.getUserIdByEmail(email);

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference postsCollection = db.collection(POSTS_COLLECTION);

        List<QueryDocumentSnapshot> getPosts = postsCollection.whereEqualTo("sellerId", userId).get().get().getDocuments();
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < getPosts.size(); i++) {
            QueryDocumentSnapshot document = getPosts.get(i);
            Post post = document.toObject(Post.class);
            posts.add(post);
        }
        return posts;
    }


}
