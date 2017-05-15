package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;
import co.edu.udea.compumovil.gr09_20171.lab4.R;

import static android.app.Activity.RESULT_OK;

public class CreateEvent extends Fragment implements View.OnClickListener {
    final private static String EVENTS_REFERENCE = "Events";
    final private static int PHOTO_SELECTED = 2;
    private ImageView eventphoto;
    private EditText titlev, fechav, locationv, information;
    private RatingBar score;
    private Button create;
    private Uri selectedImage;
    private String Uid;
    private Bitmap img;
    private Fragment eventView;

    public CreateEvent() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_event, container, false);
        eventphoto = (ImageView) view.findViewById(R.id.CEImagen);
        titlev = (EditText) view.findViewById(R.id.CETitle);
        fechav = (EditText) view.findViewById(R.id.CEFecha);
        locationv = (EditText) view.findViewById(R.id.CELocation);
        information = (EditText) view.findViewById(R.id.CEInfo);
        score = (RatingBar) view.findViewById(R.id.CEScore);
        create = (Button) view.findViewById(R.id.CEBtnPost);
        fechav.setOnClickListener(this);
        eventphoto.setOnClickListener(this);
        create.setOnClickListener(this);
        return view;

        /**/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CEFecha:
                break;
            case R.id.CEImagen:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                startActivityForResult(intent, PHOTO_SELECTED);
                break;
            case R.id.CEBtnPost:
                subirImagen(UUID.randomUUID().toString());
               /* CrearEvento(titlev.getText().toString(),
                        fechav.getText().toString(),
                        locationv.getText().toString(),
                        information.getText().toString(),
                        UUID.randomUUID().toString());
                       */ //"https://firebasestorage.googleapis.com/v0/b/lab4fcm-fc89d.appspot.com/o/ImgEvents%2FDEFAULT.jpg?alt=media&token=81462715-1cec-4f59-b257-428f32ab72e1");
                break;
        }
    }

    private void CrearEvento(final String title, final String fecha, final String location, final String info, final String photo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference eventReference = database.getReference(EVENTS_REFERENCE);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        PostEvent event = new PostEvent(user.getUid(), user.getDisplayName(), title, fecha, location, info, photo);

        boolean result = eventReference.push().setValue(event).isComplete();
        toEventView();
        if (result) {
            Toast.makeText(this.getContext(), "Evento creado", Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressWarnings("VisibleForTests")
    private void subirImagen(final String child) {
        if (isNetDisponible()) {
            if (selectedImage != null) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReferenceFromUrl("gs://lab4fcm-fc89d.appspot.com/");
                StorageReference upImg = storageRef.child("ImgEvents/" + child + ".jpg");
                UploadTask uploadTask = upImg.putFile(selectedImage);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        CrearEvento(titlev.getText().toString(),
                                fechav.getText().toString(),
                                locationv.getText().toString(),
                                information.getText().toString(),
                                taskSnapshot.getDownloadUrl().toString());
                    }
                });
            } else {
                CrearEvento(titlev.getText().toString(),
                        fechav.getText().toString(),
                        locationv.getText().toString(),
                        information.getText().toString(),
                        "https://firebasestorage.googleapis.com/v0/b/lab4fcm-fc89d.appspot.com/o/ImgEvents%2FDEFAULT.jpg?alt=media&token=81462715-1cec-4f59-b257-428f32ab72e1");
            }
        } else {
            Toast.makeText(this.getContext(), "Sin internet", Toast.LENGTH_SHORT).show();
            toEventView();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_SELECTED) {
            if (resultCode == RESULT_OK) {

                selectedImage = data.getData();
                Glide.with(this).load(selectedImage).into(eventphoto);


            }
        }
    }

    private boolean isNetDisponible() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    private void toEventView() {
        FragmentManager fragmentManager = getFragmentManager();
        eventView = new EventsViewActivity();
        fragmentManager.beginTransaction().replace(R.id.fragment_content, eventView).commit();
    }
}