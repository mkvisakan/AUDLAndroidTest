package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.ScheduleDivisionFragment;
import info.androidhive.audlandroid.ScheduleListFragment;
import info.androidhive.audlandroid.TeamsListFragment;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.ScheduleListItem;
import info.androidhive.audlandroid.model.TeamsListItem;

public class ScheduleDivisionFragmentTest extends android.test.ActivityInstrumentationTestCase2<MainActivity>{
	private FragmentManager mFragmentManager;
	private ScheduleDivisionFragment schedFrag;
	private MainActivity activity;
	
	public ScheduleDivisionFragmentTest() {
		super(MainActivity.class);
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
        
        schedFrag = new ScheduleDivisionFragment();
        Bundle midwesternArgs = new Bundle();
    	midwesternArgs.putString("DIVISION_NAME", "Midwestern");
    	schedFrag.setArguments(midwesternArgs);
    	
        mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.beginTransaction()
         .add(android.R.id.content, schedFrag, "tag")
         .commit();
        getInstrumentation().waitForIdleSync();

        schedFrag = (ScheduleDivisionFragment) mFragmentManager.findFragmentByTag("tag");
    }

	public void testAvailability() throws Exception {
        assertNotNull(schedFrag);
    }
	
	public void testParseJson() throws Exception {
		String response = "[[\"Midwestern\", [[\"Madison Radicals\", 224002, \"Indianapolis AlleyCats\", 253001, \"4/13/14\", \"3:30 PM EST\"], [\"Madison Radicals\", 224002, \"Cincinnati Revolution\", 183001, \"4/12/14\", \"7:30 PM EST\"]]], [\"Eastern\", [[\"Toronto Rush\", 195002, \"New York Empire\", 208003, \"4/13/14\", \"12:00 PM EST\"]]]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<ScheduleListItem> schedItems1 = schedFrag.parseJSON(jsonResult, "Midwestern");
		assertEquals("Division 1 : Testing number of items parsed", schedItems1.size(), 2);
		assertEquals("Division 1 : Testing parsing of home team name", "Madison Radicals", schedItems1.get(0).getHomeTeam());
		assertEquals("Division 1 : Testing parsing of home team id", "224002", schedItems1.get(0).getHomeTeamID());
		assertEquals("Division 1 : Testing parsing of away team name", "Indianapolis AlleyCats", schedItems1.get(0).getAwayTeam());
		assertEquals("Division 1 : Testing parsing of away team id", "253001", schedItems1.get(0).getAwayTeamID());
		assertEquals("Division 1 : Testing parsing of date", "4/13/14", schedItems1.get(0).getDate());
		assertEquals("Division 1 : Testing parsing of date", "3:30 PM EST", schedItems1.get(0).getTime());
		ArrayList<ScheduleListItem> schedItems2 = schedFrag.parseJSON(jsonResult, "Eastern");
		assertEquals("Division 2 : Testing number of items parsed", schedItems2.size(), 1);
		assertEquals("Division 2 : Testing parsing of home team name",  "Toronto Rush", schedItems2.get(0).getHomeTeam());
		assertEquals("Division 2 : Testing parsing of home team id", "195002", schedItems2.get(0).getHomeTeamID());
		assertEquals("Division 2 : Testing parsing of away team name", "New York Empire", schedItems2.get(0).getAwayTeam());
		assertEquals("Division 2 : Testing parsing of away team id", "208003", schedItems2.get(0).getAwayTeamID());
		assertEquals("Division 2 : Testing parsing of date", "4/13/14", schedItems2.get(0).getDate());
		assertEquals("Division 2 : Testing parsing of date", "12:00 PM EST", schedItems2.get(0).getTime());
	}
}
