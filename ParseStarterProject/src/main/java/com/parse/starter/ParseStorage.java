package pakuteam.bedlam_experiment_0_1;

import android.os.AsyncTask;

import com.parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;
import java.util.regexp;
import java.util.ArrayList;
import java.util.List;
import java.com.io;

//interface that implements basic quote features like saving data 
public interface IParseStorage
{
	Task<List> GetItemAsync(string id);
	Task<List<Item>> RefreshDataAsync();
	Task SaveItemAsync(Item item);
	Task DeleteItemAsync(Item item);
}

public class Item
{
        public Item()
        {
            Id = this.Id;
            Name = this.Name;
            Author = this.Author;
        	Content = this.Content;
        	rating = this.rating;
        	tags = this.tags;
        }

        public int Id;
        public string Name;
        public string Author;
        public string Content;
        public int rating;

        public List<string> tags;
}


public class ParseStorage implements IParseStorage
{
	static ParseStorage impl = new ParseStorage();
	public static ParseStorage default { get{return impl;} };
	public List<Item> Quotes;

	protected ParseStorage()
	{
		Items = new List<Item>();
	}

	//create new ParseObject
	public ParseObject ToParseObect(Item item)
	{
		ParseObject po = new ParseObject("Citation");
		if(item.Id != 0)
		{
			po.objectId = Integer.toString(item.id);
		}

		//saving attributes
		po["Author"] = item.Author;
        po["Name"] = item.Name;
        po["Rating"] = item.rating;
        po["Tags"] = item.tags;
        po["Content"] = item.Content;
        po["Added"] = item.Added;

		return po;
	}
	//retrieve ParseObject from server
	public static Item FromParseObject(ParseObject po)
	{
		Item ro = new Item();
		//getting all values from an object
		ro.Author = po.getString("Author");
		ro.Name = po.getString("Name");
		ro.Rating = po.getInt("Rating");
		ro.Tags = po.getString("Tags");
		ro.Content = po.getString("Content");
		ro.Content = po.get("Added");

		return ro;
	}

	public void RefreshData(List item, ParseException e)
	{
		for(Item i : intem)
		{
			ParseObject cit = ParseStorage.ToParseObect(i);
			if(e == null)
			{
				cit.saveInBackground();
			}
			else
			{
				//ммм, хреновая обработка ошибок - то что нам нужно
				throw new ParseException(0, "Something going wrong");
			}
		}

	}
	//Todo: add some asyncronus
	public void SaveItem(Item item, ParseException e)
	{
		ParseObject o = ParseStorage.ToParseObect(item);
		if(e == null)
		{
			o.saveInBackground();
		}
		else
		{
			throw new ParseException(0, "Some Exception")
		}
	}

	public void RefreshUserData(ParseUser user)
	{
		
	}




}



