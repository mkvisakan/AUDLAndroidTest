package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.StandingsListFragment;
import info.androidhive.audlandroid.model.TeamRecordItem;
import android.support.v4.app.FragmentManager;

public class StandingsListFragmentTest extends android.test.AndroidTestCase{
	private StandingsListFragment mFragment;
	
	public StandingsListFragmentTest() {
		
	}
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mFragment = new StandingsListFragment();
	}
	
	
	public void testAvailability() throws Exception {
		assertNotNull(mFragment);
	}
	
	public void testParseJson() throws Exception {
		String response = "[[\"Midwest Division\", [\"Madison Radicals\", 10, 3], [\"Chicago Wildfire\", 9, 4]], [\"Eastern Division\", [\"DC Breeze\", 10, 3], [\"New York Empire\", 9, 4]]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<ArrayList<TeamRecordItem>> leagueRecords = mFragment.parseJSON(jsonResult);
		assertEquals("Number of Divisions",2,leagueRecords.size());
		assertEquals("Number of Teams in Midwest Division",2,leagueRecords.get(0).size());
		assertEquals("Number of Teams in Eastern Division",2,leagueRecords.get(0).size());
		assertTrue("First recordItem in Midwest Division",leagueRecords.get(0).get(0).equals(new TeamRecordItem("Madison Radicals","10","3")));
		assertTrue("Second recordItem in Midwest Division",leagueRecords.get(0).get(1).equals(new TeamRecordItem("Chicago Wildfire","9","4")));
		assertTrue("First recordItem in Eastern Division",leagueRecords.get(1).get(0).equals(new TeamRecordItem("DC Breeze","10","3")));
		assertTrue("Second recordItem in Eastern Division",leagueRecords.get(1).get(1).equals(new TeamRecordItem("New York Empire","9","4")));
	}
}
