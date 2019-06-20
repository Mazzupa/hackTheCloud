package com.chad.bettr;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class AddGroupDialog extends Dialog {


//    private static final ArrayList<InstagramUser> selectedUsers = new ArrayList<InstagramUser>();
//    private int id;
//    private boolean update;
//    private List<InstagramUser> preselected;
//    private String title;
//    private final View viewSnack;
//    private InstagramUserAdapter instagramUserAdapter;
//    private LinearLayoutManager firstManager;
//    private RecyclerView recyclerView;
//    private static final ArrayList<InstagramUser> instagramUsers = new ArrayList<InstagramUser>();
//    private static ChipGroup chipview;
//    private EditText inputTitle;
//    private TextView userCount;
//    //private TextInputLayout input1;
//    private AppDatabase db;
//    private static final Set<InstagramUser> notDuplicates = new HashSet<>();
//    private EditText searchEditText;
//    //private TextInputLayout input2;
//    private RequestQueue mQueue;
//    private AppDatabase userDb;


    public AddGroupDialog(Context context) {
        super(context);
//        this.viewSnack = viewSnack;
    }

    public AddGroupDialog(Context context, View viewSnack, String title, int id, List<Issues> users) {
        super(context);
//        this.id = id;
//        this.title = title;
//        this.preselected = users;
//        this.viewSnack = viewSnack;
//        this.update = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_issue_dialog);

        Button button = findViewById(R.id.buttonDone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//        db = Room.databaseBuilder(getContext(), AppDatabase.class, "InstagramPlaylists")
//                .allowMainThreadQueries()
//                .build();
//        mQueue = Volley.newRequestQueue(getContext());
//        instagramUserAdapter = new InstagramUserAdapter(getContext(), instagramUsers);
//        recyclerView = findViewById(R.id.resutlRecyclerview);
//        chipview = findViewById(R.id.chipGroup);
//        selectedUsers.clear();
//        if (update)
//            for (InstagramUser user : preselected) {
//                Chip chip = new Chip(getContext());
//                chip.setChipText(user.getUsername());
//                chip.setClosable(true);
//                chip.setOnCloseClickListener(new OnCloseClickListener() {
//                    @Override
//                    public void onCloseClick(View v) {
//                        Log.i("CHIP", chip.getChipText());
//
//                        removechip(chip);
//                    }
//                });
//                chip.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                addchip(chip, user);
//            }
//        //input2 = findViewById(R.id.input2);
//
//        searchEditText = findViewById(R.id.searchCreators);
//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //input2.setErrorEnabled(false);
//                if (s.length() > 0)
//                    newRequest(s.toString());
//                else {
//                    instagramUsers.clear();
//                    instagramUsers.addAll(notDuplicates);
//                    Collections.sort(instagramUsers, new InstagramUser.NameComparator());
//                    instagramUserAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        firstManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        recyclerView.setAdapter(instagramUserAdapter);
//        recyclerView.setLayoutManager(firstManager);
//        inputTitle = findViewById(R.id.newPlaylisttitle);
//        inputTitle.setText(title);
//        inputTitle.addTextChangedListener(new ErrorWatchers());
//        //input1 = findViewById(R.id.input1);
//        userCount = findViewById(R.id.userCount);
//
//        userDb = Room.databaseBuilder(getContext(), AppDatabase.class, "InstagramUsers")
//                .build();
//
//
//        new Thread(() -> {
//            instagramUsers.clear();
//            instagramUsers.addAll(userDb.instagramUserDao().getAll());
//            notDuplicates.addAll(instagramUsers);
//            Collections.sort(instagramUsers, new InstagramUser.NameComparator());
////            userCount.setText("User Count: " + instagramUsers.size());
//            //if (instagramUsers.size() == 0)
//            usersRequest(userDb);
//        }).start();
//
//
//        findViewById(R.id.buttonDone).setOnClickListener(v -> {
//
//            if (!inputTitle.getText().toString().equals("") && selectedUsers.size() > 0) {
//
//                if (update)
//                    db.instagramGroupDao().update(id, inputTitle.getText().toString(), selectedUsers);
//                else
//                    db.instagramGroupDao().insert(new InstagramGroup(inputTitle.getText().toString(), selectedUsers));
//
//                db.close();
//
//                GroupFragment.updateView(getContext());
//
//
//                dismiss();
//                db.close();
//                mQueue.cancelAll("allusers");
//                if (update)
//                    Snackbar.make(viewSnack, inputTitle.getText().toString() + " Updated", Snackbar.LENGTH_SHORT).show();
//                else
//                    Snackbar.make(viewSnack, inputTitle.getText().toString() + " Added to the list", Snackbar.LENGTH_SHORT).show();
//            } else {
//                if (inputTitle.getText().toString().equals("")) {
//                    TextInputLayout input1 = findViewById(R.id.input1);
//                    input1.setErrorEnabled(true);
//                    input1.setError("Empty title");
//                }
//                if (instagramUsers.size() == 0) {
//                    TextInputLayout input2 = findViewById(R.id.input2);
//                    input2.setErrorEnabled(true);
//                    input2.setError("Empty title");
//                }
//            }
//        });

    }

    @Override
    protected void onStop() {
        super.onStop();
//        db.close();
//        userDb.close();
//        mQueue.cancelAll("allusers");
    }

//    public static void addchip(Chip chip, InstagramUser user) {
//        chipview.addView(chip);
//        selectedUsers.add(user);
//    }
//
//    public static void removechip(Chip chip) {
//        Iterator<InstagramUser> iter = selectedUsers.iterator();
//        while (iter.hasNext()) {
//            InstagramUser user = iter.next();
//            if (user.getUsername().equals(chip.getChipText())) {
//                selectedUsers.remove(user);
//                break;
//            }
//        }
//        chipview.removeView(chip);
//    }

//
//    private void usersRequest(AppDatabase userDb) {
//        mQueue = Volley.newRequestQueue(getContext());
//        final Gson gson = new Gson();
//        String url = "https://i.instagram.com/api/v1/feed/reels_tray/";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //Log.i("ugnma", response.toString());
//                Type listType = new TypeToken<Instagram>() {
//                }.getType();
//                Instagram reels_tray = gson.fromJson(response.toString(), listType);
//                for (Tray tray : reels_tray.getTray()) {
//                    notDuplicates.add(new InstagramUser(tray.getUser().getPk(), tray.getUser().getUsername(), tray.getUser().getFullName(), tray.getUser().getProfilePicUrl(), false));
//                }
//                instagramUsers.clear();
//                instagramUsers.addAll(notDuplicates);
//                Collections.sort(instagramUsers, new InstagramUser.NameComparator());
////                userCount.setText("User Count: " + instagramUsers.size());
//                new Thread(() -> {
//                    userDb.instagramUserDao().insertAll(instagramUsers);
//                    userDb.close();
//                }).start();
//                instagramUserAdapter.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("ugnma", "" + error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("User-Agent", "Instagram 10.26.0 (iPhone7,2; iOS 10_1_1; en_US; en-US; scale=2.00; gamut=normal; 750x1334) AppleWebKit/420+");
//                headers.put("Cookie", MainActivity.getCookies());
//                return headers;
//            }
//        };
//        request.setTag("allusers");
//        mQueue.add(request);
//    }
//
//    private void newRequest(String searchQuery) {
//        final Gson gson = new Gson();
//        String url = "https://i.instagram.com/api/v1/users/search/?&q=" + searchQuery;
//        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //Log.i("ugnma", response.toString());
//                try {
//                    JSONArray object = response.getJSONArray("users");
//                    Type listType = new TypeToken<List<User>>() {
//                    }.getType();
//                    List<User> reels_tray = gson.fromJson(object.toString(), listType);
//                    instagramUsers.clear();
//                    for (User tray : reels_tray) {
//                        instagramUsers.add(new InstagramUser(tray.getPk(), tray.getUsername(), tray.getFullName(), tray.getProfilePicUrl(), false));
//                    }
//                    instagramUserAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("ugnma", "" + error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("User-Agent", "Instagram 10.26.0 (iPhone7,2; iOS 10_1_1; en_US; en-US; scale=2.00; gamut=normal; 750x1334) AppleWebKit/420+");
//                headers.put("Cookie", MainActivity.getCookies());
//                return headers;
//            }
//        };
//        mQueue.add(request2);
//    }


//    private class ErrorWatchers implements TextWatcher {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            //input1.setErrorEnabled(false);
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    }
}