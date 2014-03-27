package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.ScheduleDivisionFragment;
import info.androidhive.audlandroid.ScheduleListFragment;
import info.androidhive.audlandroid.StatFragment;
import info.androidhive.audlandroid.TeamsListFragment;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.ScheduleListItem;
import info.androidhive.audlandroid.model.StatsListItem;
import info.androidhive.audlandroid.model.TeamsListItem;

public class StatFragmentTest extends android.test.ActivityInstrumentationTestCase2<MainActivity>{
	private FragmentManager mFragmentManager;
	private StatFragment statFrag;
	private MainActivity activity;
	
	public StatFragmentTest() {
		super(MainActivity.class);
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
        
        statFrag = new StatFragment();
        Bundle statArgs = new Bundle();
    	statArgs.putString("STAT_NAME", "Goals");
    	statFrag.setArguments(statArgs);
    	
        mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.beginTransaction()
         .add(android.R.id.content, statFrag, "tag")
         .commit();
        getInstrumentation().waitForIdleSync();

        statFrag = (StatFragment) mFragmentManager.findFragmentByTag("tag");
    }

	public void testAvailability() throws Exception {
        assertNotNull(statFrag);
    }
	
	public void testParseJson() throws Exception {
		String response = "{\"Throwaways\": [[\"David B\", 74, 208004], [\"Daniel S\", 62, 206001]], \"Assists\": [[\"David B\", 69, 208004], [\"Brodie S\", 63, 207003]], \"Goals\": [[\"Aj N\", 83, 207003]], \"PMC\": [[\"Aj N\", 118, 207003], [\"Matt E\", 78, 208004], [\"Scott R\", 75, 224002]], \"Drops\": [[\"Anonymous\", 37, 210001]], \"Ds\": [[\"Matt E\", 34, 208004]]}";
		JSONObject jsonResult = new JSONObject(response);
		ArrayList<StatsListItem> statItems1 = statFrag.parseJSON(jsonResult, "Throwaways");
		assertEquals("Throwaways : number of items", statItems1.size(), 2);
		assertEquals("Throwaways : ", statItems1.get(0).getPlayerName(), "David B");
		assertEquals("Throwaways : ", statItems1.get(0).getPlayerTeamID(), "208004");
		assertEquals("Throwaways : ", statItems1.get(0).getStatType(), "Throwaways");
		assertEquals("Throwaways : ", statItems1.get(0).getStatValue(), "74");
		ArrayList<StatsListItem> statItems2 = statFrag.parseJSON(jsonResult, "Assists");
		assertEquals("Assists : number of items", 2, statItems2.size());
		ArrayList<StatsListItem> statItems3 = statFrag.parseJSON(jsonResult, "Goals");
		assertEquals("Goals : number of items", 1, statItems3.size());
		ArrayList<StatsListItem> statItems4 = statFrag.parseJSON(jsonResult, "PMC");
		assertEquals("PMC : number of items", 3, statItems4.size());
		ArrayList<StatsListItem> statItems5 = statFrag.parseJSON(jsonResult, "Ds");
		assertEquals("Ds : number of items", 1, statItems5.size());
		ArrayList<StatsListItem> statItems6 = statFrag.parseJSON(jsonResult, "Drops");
		assertEquals("Drops : number of items", 1, statItems6.size());
	}
}
