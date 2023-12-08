package org.acme;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.cloud.firestore.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/firestore")
public class FirestoreResource {
    @Inject
    Firestore firestore; // Inject Firestore

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String firestore() throws ExecutionException, InterruptedException {

        Iterable<CollectionReference> collections = firestore.listCollections();
        StringBuilder result = new StringBuilder();
        for (CollectionReference collection : collections) {
            Stream<QueryDocumentSnapshot> documents = collection.get().get().getDocuments().stream();
            final Stream<String> strings = documents.map(document -> collection.getId() + " - " + document.getId() + "\n");
            String string = strings.collect(Collectors.joining());
            System.out.println(string);
            result.append(string);
        }

        return result.toString();
    }
}
