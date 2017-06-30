package com.wtcrmandroid.adapter.recycleview;

import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/27.
 */

public class PhotoChooseAdapter extends RecyclerView.Adapter<PhotoChooseAdapter.ViewHolder> {

    private List<String> list;
    private int size;

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_choose, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == size) {
            holder.ivPhoto.setImageResource(R.mipmap.ic_photo_upload);
            holder.ivDelete.setVisibility(View.GONE);
            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String status2 = Environment.getExternalStorageState();
                    // 判断SD卡
                    if (status2 != null && status2.equals(Environment.MEDIA_MOUNTED)) {

//                        Intent openCameraIntent = new Intent(
//                                MediaStore.ACTION_IMAGE_CAPTURE);
//                        openCameraIntent.addCategory(Intent.CATEGORY_DEFAULT);
//                        SimpleDateFormat sDateFormat = new SimpleDateFormat(
//                                "yyyyMMddHHmmssSSS");
//                        String date = sDateFormat.format(new java.util.Date());
//                        //营业执照
//                        headImg = WTUserManager.INSTANCE.getCurrentUser()
//                                + "headimg_" + date + ".png";
//                        headImgFilePath = Const.PHOTO_PATH + headImg;
//                        file_path = Const.PHOTO_PATH + headImg;
//                        File fileimage = new File(file_path);
//                        Uri tempUri = Uri.fromFile(fileimage);
//                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//                        startActivityForResult(openCameraIntent, REQUEST_CAMERA);
                    }
                }
            });

        } else {
            holder.ivPhoto.setImageResource(R.mipmap.ic_dog);
            holder.ivDelete.setVisibility(View.VISIBLE);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        size = list.size();
        if (size < 5)
            return size + 1;
        else return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        SquareImageView ivPhoto;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
