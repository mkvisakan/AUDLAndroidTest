package info.androidhive.audlandroid.test;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import info.androidhive.audlandroid.MainActivity;
import info.androidhive.audlandroid.NewsListFragment;
import info.androidhive.audlandroid.model.NewsListItem;

public class NewsListFragmentTest extends android.test.AndroidTestCase{
	private NewsListFragment newsFrag;
	
	public NewsListFragmentTest() {
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        newsFrag = new NewsListFragment();
    }
	
	public void testAvailability() throws Exception {
        assertNotNull(newsFrag);
    }
	
	public void testParseJson() throws Exception {
		String response = "[\"AUDL News\", [\"AUDL Annouces the signing of Brodie Smith\", \"http://www.theaudl.com/articles/ata/brodie\", \"11 Mar 2014\"], [\"Championship Weekend III Comes to Toronto\", \"http://www.theaudl.com/articles/ata/champ2104\", \"11 Mar 2014\"]]";
		JSONArray jsonResult = new JSONArray(response);
		ArrayList<NewsListItem> newsList = newsFrag.parseJSON(jsonResult);
		assertEquals("Testing the parse json of NewsListFragment", 2, newsList.size());
		assertEquals("Testing the parsing of news header", "AUDL Annouces the signing of Brodie Smith", newsList.get(0).getNewsHeadline());
		assertEquals("Testing the parsing of news url", "http://www.theaudl.com/articles/ata/brodie", newsList.get(0).getNewsURL());
		assertEquals("Testing the parsing of news datetime", "11 Mar 2014", newsList.get(0).getDatetime());
	}
}
