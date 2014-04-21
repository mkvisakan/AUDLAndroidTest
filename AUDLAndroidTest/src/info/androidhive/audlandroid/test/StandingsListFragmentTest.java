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
		String response = "[[\"Western\", [\"Vancouver Riptide\", 6226234774126592, 3, 0, 29], [\"San Jose Spiders\", 6597766625099776, 2, 0, 23], [\"San Francisco FlameThrowers\", 5094953273262080, 1, 1, 19], [\"Seattle Raptors\", 5648334039547904, 0, 1, -5], [\"Salt Lake Lions\", 5716606839685120, 0, 4, -66]]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<ArrayList<TeamRecordItem>> leagueRecords = mFragment.parseJSON(jsonResult);
		assertEquals("Number of Divisions",1,leagueRecords.size());
		assertEquals("Number of Teams in Western Division",5,leagueRecords.get(0).size());
		assertTrue("First recordItem in Westerm Division",leagueRecords.get(0).get(0).equals(new TeamRecordItem("Vancouver Riptide","6226234774126592","3","0","29")));
		assertTrue("Second recordItem in Western Division",leagueRecords.get(0).get(1).equals(new TeamRecordItem("San Jose Spiders","6597766625099776","2","0","23")));
		assertTrue("First recordItem in Western Division",leagueRecords.get(0).get(2).equals(new TeamRecordItem("San Francisco FlameThrowers","5094953273262080","1","1","19")));
		assertTrue("Second recordItem in Western Division",leagueRecords.get(0).get(3).equals(new TeamRecordItem("Seattle Raptors","5648334039547904","0","1","15")));
		
		
	}
}
