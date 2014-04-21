package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.ScoresListFragment;
import info.androidhive.audlandroid.model.ScoreListItem;

public class ScoresListFragmentTest extends android.test.AndroidTestCase{
	
	private ScoresListFragment mFragment;
	
	public ScoresListFragmentTest() {
		
	}
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mFragment = new ScoresListFragment();
	}
	
	
	public void testAvailability() throws Exception{
		assertNotNull(mFragment);
	}
	
	public void testParseJson() throws Exception{
		String response = "[[\"Western\",[[\"Salt Lake Lions\", 5716606839685120, \"San Jose Spiders\", 6597766625099776, \"4/20/14\", \"1:00 PM PST\", 18, 37, 2]]]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<ArrayList<ScoreListItem>> scoreList = mFragment.parseJSON(jsonResult);
		assertEquals("Number of divisions",1,scoreList.size());
		assertEquals("Number of Scores in Western division",scoreList.get(0).size(),2);
		assertTrue(scoreList.get(0).get(0).equals(new ScoreListItem("Salt Lake Lions","5094953273262080","San Jose Spiders","6597766625099776","4/20/14","1:00 PM PST","18","37","2")));
	}
}
