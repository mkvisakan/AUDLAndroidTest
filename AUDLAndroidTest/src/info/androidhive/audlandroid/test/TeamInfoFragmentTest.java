package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.TeamsInfoFragment;
import info.androidhive.audlandroid.VideosListFragment;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.model.VideosListItem;

public class TeamInfoFragmentTest extends android.test.AndroidTestCase{
	private TeamsInfoFragment teamFrag;
	
	public TeamInfoFragmentTest() {
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        teamFrag = new TeamsInfoFragment();
    }
	

	public void testAvailability() throws Exception {
        assertNotNull(teamFrag);
    }
	
	public void testParseJson() throws Exception {
		String response = "[[[\"Madison\", \"Radicals\", 224002], [\"Benjy K\", \"1\"], [\"Andrew M\", \"2\"]], [\"Madison Radicals\", 224002, [\"4/12/14\", \"7:30 PM EST\", \"Cincinnati Revolution\", 183001], [\"4/13/14\", \"3:30 PM EST\", \"Indianapolis AlleyCats\", 253001], [\"4/19/14\", \"12:00 AM EST\", \"Detroit Mechanix\", 219001]], [[\"Madison\", \"Radicals\", 224002], [\"Goals\", [[\"Scott R\", 42], [\"Nate T\", 36], [\"Pat S\", 36]]], [\"Assists\", [[\"Animal\", 55]]], [\"Drops\", [[\"Dave W\", 8]]], [\"Throwaways\", [[\"Dave W\", 56]]], [\"PMC\", [[\"Scott R\", 75], [\"Pat S\", 61]]], [\"Ds\", [[\"Dave W\", 28]]]]]";
		JSONArray jsonResult = new JSONArray(response);
		TeamsListItem team = teamFrag.parseJSON(jsonResult, "Madison Radicals", "224002");
		assertEquals("Testing the parse json of TeamsInfoFragment", 2, team.getPlayerNames().size());
		assertEquals("Testing the parsing of team player names", "Benjy K", team.getPlayerNames().get(0));
		assertEquals("Testing the parsing of team player id", "1", team.getPlayerIds().get(0));
		assertEquals("Testing the parsing of team schedule", 3, team.getSchedDates().size());
		assertEquals("Testing the parsing of schedule team name", "Cincinnati Revolution", team.getSchedTeams().get(0));
		assertEquals("Testing the parsing of schedule team name", "183001", team.getSchedTeamIds().get(0));
		assertEquals("Testing the parsing of schedule date", "4/12/14", team.getSchedDates().get(0));
		assertEquals("Testing the parsing of schedule time", "7:30 PM EST", team.getSchedTimes().get(0));
		assertEquals("Testing the stat keys", 6, team.getStatsKeys().size());
		assertEquals("Testing parsing of stat types", "Dave W", team.getStatsList("Ds").get(0).getPlayerName());
		assertEquals("Testing parsing of stat types", "28", team.getStatsList("Ds").get(0).getStatValue());
	}
}
