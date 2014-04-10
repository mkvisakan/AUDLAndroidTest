package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.TeamsListFragment;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.TeamsListItem;

public class TeamsListFragmentTest extends android.test.ActivityInstrumentationTestCase2<MainActivity>{
	private FragmentManager mFragmentManager;
	private TeamsListFragment teamsFrag;
	private MainActivity activity;
	
	public TeamsListFragmentTest() {
		super(MainActivity.class);
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
        mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.beginTransaction()
         .add(android.R.id.content, new TeamsListFragment(), "tag")
         .commit();
        getInstrumentation().waitForIdleSync();

        teamsFrag = (TeamsListFragment) mFragmentManager.findFragmentByTag("tag");
    }

	public void testAvailability() throws Exception {
        assertNotNull(teamsFrag);
    }
	
	public void testParseJson() throws Exception {
		String response = "[[\"Madison Radicals\", 224002], [\"New York Empire\", 208003]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<TeamsListItem> teamsList = teamsFrag.parseJSON(jsonResult);
		try {
			synchronized (this) {
				wait(3000);
			}
		} catch(InterruptedException ex){	
		}
		assertEquals("Testing the parse json of TeamsListFragment", 2, teamsList.size());
		assertEquals("Testing the parsing of team name", "Madison Radicals", teamsList.get(0).getTeamName());
		assertEquals("Testing the parsing of team id", "224002", teamsList.get(0).getTeamId());
	}
}
