package com.chad.bettr;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RequestQueue mQueue;
    private Adapter adapter;
    private RecyclerView rv;
    private LinearLayoutManager manager;
    private FloatingActionButton fab;
    private ArrayList<Result> issueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);


        int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        if (actionBarTitleId > 0) {
            TextView title = (TextView) findViewById(actionBarTitleId);
            if (title != null) {
                title.setTextColor(Color.WHITE);
            }
        }


//        issueList.add(new Issues("Titolo", "descrizione"));
//        issueList.add(new Issues("Titolo2", "descrizione2"));
//        issueList.add(new Issues("Titolo3", "descrizione3"));
//        issueList.add(new Issues("Titolo4", "descrizione4"));
        adapter = new Adapter(this, issueList);
        rv = findViewById(R.id.recyclerview);
        manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);
        adapter.notifyDataSetChanged();


        fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGroupDialog cdd = new AddGroupDialog(MainActivity.this);
                cdd.show();
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                cdd.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            }
        });


        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            private final SwipeAction deleteSwipeAction = new SwipeAction(SwipeAction.Dir.LEFT, "Upvote", getApplicationContext());
            private final SwipeAction favoriteSwipeAction = new SwipeAction(SwipeAction.Dir.RIGHT, "Downvote", getApplicationContext());

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == 4) { //DELETE

                } else if (direction == 8) { //EDIT

                }
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;
                if (dX < 0) {
                    deleteSwipeAction.draw(c, itemView, dX);
                } else {
                    favoriteSwipeAction.draw(c, itemView, dX);
                }


                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);

        newRequest();

    }

    private void sendRequest(){
        HashMap data = new HashMap();
        data.put("email","email");
        data.put("password","password");
        String url = "";

        RequestQueue requstQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.toString());
                    }
                }
        ){
            //here I want to post data to sever
        };
        requstQueue.add(jsonobj);
    }

    private void newRequest() {
        final Gson gson = new Gson();
        String url = "http://90.147.184.104:8000/issues/findAll";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response.toString());
                Type listType = new TypeToken<Pojo>() {}.getType();
                Pojo issues = gson.fromJson(response.toString(), listType);
                Log.d("TAG", issues.toString());
                issues.getResult();
                issueList.addAll(issues.getResult());
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.toString());
            }
        });
        request.setTag("tag");
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        mQueue.add(request);
    }

}
