package com.dealheaven.services;

import com.dealheaven.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String USER_COLLECTION = "users";

    public void createUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection(USER_COLLECTION);
        users.document(String.valueOf(user.getId())).set(user).get();
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference userDoc = db.collection(USER_COLLECTION).document(userId);
        return userDoc.get().get().toObject(User.class);
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
}
