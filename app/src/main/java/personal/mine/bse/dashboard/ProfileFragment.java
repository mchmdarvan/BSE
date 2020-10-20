 package personal.mine.bse.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
import personal.mine.bse.R;

import static android.view.View.GONE;

public class ProfileFragment extends Fragment {


    TextView txtName, txtEmail;
    CircleImageView imgProfile;
    ImageView ivLogo;
    Button btnSignout;
    LoginButton loginButton;

    CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtEmail = view.findViewById(R.id.tvEmail);
        txtName = view.findViewById(R.id.tvName);
        imgProfile = view.findViewById(R.id.imgAvatar);
        ivLogo = view.findViewById(R.id.imgLogo);
        loginButton = view.findViewById(R.id.btnLogin);
        btnSignout = view.findViewById(R.id.btnSignOut);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginButton.setFragment(this);
        checkLoginStatus();

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessToken.setCurrentAccessToken(null);
                btnSignout.setVisibility(View.INVISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                ivLogo.setVisibility(View.VISIBLE);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtName.setVisibility(View.VISIBLE);
                txtEmail.setVisibility(View.VISIBLE);
                imgProfile.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.INVISIBLE);
                btnSignout.setVisibility(View.VISIBLE);
                ivLogo.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                txtEmail.setText("");
                txtName.setText("");
                imgProfile.setImageResource(0);
                Toast.makeText(getActivity(), "User Logged Out", Toast.LENGTH_SHORT).show();
            } else {
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");

                    String imgUrl = "https://graph.facebook.com/"+id+"/picture?type=normal";

                    txtEmail.setText(email);
                    txtName.setText(first_name + last_name);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(getActivity())
                            .load(imgUrl)
                            .into(imgProfile);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void checkLoginStatus(){
        if (AccessToken.getCurrentAccessToken() != null){
            txtName.setVisibility(View.VISIBLE);
            txtEmail.setVisibility(View.VISIBLE);
            imgProfile.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
            btnSignout.setVisibility(View.VISIBLE);
            loadUserProfile(AccessToken.getCurrentAccessToken());
        } else if(AccessToken.getCurrentAccessToken() == null){
            ivLogo.setVisibility(View.VISIBLE);
            txtEmail.setVisibility(View.INVISIBLE);
            txtName.setVisibility(View.INVISIBLE);
            imgProfile.setVisibility(View.INVISIBLE);
        }
    }
}
