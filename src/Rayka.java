package com.raykaad;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import com.raykaad.AdListener;
import com.raykaad.Banner;
import com.raykaad.Raykaad;
import com.raykaad.VideoAdListener;
import com.raykaad.VideoResult;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class Rayka extends CordovaPlugin {
	private static final String LOG_TAG = "Rayka";
	private static Activity mActivity = null;
	public CordovaInterface cordova = null;
	private FrameLayout bannerLayout;
	private static final int POSITION_TOP = 0;
	private static final int POSITION_BOTTOM = 1;
	private static final int POSITION_XY = 2;
	
	@Override
	public void initialize(CordovaInterface initCordova, CordovaWebView webView) {
		Log.e (LOG_TAG, "initialize");
		cordova = initCordova;
		mActivity = cordova.getActivity();
		super.initialize (cordova, webView);
	}
	
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext CallbackContext) throws JSONException {
		if (action.equals("addBanner")) {
			addBanner(action, args, CallbackContext);
			return true;
		}
		if (action.equals("removeBanner")) {
			removeBanner();
			return true;
		}
		if (action.equals("cachePopup")) {
			cachePopup(action, args, CallbackContext);
			return true;
		}
		if (action.equals("showPopup")) {
			showPopup(action, args, CallbackContext);
			return true;
		}
		if (action.equals("cacheVideo")) {
			cacheVideo(action, args, CallbackContext);
		    return true;
		}
		if (action.equals("showVideo")) {
			showVideo(action, args, CallbackContext);
		    return true;
		}
	    return false;
	}
	
	private void addBanner(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					final String zoneId = args.getString(0);
					final int position = args.getInt(1);
					if (bannerLayout != null) {
						removeBanner();
					}
					bannerLayout = new FrameLayout(mActivity);
				    FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(-2, -2);
				    switch (position) {
					    case POSITION_TOP:
					    	fLayoutParams.gravity = Gravity.TOP;
					    	break;
					    case POSITION_BOTTOM:
					    	fLayoutParams.gravity = Gravity.BOTTOM;
					    case POSITION_XY:
					    	fLayoutParams.leftMargin = args.getInt(2);
					    	fLayoutParams.topMargin = args.getInt(3);
				    }
				    bannerLayout.setLayoutParams(fLayoutParams);
				    ((ViewGroup) getParentGroup().getParent()).addView(bannerLayout, 1);
				    Banner banner = new Banner(mActivity, zoneId);
				    Raykaad.bannerSetListener(mActivity, new AdListener(){

						@Override
						public void onRequest() {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onBannerRequest");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}

						@Override
						public void onReady() {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onBannerReady");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}

						@Override
						public void onFail(String message) {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onBannerFail");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}

						@Override
						public void onShow() {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onBannerShow");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}

						@Override
						public void onClick() {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onBannerClick");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}

						@Override
						public void onClose() {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onBannerClose");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}
					});
				    bannerLayout.addView(banner);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void removeBanner(){
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (bannerLayout == null)
				      return;
			    if (mActivity != null) {
			    	mActivity.runOnUiThread(new Runnable() {
				        public void run() {
				        	ViewGroup viewGroup;
			      		if (((viewGroup = getParentGroup()) != null) && ((viewGroup instanceof ViewGroup)) && (((ViewGroup)viewGroup.getParent()).getChildAt(1) != null))
			      			((ViewGroup)viewGroup.getParent()).removeViewAt(1);
				        }
			    	});
			    }
			}
		});
	}
	
	private ViewGroup getParentGroup() {
	    try {
	      return (ViewGroup)this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
	    } catch (Exception ex) {
	    	try {
	    		return (ViewGroup)this.webView.getClass().getMethod("getParent", new Class[0]).invoke(this.webView, new Object[0]);
	    	} catch (Exception e) {
	    		e.printStackTrace(); 
	        }
	    }
	    return null;
	}

	private void cachePopup(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		final String zoneId = args.getString(0);
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
			    Raykaad.popupSetListener(mActivity, new AdListener(){
			    	@Override
					public void onClick() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onPopupClick");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onClose() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onPopupClose");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onFail(String message) {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onPopupFail");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onReady() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onPopupReady");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onRequest() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onPopupRequest");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onShow() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onPopupShow");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}
				});
				Raykaad.cachePopup(mActivity, zoneId);
			}
		});
	}
	
	private void showPopup(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				Raykaad.showPopup(mActivity);
			}
		});
	}
		
	private void cacheVideo(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		final String zoneId = args.getString(0);
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				Raykaad.setVideoListener(new VideoAdListener(){

					@Override
					public void onClick() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoClick");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onFail(String message) {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoFail");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onReady() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoReady");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onRequest() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoRequest");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}

					@Override
					public void onStart() {
						PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoStart");
						pr.setKeepCallback(true);
						callbackContext.sendPluginResult(pr);
					}
			    });
			    Raykaad.setVideoResultListener(new VideoResult(){

			    	@Override
					public void onResult(boolean complete) {
						if (complete) {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoComplete");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						} else {
							PluginResult pr = new PluginResult(PluginResult.Status.OK, "onVideoNotComplete");
							pr.setKeepCallback(true);
							callbackContext.sendPluginResult(pr);
						}
					}
				});
				Raykaad.cacheVideo(mActivity, zoneId);
			}
		});
	    
	}
	
	private void showVideo(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				Raykaad.showVideo(mActivity);
			}
		});
	}
}