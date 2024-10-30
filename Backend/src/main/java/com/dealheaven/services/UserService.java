package com.dealheaven.services;

import com.dealheaven.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String USER_COLLECTION = "users";

    public void createUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection(USER_COLLECTION);

        ApiFuture<QuerySnapshot> emailQuery = users.whereEqualTo("email", user.getEmail()).get();
        List<QueryDocumentSnapshot> emailDocuments = emailQuery.get().getDocuments();
        ApiFuture<QuerySnapshot> phoneQuery = users.whereEqualTo("email", user.getEmail()).get();
        List<QueryDocumentSnapshot> phonedDocuments = phoneQuery.get().getDocuments();

        if(!emailDocuments.isEmpty()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        if(!phonedDocuments.isEmpty()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }


        // Add user and retrieve the document reference
        DocumentReference documentReference = users.add(user).get();
        // Set the Firebase-generated ID to the user object
        String generatedId = documentReference.getId();
        user.setId(generatedId);
        // Optionally update the user document with the generated ID
        documentReference.set(user).get();
    }


    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference userDoc = db.collection(USER_COLLECTION).document(userId);
        return userDoc.get().get().toObject(User.class);
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        // Query Firestore to get the document by email
        CollectionReference users = db.collection(USER_COLLECTION);
        Query query = users.whereEqualTo("email", email);
        List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();

        // If no document is found, return null or throw an appropriate exception
        if (documents.isEmpty()) {
            throw new IllegalArgumentException("No user exists with this email");
        }

        // Return the first document found (if your emails are unique)
        return documents.get(0).toObject(User.class);
    }


    public void updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference userDoc = db.collection(USER_COLLECTION).document(String.valueOf(user.getId()));
        userDoc.set(user).get();
    }

    public void deleteUser(String userId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(USER_COLLECTION).document(userId).delete().get();
    }

    public String getUserIdByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection(USER_COLLECTION);
        Query query = users.whereEqualTo("email", email);
        List<QueryDocumentSnapshot> documents = query.get().get().getDocuments();
        if (documents.isEmpty()) {
            throw new IllegalArgumentException("No user exists with this email");
        }
        return documents.get(0).getId();
    }
}
