
/*
 MIT License

 Copyright (c) 2018 Ibrahim Said Elsharawy

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package devhima.morse.coder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.os.Vibrator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.content.ClipData;
import android.content.ClipboardManager;

public class MainActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private double listid = 0;
	private double x = 0;
	private String msg = "";
	private boolean exit = false;
	private String name = "";
	private String codemsg = "";
	
	private ArrayList<String> wardstomahar = new ArrayList<>();
	private ArrayList<String> wards = new ArrayList<>();
	private ArrayList<String> morse = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> sharemap = new ArrayList<>();
	
	private LinearLayout linear5;
	private LinearLayout light;
	private ScrollView body_src;
	private LinearLayout body;
	private LinearLayout linear9;
	private Spinner spinner;
	private LinearLayout linear10;
	private LinearLayout morse_buttons_l;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private TextView title;
	private TextView help;
	private LinearLayout linear11;
	private Button clear;
	private EditText edittext1;
	private EditText edittext2;
	private Button dot;
	private Button dash;
	private Button s3;
	private Button s7;
	private Button get_code;
	private Button paste;
	private Button copy;
	private ListView sharelist;
	private Spinner share_spinner;
	private TextView textview4;
	private EditText signal_unit;
	private TextView textview3;
	private Button vibration;
	private Button sound;
	private Button signal_light;
	private ImageView help_img;
	private TextView textview2;
	private TextView tvMyLink;
	private TimerTask holding_time;
	private Vibrator vibration_com;
	private AlertDialog.Builder dialog;
	private MediaPlayer beep;
	private TimerTask beeptime;
	private Intent copying = new Intent();
	private TimerTask exit_timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}
	
	private void initialize() {
		
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		light = (LinearLayout) findViewById(R.id.light);
		body_src = (ScrollView) findViewById(R.id.body_src);
		body = (LinearLayout) findViewById(R.id.body);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		spinner = (Spinner) findViewById(R.id.spinner);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		morse_buttons_l = (LinearLayout) findViewById(R.id.morse_buttons_l);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		title = (TextView) findViewById(R.id.title);
		help = (TextView) findViewById(R.id.help);
		tvMyLink = (TextView) findViewById(R.id.tvMyLink);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		clear = (Button) findViewById(R.id.clear);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		dot = (Button) findViewById(R.id.dot);
		dash = (Button) findViewById(R.id.dash);
		s3 = (Button) findViewById(R.id.s3);
		s7 = (Button) findViewById(R.id.s7);
		get_code = (Button) findViewById(R.id.get_code);
		paste = (Button) findViewById(R.id.paste);
		copy = (Button) findViewById(R.id.copy);
		sharelist = (ListView) findViewById(R.id.sharelist);
		share_spinner = (Spinner) findViewById(R.id.share_spinner);
		textview4 = (TextView) findViewById(R.id.textview4);
		signal_unit = (EditText) findViewById(R.id.signal_unit);
		textview3 = (TextView) findViewById(R.id.textview3);
		vibration = (Button) findViewById(R.id.vibration);
		sound = (Button) findViewById(R.id.sound);
		signal_light = (Button) findViewById(R.id.signal_light);
		help_img = (ImageView) findViewById(R.id.help_img);
		textview2 = (TextView) findViewById(R.id.textview2);
		vibration_com = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		dialog = new AlertDialog.Builder(this);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				_check_list(_position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				linear5.setVisibility(View.GONE);
				light.setVisibility(View.VISIBLE);
				textview2.setVisibility(View.GONE);
				help_img.setVisibility(View.VISIBLE);
				SketchwareUtil.showMessage(getApplicationContext(), "Tap to go back.");
			}
		});
		
		tvMyLink.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://devhima.tk/"));
					startActivity(browse);
				}
		});
		
		clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (listid == 0) {
					edittext1.setText("");
				}
				if (listid == 1) {
					edittext2.setText("");
				}
			}
		});
		
		edittext2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				morse_buttons_l.setVisibility(View.VISIBLE);
			}
		});
		
		dot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				edittext2.setText(edittext2.getText().toString().concat("• "));
			}
		});
		
		dash.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				edittext2.setText(edittext2.getText().toString().concat("--- "));
			}
		});
		
		s3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				edittext2.setText(edittext2.getText().toString().concat("  "));
			}
		});
		
		s7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				edittext2.setText(edittext2.getText().toString().concat("      "));
			}
		});
		
		get_code.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				_clean();
				x = 0;
				if (listid == 0) {
					for(int _repeat28 = 0; _repeat28 < (int)(edittext1.getText().toString().length()); _repeat28++) {
						if (wards.contains(edittext1.getText().toString().toLowerCase().substring((int)(x), (int)(x + 1)))) {
							edittext2.setText(edittext2.getText().toString().concat(morse.get((int)(wards.indexOf(edittext1.getText().toString().toLowerCase().substring((int)(x), (int)(x + 1)))))).concat("   "));
						}
						x = x + 1;
					}
				}
				else {
					_reverse();
				}
			}
		});
		
		paste.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
		
		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (listid == 0) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", edittext2.getText().toString()));
				}
				else {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", edittext1.getText().toString()));
				}
				SketchwareUtil.showMessage(getApplicationContext(), "message copied.");
			}
		});
		
		share_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					
				}
				else {
					if (_position == 1) {
						if (listid == 0) {
							copying.setData(Uri.parse("whatsapp://send?text=".concat(edittext2.getText().toString())));
						}
						else {
							copying.setData(Uri.parse("whatsapp://send?text=".concat(edittext1.getText().toString())));
						}
					}
					if (_position == 2) {
						if (listid == 0) {
							copying.setData(Uri.parse("mailto:?title=see&body=".concat(edittext2.getText().toString())));
						}
						else {
							copying.setData(Uri.parse("mailto:?title=see&body=".concat(edittext1.getText().toString())));
						}
					}
					copying.setAction(Intent.ACTION_VIEW);
					startActivity(copying);
					share_spinner.setSelection((int)(0));
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		vibration.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.setTitle("Caution! Risky to use.");
				dialog.setMessage("Continue with vibrations?");
				dialog.create().show();
				dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						textview2.setText("Vibrator is on");
						light.setVisibility(View.VISIBLE);
						x = 0;
						edittext2.setEnabled(false);
						if ((signal_unit.getText().toString().length() < 1) || (Double.parseDouble(signal_unit.getText().toString()) < 1)) {
							signal_unit.setText("1");
						}
						if (!(edittext2.getText().toString().length() == 0)) {
							holding_time = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (x == (edittext2.getText().toString().length() - 2)) {
												holding_time.cancel();
												SketchwareUtil.showMessage(getApplicationContext(), "Signal over. Thanks");
												textview2.setText("Signal");
												light.setVisibility(View.GONE);
												edittext2.setEnabled(true);
												holding_time.cancel();
											}
											else {
												edittext2.setEnabled(false);
												if (!edittext2.getText().toString().substring((int)(x), (int)(x + 1)).equals(" ")) {
													vibration_com.vibrate((long)(Double.parseDouble(signal_unit.getText().toString()) * 250));
												}
												x++;
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(holding_time, (int)(1), (int)(Double.parseDouble(signal_unit.getText().toString()) * 250));
						}
					}
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "Try some small word.");
					}
				});
			}
		});
		
		sound.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				textview2.setText("Sound is on");
				x = 0;
				edittext2.setEnabled(false);
				if ((signal_unit.getText().toString().length() < 1) || (Double.parseDouble(signal_unit.getText().toString()) < 1)) {
					signal_unit.setText("2");
				}
				if (!(edittext2.getText().toString().length() == 0)) {
					holding_time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									if (x == (edittext2.getText().toString().length() - 2)) {
										holding_time.cancel();
										linear5.setVisibility(View.VISIBLE);
										light.setVisibility(View.GONE);
										SketchwareUtil.showMessage(getApplicationContext(), "Signal over. Thanks");
										edittext2.setEnabled(true);
										textview2.setText("Singnal");
									}
									else {
										edittext2.setEnabled(false);
										if (edittext2.getText().toString().substring((int)(x), (int)(x + 1)).equals(" ")) {
											x++;
										}
										else {
											beep.start();
											x++;
										}
									}
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(holding_time, (int)(0), (int)(Double.parseDouble(signal_unit.getText().toString()) * 250));
				}
			}
		});
		
		signal_light.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				linear5.setVisibility(View.GONE);
				light.setVisibility(View.VISIBLE);
				textview2.setText("");
				x = 0;
				edittext2.setEnabled(false);
				if ((signal_unit.getText().toString().length() < 1) || (Double.parseDouble(signal_unit.getText().toString()) < 1)) {
					signal_unit.setText("1");
				}
				if (!(edittext2.getText().toString().length() == 0)) {
					holding_time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									if (x == (edittext2.getText().toString().length() - 2)) {
										holding_time.cancel();
										linear5.setVisibility(View.VISIBLE);
										light.setVisibility(View.GONE);
										SketchwareUtil.showMessage(getApplicationContext(), "Signal over. Thanks");
										edittext2.setEnabled(true);
										textview2.setText("Singnal");
										holding_time.cancel();
									}
									else {
										edittext2.setEnabled(false);
										if (edittext2.getText().toString().substring((int)(x), (int)(x + 1)).equals(" ")) {
											light.setBackgroundColor(0xFFE0E0E0);
										}
										else {
											light.setBackgroundColor(0xFFF44336);
										}
										x++;
									}
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(holding_time, (int)(1), (int)(Double.parseDouble(signal_unit.getText().toString()) * 250));
				}
			}
		});
		
		help_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				linear5.setVisibility(View.VISIBLE);
				light.setVisibility(View.GONE);
				help_img.setVisibility(View.GONE);
				textview2.setVisibility(View.VISIBLE);
			}
		});
	}
	private void initializeLogic() {
		setTitle("Morse Coder");
		wardstomahar.add("Words to Morse");
		wardstomahar.add("Morse to Words");
		spinner.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, wardstomahar));
		((ArrayAdapter)spinner.getAdapter()).notifyDataSetChanged();
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Share");
			sharemap.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "WhatsApp");
			sharemap.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Gmail");
			sharemap.add(_item);
		}
		
		share_spinner.setAdapter(new SharelistAdapter(sharemap));
		light.setVisibility(View.INVISIBLE);
		morse_buttons_l.setVisibility(View.GONE);
		help_img.setVisibility(View.GONE);
		beep = MediaPlayer.create(getApplicationContext(), R.raw.beep250);
		_check_list(0);
		_makelist();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (exit) {
			exit = false;
			finish();
		}
		else {
			exit = true;
			SketchwareUtil.showMessage(getApplicationContext(), "press again to exit.");
			exit_timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							exit = false;
						}
					});
				}
			};
			_timer.schedule(exit_timer, (int)(500));
		}
	}
	private void _makelist () {
		wards.add("a");
		wards.add("b");
		wards.add("c");
		wards.add("d");
		wards.add("e");
		wards.add("f");
		wards.add("g");
		wards.add("h");
		wards.add("i");
		wards.add("j");
		wards.add("k");
		wards.add("l");
		wards.add("m");
		wards.add("n");
		wards.add("o");
		wards.add("p");
		wards.add("q");
		wards.add("r");
		wards.add("s");
		wards.add("t");
		wards.add("u");
		wards.add("v");
		wards.add("w");
		wards.add("x");
		wards.add("y");
		wards.add("z");
		wards.add("1");
		wards.add("2");
		wards.add("3");
		wards.add("4");
		wards.add("5");
		wards.add("6");
		wards.add("7");
		wards.add("8");
		wards.add("9");
		wards.add("0");
		wards.add(" ");
		morse.add("• ---");
		morse.add("--- • • •");
		morse.add("--- • --- •");
		morse.add("--- • •");
		morse.add("•");
		morse.add("• • --- •");
		morse.add("--- --- •");
		morse.add("• • • •");
		morse.add("• •");
		morse.add("• --- --- ---");
		morse.add("--- • ---");
		morse.add("• --- • •");
		morse.add("--- ---");
		morse.add("--- •");
		morse.add("--- --- ---");
		morse.add("• --- --- •");
		morse.add("--- --- • ---");
		morse.add("• --- •");
		morse.add("• • •");
		morse.add("---");
		morse.add("• • ---");
		morse.add("• • • ---");
		morse.add("• --- ---");
		morse.add("--- • • ---");
		morse.add("--- • --- ---");
		morse.add("--- --- • •");
		morse.add("• --- --- --- ---");
		morse.add("• • --- --- ---");
		morse.add("• • • --- ---");
		morse.add("• • • • ---");
		morse.add("• • • • •");
		morse.add("--- • • • •");
		morse.add("--- --- • • •");
		morse.add("--- --- --- • •");
		morse.add("--- --- --- --- •");
		morse.add("--- --- --- --- ---");
		morse.add(" ");
	}
	
	
	private void _clean () {
		if (listid == 0) {
			edittext2.setText("");
		}
		if (listid == 1) {
			edittext1.setText("");
		}
	}
	
	
	private void _reverse () {
		x = 0;
		msg = "";
		codemsg = edittext2.getText().toString().concat("      ");
		for(int _repeat12 = 0; _repeat12 < (int)((codemsg.length() - 7)); _repeat12++) {
			if (codemsg.substring((int)(x), (int)(x + 3)).equals("   ")) {
				_write();
				if (x < (codemsg.length() - 7)) {
					if (codemsg.substring((int)(x), (int)(x + 7)).equals("       ")) {
						msg = msg.concat(" ");
						x = x + 7;
						_write();
					}
					else {
						x = x + 3;
					}
				}
			}
			else {
				msg = msg.concat(codemsg.substring((int)(x), (int)(x + 1)));
				if (x < (codemsg.length() - 4)) {
					x = x + 1;
				}
			}
		}
		if (edittext1.getText().toString().length() == 0) {
			edittext2.setText(codemsg);
			_reverse();
		}
	}
	
	
	private void _write () {
		if (morse.contains(msg)) {
			edittext1.setText(edittext1.getText().toString().concat(wards.get((int)(morse.indexOf(msg)))));
			msg = "";
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "Code contains error!!");
		}
	}
	
	
	private void _check_list (final double _number) {
		if (_number == 0) {
			listid = _number;
			get_code.setText("Encode");
			edittext2.setText("");
			edittext1.setEnabled(true);
			edittext2.setEnabled(false);
			morse_buttons_l.setVisibility(View.GONE);
		}
		else {
			listid = _number;
			get_code.setText("Decode");
			edittext1.setText("");
			edittext2.setEnabled(true);
			edittext1.setEnabled(false);
			morse_buttons_l.setVisibility(View.VISIBLE);
		}
	}
	
	
	private void _sound1 () {
		textview2.setText("Sound is on");
		beep = MediaPlayer.create(getApplicationContext(), R.raw.beep250);
		x = 0;
		edittext2.setEnabled(false);
		if (signal_unit.getText().toString().length() == 0) {
			signal_unit.setText("1");
		}
		if (!(edittext2.getText().toString().length() == 0)) {
			if (x == (edittext2.getText().toString().length() - 2)) {
				holding_time.cancel();
				linear5.setVisibility(View.VISIBLE);
				light.setVisibility(View.GONE);
				SketchwareUtil.showMessage(getApplicationContext(), "Signal over. Thanks");
				edittext2.setEnabled(true);
				textview2.setText("Singnal");
			}
			else {
				edittext2.setEnabled(false);
				if (edittext2.getText().toString().substring((int)(x), (int)(x + 1)).equals(" ")) {
					holding_time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									beep.pause();
									holding_time.cancel();
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(holding_time, (int)(0), (int)(Double.parseDouble(signal_unit.getText().toString()) * 250));
				}
				else {
					holding_time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									beep.start();
									holding_time.cancel();
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(holding_time, (int)(0), (int)(Double.parseDouble(signal_unit.getText().toString()) * 250));
				}
				x++;
			}
		}
	}
	
	
	public class SharelistAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public SharelistAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.test, null);
			}
			
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			
			name = sharemap.get((int)_position).get("name").toString();
			if (name.contains("Share")) {
				imageview1.setImageResource(R.drawable.share);
			}
			if (name.contains("WhatsApp")) {
				imageview1.setImageResource(R.drawable.whatsapp);
			}
			if (name.contains("Gmail")) {
				imageview1.setImageResource(R.drawable.gmail);
			}
			
			return _v;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
