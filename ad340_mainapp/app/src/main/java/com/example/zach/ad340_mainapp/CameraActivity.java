package com.example.zach.ad340_mainapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CameraAdapter cameraAdapter;
    private ArrayList<Camera> cameraArrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.camera_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cameraArrayList = new ArrayList<>();
        cameraAdapter = new CameraAdapter(cameraArrayList);
        recyclerView.setAdapter(cameraAdapter);

        requestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON() {
        String URL = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Features");

                            for (int i = 1; i < jsonArray.length(); i++) {
                                JSONObject feature = jsonArray.getJSONObject(i);

                                JSONArray cameras = feature.getJSONArray("Cameras");
                                for (int j = 0; j < cameras.length(); j++) {
                                    JSONObject camera = cameras.getJSONObject(j);
                                    String type = camera.getString("Type");
                                    String imageURL = camera.getString("ImageUrl");
                                    String desc = camera.getString("Description");
                                    if (type.equals("sdot")) {
                                        imageURL = "http://www.seattle.gov/trafficcams/images/"
                                                + imageURL;
                                    } else {
                                        imageURL = "http://images.wsdot.wa.gov/nw/" + imageURL;
                                    }
                                    cameraArrayList.add(new Camera(imageURL, desc));
                                }
                            }
                            cameraAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(CameraActivity.this, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Toast.makeText(CameraActivity.this, "Network error. " +
                        "Please check your connection.\n"
                        + e.toString(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        });

        requestQueue.add(request);
    }

    public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.ViewHolder> {
        private ArrayList<Camera> cameraArray;

        Context context = getApplicationContext();

        public CameraAdapter(ArrayList<Camera> cameraArrayList) {
            this.cameraArray = cameraArrayList;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                textView  = view.findViewById(R.id.name_view);
                imageView = view.findViewById(R.id.image_view);
            }
        }

        @NonNull
        @Override
        public CameraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.camera_layout, parent,
                    false);

            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
            Camera current  = cameraArray.get(pos);
            String imageUrl = current.getImageURL();
            String desc     = current.getDesc();

            holder.textView.setText(desc);
            Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return cameraArrayList.size();
        }
    }
}
