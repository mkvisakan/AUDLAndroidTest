package info.androidhive.audlandroid.test;

import android.os.Parcel;
import info.androidhive.audlandroid.adapter.NavDrawerListAdapter;
import info.androidhive.audlandroid.model.Authenticated;
import info.androidhive.audlandroid.model.NavDrawerItem;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.ScheduleListItem;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.model.StatsListItem;
import info.androidhive.audlandroid.model.TeamRecordItem;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.model.VideosListItem;

public class ModelsTest extends android.test.AndroidTestCase{
	
	public ModelsTest() {	
	}
	
	public void testAuthenticated() {
		Authenticated obj = new Authenticated();
		assertNotNull(obj);
	}
	
	public void testNavDrawerItem(){
		NavDrawerItem obj = new NavDrawerItem("title", 1, true, "2");
		assertNotNull(obj);
		assertEquals("title", obj.getTitle());
		assertEquals(1, obj.getIcon());
		assertEquals(true, obj.getCounterVisibility());
		assertEquals("2", obj.getCount());
		obj.setTitle("new title");
		obj.setIcon(2);
		obj.setCounterVisibility(false);
		obj.setCount("3");
		assertEquals("new title", obj.getTitle());
		assertEquals(2, obj.getIcon());
		assertEquals(false, obj.getCounterVisibility());
		assertEquals("3", obj.getCount());
	}
	
	public void testNewsListItem(){
		NewsListItem obj = new NewsListItem("headline", "URL", "datetime");
		assertNotNull(obj);
		assertEquals("headline", obj.getNewsHeadline());
		assertEquals("URL", obj.getNewsURL());
		assertEquals("datetime", obj.getDatetime());
	}
	
	public void testScheduleListItem(){
		ScheduleListItem obj = new ScheduleListItem("division", "homeTeam", "homeTeamID", "awayTeam", "awayTeamID", "date", "time");
		assertNotNull(obj);
		assertEquals("division", obj.getDivision());
		assertEquals("homeTeam", obj.getHomeTeam());
		assertEquals("homeTeamID", obj.getHomeTeamID());
		assertEquals("awayTeam", obj.getAwayTeam());
		assertEquals("awayTeamID", obj.getAwayTeamID());
		assertEquals("date", obj.getDate());
		assertEquals("time", obj.getTime());
	}
	
	public void testScoreListItem(){
		ScoreListItem obj = new ScoreListItem("homeTeam", "homeTeamID", "awayTeam", "awayTeamID", "date", "time", "homeTeamScore","awayTeamScore","status", null);
		ScoreListItem obj1 = new ScoreListItem("homeTeam1", "homeTeam1ID", "awayTeam1", "awayTeamID1", "date1", "time1", "homeTeamScore1","awayTeamScore1","status2", null);
		assertNotNull(obj);
		assertEquals("homeTeam", obj.getHomeTeam());
		assertEquals("homeTeamID", obj.getHomeTeamID());
		assertEquals("awayTeam", obj.getAwayTeam());
		assertEquals("awayTeamID", obj.getAwayTeamID());
		assertEquals("date", obj.getDate());
		assertEquals("time", obj.getTime());
		assertEquals("homeTeamScore",obj.getHomeTeamScore());
		assertEquals("awayTeamScore",obj.getAwayTeamScore());
		assertEquals("status",obj.getGameStatus());
		assertEquals(true, obj.equals(obj));
		assertEquals(false, obj.equals(obj1));
	}
	
	public void testStatsListItem(){
		StatsListItem obj = new StatsListItem("statType", "playerName", "statValue", "playerTeamID");
		assertNotNull(obj);
		assertEquals("statType", obj.getStatType());
		assertEquals("playerName", obj.getPlayerName());
		assertEquals("statValue", obj.getStatValue());
		assertEquals("playerTeamID", obj.getPlayerTeamID());
		assertEquals(0, obj.describeContents());
	}
	
	public void testTeamRecordItem(){
		TeamRecordItem obj = new TeamRecordItem("teamName","teamID","wins", "losses","pointsDiff");
		TeamRecordItem obj1 = new TeamRecordItem("teamName1","teamID1", "wins1", "losses1","pDiff1");
		assertNotNull(obj);
		assertEquals("teamName", obj.getTeamName());
		assertEquals("teamID",obj.getTeamID());
		assertEquals("wins", obj.getWins());
		assertEquals("losses", obj.getLosses());
		assertEquals("pointsDiff",obj.getPointsDiff());
		assertEquals("teamName teamID wins losses pointsDiff", obj.toString());
		assertEquals(true, obj.equals(obj));
		assertEquals(false, obj.equals(obj1));
	}
	
	public void testTeamListItem(){
		TeamsListItem obj = new TeamsListItem("name", "id");
		assertNotNull(obj);
		obj.addPlayer("player_name", "player_id");
		obj.addStats("goals", "player1", "1");
		obj.addStats("drops", "player2", "4");
		obj.addSchedule("team", "teamId", "date", "time");
		assertEquals("name", obj.getTeamName());
		assertEquals("id", obj.getTeamId());
		assertEquals("player_name", obj.getPlayerNames().get(0));
		assertEquals("player_id", obj.getPlayerIds().get(0));
		assertEquals("team", obj.getSchedTeams().get(0));
		assertEquals("teamId", obj.getSchedTeamIds().get(0));
		assertEquals("date", obj.getSchedDates().get(0));
		assertEquals("time", obj.getSchedTimes().get(0));
		assertEquals("goals", obj.getStatsKeys().get(0));
		assertEquals("drops", obj.getStatsKeys().get(1));
		assertEquals("player1", obj.getStatsList("goals").get(0).getPlayerName());
		assertEquals("1", obj.getStatsList("goals").get(0).getStatValue());
	}
	
	public void testVideoListItem(){
		VideosListItem obj = new VideosListItem("name", "url", "thumbnail");
		assertNotNull(obj);
		assertEquals("name", obj.getVideoName());
		assertEquals("url", obj.getVideoURL());
		assertEquals("thumbnail", obj.getVideoThumnbnail());
	}

}
