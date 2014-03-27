package com.reice.misc.ColorFingers;


import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
//import android.widget.RadioGroup;

public class TouchActivity extends Activity {

	// min finger-pixel movement to indicate change change in position
	private static final int MIN_DXDY = 2;

	// Assume no more than 20 simultaneous touches
	//final private static int MAX_TOUCHES = 2;

	// Single marker
	private MarkerView marker;

	// bitmap for wheeler
	private Bitmap bitmap;

	// single wheeler
	private WheelView wheeler;

	// padding to centre the wheeler
	private int padding = 500 / 2;

	// radius of the circle
	//final private int RADIUS = 250;

	// color calculator
	private ColorCalc colorC;
	private int rgb[];

	// Managing points
	private MPoint p1;
	private MPoint p2;
	//private MPoint p3;

	// screen size

	private DisplayMetrics mDisplayMetrics;
	//private int mDisplayWidth;
	private int mDisplayHeight;

	// value variable in HSV
	double mValue = 1;

	// differnce saved
	double xDiff = 0;
	double yDiff = 0;

	// To find if second finger was used
	boolean newFinger = false;

	// Pool of MarkerViews
	//final private static LinkedList<View> mInactiveMarkers = new LinkedList<View>();

	// Set of MarkerViews currently visible on the display
	// Map is used to create a key-value pair
	// @SuppressLint("UseSparseArrays")
	//final private static Map<Integer, View> mActiveMarkers = new HashMap<Integer, View>();

	// choice of colors
	// private RadioGroup mGroupColor1;
	// private RadioGroup mGroupColor2;

	protected static final String TAG = "TouchActivity";
	protected static final String testTAG = "testTagforIndicate";

	private FrameLayout mFrame;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// setting the reference to radioGroups

		wheeler = new WheelView(getApplicationContext(), 0, 0);
		marker = new MarkerView(getApplicationContext(), 0, 0);

		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a1);
		bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);

		mFrame = (FrameLayout) findViewById(R.id.frame);

		// to find the height and width of the screen
		mDisplayMetrics = new DisplayMetrics();
		TouchActivity.this.getWindowManager().getDefaultDisplay()
				.getMetrics(mDisplayMetrics);
		// get the display width and height
		//mDisplayWidth = mDisplayMetrics.widthPixels;
		mDisplayHeight = mDisplayMetrics.heightPixels;

		// Initialize pool of View.
		// initViews();

		// Create and set on touch listener
		mFrame.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				// storing the third finger movement
				//int thirdFingerID = 0;

				switch (event.getActionMasked()) {

				// Show new MarkerView
				// first finger for color1
				case MotionEvent.ACTION_DOWN: {

					int pointerIndex = event.getActionIndex();
					//int pointerID = event.getPointerId(pointerIndex);

					// saving into hasmap to save the pointer and wheel
					//mActiveMarkers.put(pointerID, wheeler);

					// for setting positions
					wheeler.setXLoc(event.getX(pointerIndex) - padding);
					wheeler.setYLoc(event.getY(pointerIndex) - padding);
					// create a marker view
					marker.setXLoc(event.getX(pointerIndex));
					marker.setYLoc(event.getY(pointerIndex));
					mFrame.addView(wheeler);

					// saving origin location
					p1 = new MPoint(event.getX(pointerIndex), event
							.getY(pointerIndex));
					// dummy initialization
					p2 = new MPoint(0, 0);
					colorC = new ColorCalc(p1, p2);
					break;
				}

				case MotionEvent.ACTION_POINTER_DOWN: {
					if (!newFinger) {
						int pointerIndex = event.getActionIndex();
						//int pointerID = event.getPointerId(pointerIndex);

						// saving into hasmap to save the pointer and wheel
						//mActiveMarkers.put(pointerID, marker);

						//Log.i("Test", "Something strage" + pointerIndex);

						marker.setXLoc(event.getX());
						marker.setYLoc(event.getY());

						// setting the point 2 location
						p2 = new MPoint(event.getX(pointerIndex), event
								.getY(pointerIndex));

						xDiff = p2.x - p1.x;
						yDiff = p2.y - p1.y;

						// calculating colorValues
						rgb = new int[3];
						colorC = new ColorCalc(p1, p2);
						// calculate with value = 1;
						rgb = colorC.calculateHSVtoRGB(
								colorC.calculateAngleNormalized(),
								colorC.calculateDistanceNormalized(), mValue);

						// set the color of marker based on calc
						marker.setColor(255, rgb[0], rgb[1], rgb[2]);
						mFrame.addView(marker);
						// since marker is put over wheeler
						mFrame.bringChildToFront(wheeler);

						// new finger has been used now
						newFinger = true;

						break;
					} else {
						int pointerIndex = event.getActionIndex();
						//int pointerID = event.getPointerId(pointerIndex);

						//Log.i("Test", "new finger detected after first time"
							//	+ pointerIndex);

						marker.setXLoc(event.getX());
						marker.setYLoc(event.getY());

						// setting the point 2 location
						p2.x = event.getX(pointerIndex);
						p2.y = event.getY(pointerIndex);

						xDiff = p2.x - p1.x;
						yDiff = p2.y - p1.y;

						// calculating colorValues
						rgb = new int[3];
						// calculate with value = 1;
						rgb = colorC.calculateHSVtoRGB(
								colorC.calculateAngleNormalized(),
								colorC.calculateDistanceNormalized(), mValue);

						// set the color of marker based on calc
						marker.setColor(255, rgb[0], rgb[1], rgb[2]);
						// since marker is put over wheeler
						mFrame.bringChildToFront(wheeler);

						break;
					}

					// // if (pointerIndex == 1) {
					// // setting the point 2 location
					// p2 = new MPoint(event.getX(pointerIndex), event
					// .getY(pointerIndex));
					//
					// // calculating colorValues
					// rgb = new int[3];
					// colorC = new ColorCalc(p1, p2);
					// // calculate with value = 1;
					// rgb = colorC.calculateHSVtoRGB(
					// colorC.calculateAngleNormalized(),
					// colorC.calculateDistanceNormalized(), mValue);
					//
					// // set the color of marker based on calc
					// marker.setColor(255, rgb[0], rgb[1], rgb[2]);
					// mFrame.addView(marker);
					// // since marker is put over wheeler
					// mFrame.bringChildToFront(wheeler);
					//
					// break;
					// }
					//
					// else {
					// double normalizedScreenHeight = event
					// .getY(pointerIndex) / mDisplayHeight;
					// //top is brighter and bottom is darker
					// mValue = 1 - normalizedScreenHeight;
					//
					// // store the id of third finger for movement traction
					// thirdFingerID = event.getPointerId(pointerIndex);
					//
					// // calculate with value set by user touch;
					// rgb = colorC.calculateHSVtoRGB(
					// colorC.calculateAngleNormalized(),
					// colorC.calculateDistanceNormalized(), mValue);
					//
					// // set the color of marker based on calc
					// marker.setColor(255, rgb[0], rgb[1], rgb[2]);
					// marker.invalidate();
					// mFrame.bringChildToFront(wheeler);
					// break;
					// }
				}

				// Remove one MarkerView
				case MotionEvent.ACTION_UP: {

					// int pointerIndex = event.getActionIndex();
					// int pointerID = event.getPointerId(pointerIndex);
					mFrame.removeAllViews();
					newFinger = false;
					break;
				}

				// case MotionEvent.ACTION_POINTER_UP: {
				// int pointerIndex = event.getActionIndex();
				// if (pointerIndex == 1)
				// mFrame.removeView(marker);
				// break;
				//
				// }

				case MotionEvent.ACTION_MOVE: {

					// for (int idx = 0; idx < event.getPointerCount(); idx++) {

					// int ID = event.getPointerId(idx);

					// View view = mActiveMarkers.get(ID);
					// if (null != marker) {

					// Redraw only if finger has traveled a minimum distance
					if (event.getActionIndex() == 0)
						if (Math.abs(wheeler.getXLoc() - event.getX(0)) > MIN_DXDY
								|| Math.abs(wheeler.getYLoc() - event.getY(0)) > MIN_DXDY) {

							// Set new location

							wheeler.setXLoc(event.getX(0) - padding);
							wheeler.setYLoc(event.getY(0) - padding);

							marker.setXLoc(event.getX(0));
							marker.setYLoc(event.getY(0));

							// set color change
							double normalizedScreenHeight = event.getY(0)
									/ mDisplayHeight;
							// top is brighter and bottom is darker
							mValue = 1 - normalizedScreenHeight;

							// set new point 1 location
							p1.x = event.getX(0);
							p1.y = event.getY(0);

							p2.x = p1.x + xDiff;
							p2.y = p1.y + yDiff;

							// recalculate colors

							rgb = colorC.calculateHSVtoRGB(
									colorC.calculateAngleNormalized(),
									colorC.calculateDistanceNormalized(),
									mValue);
							marker.setColor(255, rgb[0], rgb[1], rgb[2]);

							// Request re-draw
							marker.invalidate();
							wheeler.invalidate();
						}
					if (event.getPointerCount() > 1) {
						//Log.i("Test", "Happy");
						if (Math.abs(marker.getXLoc() - event.getX(1)) > MIN_DXDY
								|| Math.abs(marker.getYLoc() - event.getY(1)) > MIN_DXDY) {
							p2.x = event.getX(1);
							p2.y = event.getY(1);

							// recalculate colors
							
							rgb = colorC.calculateHSVtoRGB(
									colorC.calculateAngleNormalized(),
									colorC.calculateDistanceNormalized(),
									mValue);
							marker.setColor(255, rgb[0], rgb[1], rgb[2]);
							
							
							//saving x and y diff
							xDiff = p2.x - p1.x ;
							yDiff = p2.y - p1.y;
							// Request re-draw
							marker.invalidate();
							wheeler.invalidate();

						}
					}
					// }
					// }

					break;
				}

				default:

					//Log.i(TAG, "unhandled action");
				}

				return true;
			}

		});
	}

	private class MarkerView extends View {
		private float mX, mY;
		// determines the size of the circle.
		final static private int MAX_SIZE = 550;
		// initially assuming touches to be zero.
		//private int mTouches = 0;
		// creating a paint object to draw on the canvas
		final private Paint mPaint = new Paint();

		// when color is not chosen
		public MarkerView(Context context, float x, float y) {
			super(context);
			mX = x + padding;
			mY = y + padding;
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

//		void setTouches(int touches) {
//			mTouches = touches;
//		}

		void setColor(int alpha, int red, int green, int blue) {
			mPaint.setARGB(220, red, green, blue);
		}

		// this is called before the view is projected onto the screen.
		// canvas ref is sent by the android system.
		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawCircle(mX, mY, MAX_SIZE, mPaint);
		}
	}

	private class WheelView extends View {
		private float mX, mY;
		// determines the size of the circle.
		//final static private int MAX_SIZE = 200;
		// initially assuming touches to be zero.
		//private int mTouches = 0;
		// creating a paint object to draw on the canvas
		final private Paint mPaint = new Paint();

		// when color is not chosen
		public WheelView(Context context, float x, float y) {
			super(context);
			mX = x + padding;
			mY = y + padding;
			mPaint.setStyle(Style.FILL);

			Random rnd = new Random();
			mPaint.setARGB(220, rnd.nextInt(256), rnd.nextInt(256),
					rnd.nextInt(256));
		}

		// when some color is chosen
		public WheelView(Context context, float x, float y, int red, int green,
				int blue) {
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

		/*void setTouches(int touches) {
			mTouches = touches;
		}

		String getType() {
			return "wheeler";

		}*/

		/*
		 * void setColor(int alpha, int red, int green, int blue) {
		 * mPaint.setARGB(220, red, green, blue); }
		 */

		// this is called before the view is projected onto the screen.
		// canvas ref is sent by the android system.
		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawBitmap(bitmap, mX, mY, mPaint);
		}
	}

}