package dimaarts.ru.sampleapplication.mvp.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import dimaarts.ru.sampleapplication.R;
import dimaarts.ru.sampleapplication.entities.AnimalEntity;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalsViewHolder> {
    private List<AnimalEntity> mDataset;
    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class AnimalsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public AnimalsViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.text1);
            mImageView = v.findViewById(R.id.icon);
        }
    }

    public void setData(List<AnimalEntity> myDataset) {
        mDataset = myDataset;
        this.notifyDataSetChanged();
    }

    public List<AnimalEntity> getData() {
        return mDataset;
    }

    public AnimalsAdapter(List<AnimalEntity> myDataset) {
        mDataset = myDataset;
    }

    public AnimalsAdapter() {
    }

    @NonNull
    @Override
    public AnimalsAdapter.AnimalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        v.setOnClickListener(onItemClickListener);
        return new AnimalsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalsViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getName());
        Picasso.get().load(mDataset.get(position).getImageUrl()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
