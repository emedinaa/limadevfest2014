package com.gdglima.myapp.user;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gdglima.myapp.R;
import com.gdglima.myapp.entity.SpeakerEntity;

import java.util.Arrays;

/**
 * Created by @eduardomedina on 23/08/2014.
 */
public class SpeakerListFragment extends Fragment
{

    private ListView lviUser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lviUser= (ListView)getView().findViewById(R.id.lviUser);

        SpeakerEntity[] data = new SpeakerEntity[]{
                new SpeakerEntity(100, "Eduardo Medina Alfaro", "Android",R.drawable.eduardo),
                new SpeakerEntity(101, "Carlos Piñan", "Android, Games",R.drawable.pinian),
                new SpeakerEntity(102, "Hansy Schmitt", "Android,Cloud",R.drawable.hansy),
                new SpeakerEntity(102, "Milton Rodriguez", "Cloud, Web",R.drawable.default_user)
        };

        SpeakerAdapter adapter = new SpeakerAdapter(getActivity(), R.layout.row_speaker, Arrays.asList(data));
        lviUser.setAdapter(adapter);
    }
}
