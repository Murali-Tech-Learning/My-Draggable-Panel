package nonuithreads;

import java.io.Serializable;

/**
 * Created by divum on 23/1/17.
 */

public interface IVideoListing extends Serializable {


    public String getUrl();

    public void setUrl(String url);

    public String getDescription();

    public void setDescription(String description);


    public String getImageurl();

    public void setImageurl(String imageurl);


}
