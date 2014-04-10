package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.ScoresListFragment;
import info.androidhive.audlandroid.model.ScoreListItem;

public class ScoresListFragmentTest extends android.test.ActivityInstrumentationTestCase2<MainActivity>{
	
	private FragmentManager mManager;
	private ScoresListFragment mFragment;
	private MainActivity activity;
	
	public ScoresListFragmentTest() {
		super(MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
		
		mFragment = new ScoresListFragment();
		mManager = activity.getSupportFragmentManager();
		mManager.beginTransaction().add(android.R.id.content, mFragment,"tag").commit();
		getInstrumentation().waitForIdleSync();
		
		mFragment = (ScoresListFragment) mManager.findFragmentByTag("tag");
	}
	
	
	public void testAvailability() throws Exception{
		assertNotNull(mFragment);
	}
	
	public void testParseJson() throws Exception{
		String response = "[[\"Western\", [[\"San Francisco FlameThrowers\", 5094953273262080, \"San Jose Spiders\", 6597766625099776, \"4/12/14\", \"7:00 PM PST\", \"0-0\"], [\"Vancouver Riptide\", 6226234774126592, \"Salt Lake Lions\", 5716606839685120, \"4/12/14\", \"7:00 PM MST\", \"0-0\"]]]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<ArrayList<ScoreListItem>> scoreList = mFragment.parseJSON(jsonResult);
		try {
			synchronized (this) {
				wait(3000);
			}
		} catch(InterruptedException ex){	
		}
		Log.i("Test","Nirav" + scoreList.toString());
		assertEquals("Number of divisions",1,scoreList.size());
		assertEquals("Number of Scores in Western division",scoreList.get(0).size(),2);
		assertTrue(scoreList.get(0).get(0).equals(new ScoreListItem("San Francisco FlameThrowers","5094953273262080","San Jose Spiders","6597766625099776","4/12/14","7:00 PM PST","0-0")));
	}
}
