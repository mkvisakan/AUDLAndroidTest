package info.androidhive.audlandroid.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.test.UiThreadTest;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.adapter.ExpandableListAdapter;
import info.androidhive.audlandroid.adapter.FaqAdapter;
import info.androidhive.audlandroid.adapter.ListAdapter;
import info.androidhive.audlandroid.adapter.NavDrawerListAdapter;
import info.androidhive.audlandroid.adapter.NewsListBaseAdapter;
import info.androidhive.audlandroid.adapter.ScheduleListBaseAdapter;
import info.androidhive.audlandroid.adapter.ScoreListBaseAdapter;
import info.androidhive.audlandroid.adapter.StatsListBaseAdapter;
import info.androidhive.audlandroid.adapter.TeamRosterBaseAdapter;
import info.androidhive.audlandroid.adapter.TeamsListBaseAdapter;
import info.androidhive.audlandroid.adapter.VideosListBaseAdapter;
import info.androidhive.audlandroid.model.NavDrawerItem;
import info.androidhive.audlandroid.model.NewsListItem;
import info.androidhive.audlandroid.model.ScheduleListItem;
import info.androidhive.audlandroid.model.ScoreListItem;
import info.androidhive.audlandroid.model.StatsListItem;
import info.androidhive.audlandroid.model.TeamsListItem;
import info.androidhive.audlandroid.model.VideosListItem;

public class MainActivityTest extends android.test.ActivityInstrumentationTestCase2<MainActivity>{
	
	private MainActivity activity;
	
	public MainActivityTest() {
	    super(MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    setActivityInitialTouchMode(false);
	    activity = getActivity();
	}
	
	@Override
	protected void tearDown() throws Exception {
		activity.finish();
	}
	
	public void testNumberOfSections(){
		int drawerListId = info.androidhive.audlandroid.R.id.list_slidermenu;
		assertNotNull(activity.findViewById(drawerListId));
		ListView drawerList = (ListView) activity.findViewById(drawerListId);
		assertEquals("Incorrect number of navigation list items", 10, drawerList.getCount());
	}
	
	public void testListenerAvailability(){
		int drawerListId = info.androidhive.audlandroid.R.id.list_slidermenu;
		ListView drawerList = (ListView) activity.findViewById(drawerListId);
		assertTrue(drawerList.getOnItemClickListener() != null);
	}
	
	public void testDefaultSection(){
		int drawerListId = info.androidhive.audlandroid.R.id.list_slidermenu;
		ListView drawerList = (ListView) activity.findViewById(drawerListId);
		int mPos = drawerList.getSelectedItemPosition();
		NavDrawerItem mSelection = (NavDrawerItem) drawerList.getItemAtPosition(mPos);
		assertEquals("Default section should be Home", "Home", mSelection.getTitle());
	}
	
	@UiThreadTest
	public void testOnClickFragmentLoads(){
		HashMap<Integer, String> menuSections = new HashMap<Integer, String>();
		menuSections.put(0, "Home");
		menuSections.put(1, "News");
		menuSections.put(2, "Teams");
		menuSections.put(3, "Now");
		menuSections.put(4, "Standings");
		menuSections.put(5, "Scores");
		menuSections.put(6, "Schedule");
		menuSections.put(7, "Videos");
		menuSections.put(8, "Statistics");
		menuSections.put(9, "Settings");		
		
		int drawerListId = info.androidhive.audlandroid.R.id.list_slidermenu;
		ListView drawerList = (ListView) activity.findViewById(drawerListId);
		for (Map.Entry<Integer, String> entry : menuSections.entrySet()){
			drawerList.performItemClick(drawerList, entry.getKey(), entry.getKey());
			assertEquals("Testing clicking of " +  entry.getValue() + " menu", entry.getValue(), getActivity().getActionBar().getTitle());
		}
	}
	
	public void testFaqAdapter() {
		ArrayList<String> qs = new ArrayList<String>();
		qs.add("question1");
		qs.add("question2");
		ArrayList<String> as = new ArrayList<String>();
		as.add("answer1");
		as.add("answer2");
		FaqAdapter fq = new FaqAdapter(activity, qs, as);
		assertEquals("TestFaqAdapter: testing count ", 2, fq.getCount());
		assertEquals("TestFaqAdapter: testing item ", 1, fq.getItem(1));
		assertEquals("TestFaqAdapter: testing itemid ", 0, fq.getItemId(0));
	}
	
	public void testListAdapter() {
		ArrayList<String> as = new ArrayList<String>();
		as.add("string1");
		as.add("string2");
		ListAdapter lt = new ListAdapter(activity.getApplicationContext(), 1, as);
		assertEquals("TestListAdapter: testing itemid ", 0, lt.getItemId(0));
		assertEquals("TestListAdapter: testing stableIds ", true, lt.hasStableIds());
	}
	
	public void testNavDrawerListAdapter() {
		ArrayList<NavDrawerItem> items = new ArrayList<NavDrawerItem>();
		items.add(new NavDrawerItem("Title1", 1));
		NavDrawerListAdapter ap = new NavDrawerListAdapter(activity.getApplicationContext(), items);
		assertEquals("TestNavDrawerListAdapter: testing count ", 1, ap.getCount());
		assertEquals("TestNavDrawerListAdapter: testing item ", "Title1", ((NavDrawerItem)ap.getItem(0)).getTitle());
		assertEquals("TestNavDrawerListAdapter: testing itemid ", 0, ap.getItemId(0));
	}
	
	public void testExpandableListAdapter(){
		ArrayList<String> header = new ArrayList<String>();
		header.add("header1");
		ArrayList<String> data = new ArrayList<String>();
		data.add("data1");
		ArrayList<String> val = new ArrayList<String>();
		val.add("value1");
		HashMap<String, List<String>> dataMp = new HashMap<String, List<String>>();
		dataMp.put("header1", data);
		HashMap<String, List<String>> valMp = new HashMap<String, List<String>>();
		valMp.put("header1", val);
		ExpandableListAdapter ap = new ExpandableListAdapter(activity.getApplicationContext(), header, dataMp, valMp);
		assertEquals("TestExpandableListAdapter: testing get child ", "data1", ap.getChild(0, 0));
		assertEquals("TestExpandableListAdapter: testing get child ", 0, ap.getChildId(0, 0));
		assertEquals("TestExpandableListAdapter: testing get child ", "value1", ap.getChildVal(0, 0));
		assertEquals("TestExpandableListAdapter: testing get child count", 1, ap.getChildrenCount(0));
		assertEquals("TestExpandableListAdapter: testing get group ", 0, ap.getGroupId(0));
		assertEquals("TestExpandableListAdapter: testing get group ", "header1", (String)ap.getGroup(0));
		assertEquals("TestExpandableListAdapter: testing get group count ",1, ap.getGroupCount());
		assertEquals("TestExpandableListAdapter: testing get group count ",false, ap.hasStableIds());
		assertEquals("TestExpandableListAdapter: testing get group count ",true, ap.isChildSelectable(0, 0));
	}
	
	public void testNewsListBaseAdapter(){
		ArrayList<NewsListItem> items = new ArrayList<NewsListItem>();
		items.add(new NewsListItem("headline", "URL", "datetime"));
		NewsListBaseAdapter ap = new NewsListBaseAdapter(activity, items);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
	public void testScheduleListBaseAdapter(){
		ArrayList<ScheduleListItem> items = new ArrayList<ScheduleListItem>();
		items.add(new ScheduleListItem("division", "homeTeam", "homeTeamID", "awayTeam", "awayTeamID", "date", "time"));
		ScheduleListBaseAdapter ap = new ScheduleListBaseAdapter(activity, items);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
	public void testScoreListBaseAdapter(){
		ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
		items.add(new ArrayList<String>());
		ScoreListBaseAdapter ap = new ScoreListBaseAdapter(activity, items);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
	public void testStatsListBaseAdapter(){
		ArrayList<StatsListItem> items = new ArrayList<StatsListItem>();
		items.add(new StatsListItem("statType", "playerName", "statValue", "playerTeamID"));
		StatsListBaseAdapter ap = new StatsListBaseAdapter(activity, items);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
	public void testTeamRosterBaseAdapter(){
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("1");
		ArrayList<String> names = new ArrayList<String>();
		names.add("name");
		TeamRosterBaseAdapter ap = new TeamRosterBaseAdapter(activity, ids, names);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
	public void testTeamsListBaseAdapter(){
		ArrayList<TeamsListItem> items = new ArrayList<TeamsListItem>();
		items.add(new TeamsListItem("name", "id"));
		TeamsListBaseAdapter ap = new TeamsListBaseAdapter(activity, items);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
	public void testVideosListBaseAdapter(){
		ArrayList<VideosListItem> items = new ArrayList<VideosListItem>();
		items.add(new VideosListItem("name", "url", "thumbnail"));
		VideosListBaseAdapter ap = new VideosListBaseAdapter(activity, items);
		assertEquals("testing count", 1, ap.getCount());
		assertEquals("testing item id", 0, ap.getItemId(0));
		assertEquals("testing item", 0, ap.getItem(0));
	}
	
}
