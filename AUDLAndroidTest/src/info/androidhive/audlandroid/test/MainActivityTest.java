package info.androidhive.audlandroid.test;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.test.UiThreadTest;
import android.widget.ListView;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.model.NavDrawerItem;

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
	
}
