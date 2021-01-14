package com.example.bakingapp.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Step;

import java.util.List;

public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepRecyclerViewAdapter.StepsViewHolder>{
    private List<Step> steps;
    private Context mContext;

    public StepRecyclerViewAdapter(List<Step> steps, Context mContext) {
        this.steps = steps;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_steps, parent, false);
        StepRecyclerViewAdapter.StepsViewHolder viewHolder = new StepRecyclerViewAdapter.StepsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        holder.stepDetails.setText(steps.get(position).getDescription());
        if (steps.get(position).getVideoURL().isEmpty()){
            holder.stepVideo.setVisibility(View.GONE);
        }else{
            setVideo(holder.stepVideo,steps.get(position).getVideoURL());
        }
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class StepsViewHolder extends RecyclerView.ViewHolder{

        TextView stepDetails;
        VideoView stepVideo;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);

            stepDetails = itemView.findViewById(R.id.tv_step_details);
            stepVideo = itemView.findViewById(R.id.vv_stepvideo);

        }
    }

    private void setVideo(VideoView videoView,String videoURL) {
        Uri uri = Uri.parse(videoURL);
        MediaController mediaController = new MediaController(videoView.getContext());
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
            }
        });
    }
}
