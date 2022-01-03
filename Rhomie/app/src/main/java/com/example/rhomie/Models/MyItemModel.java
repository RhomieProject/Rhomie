package com.example.rhomie.Models;

import androidx.annotation.NonNull;

import com.example.rhomie.Objects.IUser;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.Objects.Request;
import com.example.rhomie.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

public class MyItemModel extends Observable {

    private final FirebaseUser user;
    private DatabaseReference items, users, userToItem;
    private ArrayList<Request> requestList;
    ArrayList<String> details = new ArrayList<>();

    public MyItemModel(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        userToItem = db.getReference("UserToItem");
        users = db.getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getRequests(String item_id) {

        userToItem.child(user.getUid()).child(item_id).child("requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Request> requestList = new ArrayList<>();
                for (DataSnapshot dts : snapshot.getChildren()) {
                    Request req = dts.getValue(Request.class);
                    if((req.getStatus() == 1 || req.getStatus() == 0))
                        requestList.add(req);
                }
                setChanged();
                notifyObservers(requestList);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

}