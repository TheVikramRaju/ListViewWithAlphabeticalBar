package com.example.listviewwithalphabeticalbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.inputmethodservice.Keyboard.Row;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Map<String, Integer> mapIndex;
	ListView fruitList;
	String[] fruits;
	String[] indexList;
	String[] dummyList;
	ArrayList<String> list;
	private HashMap<String, Integer> sections = new HashMap<String, Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Row> rows = new ArrayList<Row>();
		fruits = getResources().getStringArray(R.array.fruits_array);
		indexList = getResources().getStringArray(R.array.alphabetical_array);

		Arrays.asList(fruits);

		fruitList = (ListView) findViewById(R.id.list_fruits);
		fruitList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fruits));

		getIndexList(indexList);
		getAnotherIndexList(fruits);

		displayIndex();

	}

	// private void getIndexList(String[] fruits) {
	// mapIndex = new LinkedHashMap<String, Integer>();
	// for (int i = 0; i < fruits.length; i++) {
	// String fruit = fruits[i];
	// String index = fruit.substring(0, 1);
	//
	// if (mapIndex.get(index) == null)
	// mapIndex.put(index, i);
	// }
	// }

	private void getIndexList(String[] indexList) {
		mapIndex = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < indexList.length; i++) {
			String index = indexList[i];
			// String index = list.substring(0, 1);

			if (mapIndex.get(index) == null)
				mapIndex.put(index, i);
		}
	}

	private void getAnotherIndexList(String[] fruitList) {
		// mapIndex = new LinkedHashMap<String, Integer>();
		// list = new ArrayList<>(fruitList.length);
		// for (int i = 0; i < fruitList.length; i++) {
		// list.add(fruitList[i]);
		// }

		dummyList = new String[fruitList.length];
		for (int i = 0; i < fruitList.length; i++) {
			String list = fruitList[i];
			String index = list.substring(0, 1);
			dummyList[i] = index;
		}
		List<String> list = Arrays.asList(dummyList);
		Set<String> set = new HashSet<String>(list);

		String[] result = new String[set.size()];
		set.toArray(result);
		for (int i = 0; i < result.length; i++) {
			String index = result[i];
			dummyList[i] = index;
		}
	}

	private void displayIndex() {
		LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

		TextView textView;
		List<String> indexList = new ArrayList<String>(mapIndex.keySet());
		// indexList =
		// getResources().getStringArray(R.array.alphabetical_array);
		for (String index : indexList) {
			textView = (TextView) getLayoutInflater().inflate(
					R.layout.side_index_item, null);
			textView.setText(index);
			textView.setOnClickListener(this);
			indexLayout.addView(textView);
		}
	}

	public void onClick(View view) {
		TextView selectedIndex = (TextView) view;
		String sIndex = (String) selectedIndex.getText();
		String newindex = "";
		String pindex = "";
		int newIndexPosition = 0;
		boolean available = false;

		for (int i = 0; i < fruits.length; i++) {
			String fruit = fruits[i];
			pindex = fruit.substring(0, 1);
			newindex = fruit.substring(0, 1);
			if (newindex.equals(sIndex)) {
				available = true;
				newIndexPosition = i;
				break;
			}
		}

		/*for (int i = 0; i < indexList.length; i++) {
			for (int j = 0; j < fruits.length; j++) {
				String fruit = fruits[i];
				pindex = fruit.substring(0, 1);
				newindex = fruit.substring(0, 1);
				if (indexList[i].equals(newindex) && newindex.equals(sIndex)) {
					newIndexPosition = i;
					break;
				}
				else if(!indexList[i].equals(newindex) && !newindex.equals(sIndex)){
					if(i==0){
						newIndexPosition = i+1;
						break;
					}
					else if(i==25){
						newIndexPosition = i-1;
						break;
					}
				}
				break;
			}
		}*/

		if (!available) {
			for (int i = 0; i < indexList.length; i++) {
				for (int j = 0; j < dummyList.length; j++) {
					if (!indexList[i].equals(dummyList)) {
						newIndexPosition = j + 1;
						if (j == 25 && newIndexPosition == 26) {
							newIndexPosition = 24;
						}
					}
				}
				
			}
		}
		fruitList.setSelection(newIndexPosition);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
}
