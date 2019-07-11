package tw.jason.homework.bean;

import java.util.ArrayList;
import java.util.List;


//type = 1 img one  or   type = 2 img many
public class AdapterImgBean extends AdapterTypeBean{

    private List<TweetsBean.ImagesBean> images = new ArrayList<>(0);


    public AdapterImgBean(List<TweetsBean.ImagesBean> images) {
        this(images,1);
    }

    public AdapterImgBean(List<TweetsBean.ImagesBean> images, int type) {
        this.images = images;
        this.type = type;
    }



    public void setImages(List<TweetsBean.ImagesBean> images) {
        this.images = images;
    }

    public List<TweetsBean.ImagesBean> getImages() {
        return images;
    }
}
