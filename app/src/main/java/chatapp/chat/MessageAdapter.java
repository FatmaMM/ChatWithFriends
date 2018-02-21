package chatapp.chat;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android-2 on 2/12/2018.
 */

 class MessageAdapter extends ArrayAdapter<MessageObject> {
    @BindView(R.id.photoImageView)
    ImageView imageView;
    @BindView(R.id.messageTextView)
    TextView textView;
    @BindView(R.id.nameTextView)
    TextView nameTextView;

    public MessageAdapter(@NonNull Context context, int resource, @NonNull List<MessageObject> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }
        ButterKnife.bind(this, convertView);
        MessageObject messageObject = getItem(position);
        boolean isPhoto = messageObject.getPhotoUrl() != null;
        if (isPhoto) {
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext())
                    .load(messageObject.getPhotoUrl())
                    .into(imageView);
        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            textView.setText(messageObject.getText());
        }
        nameTextView.setText(messageObject.getName());
        return convertView;
    }
}
