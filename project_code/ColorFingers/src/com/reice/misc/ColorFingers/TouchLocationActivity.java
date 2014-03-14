package com.reice.misc.ColorFingers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

public class TouchLocationActivity extends Activity {

	// min finger-pixel movement to indicate change change in position
	private static final int MIN_DXDY = 2;

	// Assume no more than 20 simultaneous touches
	final private static int MAX_TOUCHES = 20;

	// Pool of MarkerViews
	final private static LinkedList<MarkerView> mInactiveMarkers = new LinkedList<MarkerView>();

	// choice of colors
	private RadioGroup mGroupColor1;
	private RadioGroup mGroupColor2;

	// Set of MarkerViews currently visible on the display
	// Map is used to create a key-value pair
	@SuppressLint("UseSparseArrays")
	final private static Map<Integer, MarkerView> mActiveMarkers = new HashMap<Integer, MarkerView>();

	protected static final String TAG = "TouchLocationActivity";
	protected static final String testTAG = "testTagforIndicate";

	private FrameLayout mFrame;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//setting the reference to radioGroups
		mGroupColor1 = (RadioGroup) findViewById(R.id.radioGroup1);
		mGroupColor2 = (RadioGroup) findViewById(R.id.radioGroup2);
		
		
		mFrame = (FrameLayout) findViewById(R.id.frame);

		// Initialize pool of View.
		initViews();

		// Create and set on touch listener
		mFrame.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getActionMasked()) {

				// Show new MarkerView
				//first finger for color1
				case MotionEvent.ACTION_DOWN:
				{

					int pointerIndex = event.getActionIndex();
					int pointerID = event.getPointerId(pointerIndex);
					//remove an inactive marker from the linkedList and reference it to marker.
					MarkerView marker = mInactiveMarkers.remove();
					
					//if the marker is not null then put marker in to a hasmap
					int colorSelected = mGroupColor1.getCheckedRadioButtonId();
					if(colorSelected == -1)
					{
						Log.i(testTAG, "Entering random color");
						//if the marker is not null then put marker in to a hasmap
						if (null != marker && mActiveMarkers.size() < 2) {
							mActiveMarkers.put(pointerID, marker);
	
							marker.setXLoc(event.getX(pointerIndex));
							marker.setYLoc(event.getY(pointerIndex));
	
							updateTouches(mActiveMarkers.size());
	
							mFrame.addView(marker);
						}
					}
					else
					{
						Log.i(testTAG, "Entering fixed color");
						if (null != marker && mActiveMarkers.size() < 2) {
							mActiveMarkers.put(pointerID, marker);
							
							marker.setXLoc(event.getX(pointerIndex));
							marker.setYLoc(event.getY(pointerIndex));
							
							updateTouches(mActiveMarkers.size());
							
							//check for what is the color
							if(colorSelected == R.id.redButton1)
								marker.setColor(150,255,0,0);
							else if(colorSelected == R.id.greenButton1)
								marker.setColor(150,0,255,0);
							else if(colorSelected == R.id.blueButton1)
								marker.setColor(150,0,0,255);
							else
								Log.i(testTAG, "Not able to check any color");
							
							mFrame.addView(marker);
						}
					}
					break;
				}
				//second finger for color 2
				case MotionEvent.ACTION_POINTER_DOWN: 
				{
					int pointerIndex = event.getActionIndex();
					int pointerID = event.getPointerId(pointerIndex);
					//remove an inactive marker from the linkedList and reference it to marker.
					MarkerView marker = mInactiveMarkers.remove();
					int colorSelected = mGroupColor2.getCheckedRadioButtonId();
					if(colorSelected == -1)
					{
						//if the marker is not null then put marker in to a hasmap
						if (null != marker && mActiveMarkers.size() < 2) {
							mActiveMarkers.put(pointerID, marker);
	
							marker.setXLoc(event.getX(pointerIndex));
							marker.setYLoc(event.getY(pointerIndex));
	
							updateTouches(mActiveMarkers.size());
	
							mFrame.addView(marker);
						}
					}
					else
					{
						if (null != marker && mActiveMarkers.size() < 2) {
							mActiveMarkers.put(pointerID, marker);
	
							marker.setXLoc(event.getX(pointerIndex));
							marker.setYLoc(event.getY(pointerIndex));
							
							updateTouches(mActiveMarkers.size());
							
							//check for what is the color
							if(colorSelected == R.id.blueButton2)
								marker.setColor(100,0, 0, 255);
							else if(colorSelected == R.id.redButton2)
								marker.setColor(100,255,0,0);
							else if(colorSelected == R.id.greenButton2)
								marker.setColor(100,0,255,0);
							else
								Log.i(testTAG,"Not able to check any color");
							
							mFrame.addView(marker);
						}
					}
					break;
				}
	
					
					

				// Remove one MarkerView
				
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP: {

					int pointerIndex = event.getActionIndex();
					int pointerID = event.getPointerId(pointerIndex);

					MarkerView marker = mActiveMarkers.remove(pointerID);

					if (null != marker) {

						mInactiveMarkers.add(marker);

						updateTouches(mActiveMarkers.size());

						mFrame.removeView(marker);
					}
					break;
				}
				
				
				// Move all currently active MarkerViews
				
				case MotionEvent.ACTION_MOVE: {

					for (int idx = 0; idx < event.getPointerCount(); idx++) {

						int ID = event.getPointerId(idx);

						MarkerView marker = mActiveMarkers.get(ID);
						if (null != marker) {
							
							// Redraw only if finger has traveled a minimum distance   
							if (Math.abs(marker.getXLoc() - event.getX(idx)) > MIN_DXDY
									|| Math.abs(marker.getYLoc()
											- event.getY(idx)) > MIN_DXDY) {
								
								// Set new location
								
								marker.setXLoc(event.getX(idx));
								marker.setYLoc(event.getY(idx));
								
								// Request re-draw
								marker.invalidate();
							}
						}
					}

					break;
				}

				default:

					Log.i(TAG, "unhandled action");
				}

				return true;
			}

			// update number of touches on each active MarkerView
			//each marker is updated with a size value to indicate what should be the size of the marker
			private void updateTouches(int numActive) {
				for (MarkerView marker : mActiveMarkers.values()) {
					marker.setTouches(numActive);
				}
			}
		});
	}

	private void initViews() {
		// 20 markers representing 20 fingers created with -1,-1 location
		// initially.
		for (int idx = 0; idx < MAX_TOUCHES; idx++) {
			mInactiveMarkers.add(new MarkerView(this, -1, -1));
		}
	}

	private class MarkerView extends View {
		private float mX, mY;
		// determines the size of the circle.
		final static private int MAX_SIZE = 400;
		// initially assuming touches to be zero.
		private int mTouches = 0;
		// creating a paint object to draw on the canvas
		final private Paint mPaint = new Paint();

		// when color is not chosen
		public MarkerView(Context context, float x, float y) {
			super(context);
			mX = x;
			mY = y;
			mPaint.setStyle(Style.FILL);

			Random rnd = new Random();
			mPaint.setARGB(220, rnd.nextInt(256), rnd.nextInt(256),
					rnd.nextInt(256));
		}

		// when some color is chosen
		public MarkerView(Context context, float x, float y, int red,
				int green, int blue) {
			super(context);
			mX = x;
			mY = y;
			mPaint.setStyle(Style.FILL);

			mPaint.setARGB(220, red, green, blue);
		}

		float getXLoc() {
			return mX;
		}

		void setXLoc(float x) {
			mX = x;
		}

		float getYLoc() {
			return mY;
		}

		void setYLoc(float y) {
			mY = y;
		}

		void setTouches(int touches) {
			mTouches = touches;
		}
		
		void setColor(int alpha, int red, int green, int blue)
		{
			mPaint.setARGB(220, red, green, blue);
		}

		// this is called before the view is projected onto the screen.
		// canvas ref is sent by the android system.
		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawCircle(mX, mY, MAX_SIZE / mTouches, mPaint);
		}
	}

}