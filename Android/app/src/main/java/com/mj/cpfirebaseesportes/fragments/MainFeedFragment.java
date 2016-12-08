package com.mj.cpfirebaseesportes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mj.cpfirebaseesportes.R;
import com.mj.cpfirebaseesportes.adapters.MainFeedAdapter;
import com.mj.cpfirebaseesportes.models.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFeedFragment extends Fragment {
    private FirebaseAuth mAuth;
    private RecyclerView mFeedListRecyclerView;
    private final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseRef = mDatabase.getReference().child("eventos");
    private List<Evento> mEventos = new ArrayList<Evento>();

    public MainFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_feed, container, false);

        mAuth = FirebaseAuth.getInstance();

        mFeedListRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_feed_list);
        mFeedListRecyclerView.setHasFixedSize(true);
        final MainFeedAdapter mFeedAdapter = new MainFeedAdapter(mEventos);
        mFeedListRecyclerView.setAdapter(mFeedAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(container.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mFeedListRecyclerView.setLayoutManager(llm);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                HashMap<String, Object> values = (HashMap<String, Object>) dataSnapshot.getValue();
                for (HashMap.Entry<String, Object> entry : values.entrySet())
                {
                    HashMap<String, Object> eventoHash = (HashMap<String, Object>) entry.getValue();
                    String title = (String) eventoHash.get("titulo");
                    String esporte = (String) eventoHash.get("esporte");
                    String descricao = (String) eventoHash.get("descricao");
                    String local = (String) eventoHash.get("local");
                    Integer nPessoas = eventoHash.get("nPessoas") != null ? Integer.parseInt(eventoHash.get("nPessoas").toString()) : null ;
                    Double valor = eventoHash.get("valor") != null ? Double.parseDouble((String)eventoHash.get("valor").toString()) : null ;

                    mEventos.add(new Evento(descricao, esporte, descricao, local, null, nPessoas, valor));

                }
                mFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        mDatabaseRef.addValueEventListener(postListener);

        // Evento de swipe nos cards
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int pos = viewHolder.getAdapterPosition();
                mFeedAdapter.remove(pos);
                mFeedAdapter.notifyItemRemoved(pos);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mFeedListRecyclerView);

        // Inflate the layout for this fragment
        return rootView;
    }

}
