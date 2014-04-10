package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.VideosListFragment;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.VideosListItem;

public class VideosListFragmentTest extends android.test.AndroidTestCase{
	private VideosListFragment vidFrag;
	
	public VideosListFragmentTest() {
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        vidFrag = new VideosListFragment();
    }

	public void testAvailability() throws Exception {
        assertNotNull(vidFrag);
    }
	
	public void testParseJson() throws Exception {
		String response = "[[\"Vancouver Riptide Combine with Mark Lloyd\", \"http://www.youtube.com/watch?v=VgQzDDJf8Ug&feature=youtube_gdata\", \"http://i.ytimg.com/vi/VgQzDDJf8Ug/0.jpg\"], [\"Wildfire Combine Promo\", \"http://www.youtube.com/watch?v=hFdZVOWHGCI&feature=youtube_gdata\", \"http://i.ytimg.com/vi/hFdZVOWHGCI/0.jpg\"]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<VideosListItem> vidList = vidFrag.parseJSON(jsonResult);
		assertEquals("Testing the parse json of VideosListFragment", 2, vidList.size());
		assertEquals("Testing the parsing of video header", "Vancouver Riptide Combine with Mark Lloyd", vidList.get(0).getVideoName());
		assertEquals("Testing the parsing of video url", "http://www.youtube.com/watch?v=VgQzDDJf8Ug&feature=youtube_gdata", vidList.get(0).getVideoURL());
		assertEquals("Testing the parsing of video thumbnail", "http://i.ytimg.com/vi/VgQzDDJf8Ug/0.jpg", vidList.get(0).getVideoThumnbnail());
	}
}
