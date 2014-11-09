package com.gdglima.myapp.user;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gdglima.myapp.R;
import com.gdglima.myapp.entity.SpeakerEntity;
import com.gdglima.myapp.entity.SpeakerResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @eduardomedina on 23/08/2014.
 */
public class SpeakerListFragment extends Fragment
{

    private ListView lviSpeaker;
    private View rlayLoading;
    private RequestQueue queue;
    private List<SpeakerEntity> dataSpeaker;
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

        lviSpeaker= (ListView)getView().findViewById(R.id.lviSpeaker);
        rlayLoading= getView().findViewById(R.id.rlayLoading);
        rlayLoading.setVisibility(View.GONE);
        /*SpeakerEntity[] data = new SpeakerEntity[]{
                new SpeakerEntity(100, "Eduardo Medina Alfaro", "Android",R.drawable.eduardo),
                new SpeakerEntity(101, "Carlos Piñan", "Android, Games",R.drawable.pinian),
                new SpeakerEntity(102, "Hansy Schmitt", "Android,Cloud",R.drawable.hansy),
                new SpeakerEntity(102, "Milton Rodriguez", "Cloud, Web",R.drawable.default_user)
        };

        SpeakerAdapter adapter = new SpeakerAdapter(getActivity(), R.layout.row_speaker, Arrays.asList(data));
        lviSpeaker.setAdapter(adapter);*/

        loadData();
    }

    private void loadData() {

        dataSpeaker= new ArrayList<SpeakerEntity>();
        queue = Volley.newRequestQueue(getActivity());

        String url = getString(R.string.url_speaker_get);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Speaker", response.toString());
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        SpeakerResponseEntity objects = gson.fromJson(response.toString(), SpeakerResponseEntity.class);

                        dataSpeaker= objects.getResults();
                        populateSpeaker();

                        rlayLoading.setVisibility(View.GONE);

                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Speaker", "Error: " + error.getMessage());
                // hide the progress dialog

                rlayLoading.setVisibility(View.GONE);

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Parse-Application-Id", "tNqFLFOkpUgFplAxTtpgqVvA7d403iam34asQ4RY");
                params.put("X-Parse-REST-API-Key", "9ntv9ynsj0QEal8oF2KT6tqV4StjtGK3fcq13QPL");

                return params;
            }
        };
        queue.add(jsonObjReq);
        rlayLoading.setVisibility(View.VISIBLE);
    }

    private void populateSpeaker()
    {
        SpeakerAdapter adapter = new SpeakerAdapter(getActivity(), R.layout.row_speaker, dataSpeaker);
        lviSpeaker.setAdapter(adapter);
    }
}
