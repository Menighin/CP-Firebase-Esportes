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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFeedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFeedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private FirebaseAuth mAuth;
    private RecyclerView mFeedListRecyclerView;
    private final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseRef = mDatabase.getReference().child("eventos");
    private List<Evento> mEventos = new ArrayList<Evento>();

    public MainFeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFeedFragment newInstance(String param1, String param2) {
        MainFeedFragment fragment = new MainFeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
