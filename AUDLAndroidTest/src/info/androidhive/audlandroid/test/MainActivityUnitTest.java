package info.androidhive.audlandroid.test;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.model.NavDrawerItem;

public class MainActivityUnitTest extends android.test.ActivityInstrumentationTestCase2<MainActivity>{
	
	private MainActivity activity;
	
	public MainActivityUnitTest() {
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
	
	public void testMenuClickListener(){
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
	
}
