package dimaarts.ru.sampleapplication.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import dimaarts.ru.sampleapplication.R;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;

public class DetailsActivity extends AppCompatActivity {
    public static final String DETAIL_ANIMAL = "DETAIL_ANIMAL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        AnimalEntity animalEntity = (AnimalEntity) getIntent().getSerializableExtra(DETAIL_ANIMAL);
        TextView textView = findViewById(R.id.text1);
        textView.setText(animalEntity.getName());
        ImageView imageView = findViewById(R.id.icon);
        Picasso.get().load(animalEntity.getImageUrl()).into(imageView);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
