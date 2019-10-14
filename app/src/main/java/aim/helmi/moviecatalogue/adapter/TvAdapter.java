package aim.helmi.moviecatalogue.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import aim.helmi.moviecatalogue.R;
import aim.helmi.moviecatalogue.model.TvModel;
import aim.helmi.moviecatalogue.ui.DetailTvShow;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MovieViewHolder> {

    private ArrayList<TvModel> mData = new ArrayList<>();
    public void setData(ArrayList<TvModel> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mData.get(i));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView gambar;
        private TextView judul;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.img_view);
            judul = itemView.findViewById(R.id.tv_judul);

            itemView.setOnClickListener(this);
        }

        public void bind(TvModel tvModel) {

            String url_image = "https://image.tmdb.org/t/p/w185" + tvModel.getPoster_path();

            judul.setText(tvModel.getName());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .into(gambar);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TvModel tvModel = mData.get(position);
//
            tvModel.setName(tvModel.getName());
            tvModel.setOverview(tvModel.getOverview());

            Intent i = new Intent(itemView.getContext(), DetailTvShow.class);
            i.putExtra(DetailTvShow.EXTRA_JOS, tvModel);
            itemView.getContext().startActivity(i);
        }
    }
}
