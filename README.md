Rayka Cordova plugin
====================
Rayka ad service plugin for cordova, phonegap and ionic.<br/>

- Rayka android SDK 4.0.0<br/>
- Cordova version >3.0<br/>

## Install plugin ##

### Cordova cli ###
https://cordova.apache.org/docs/en/edge/guide_cli_index.md.html#The%20Command-Line%20Interface - npm install -g cordova@6.0.0
```c
cordova plugin add rayka-cordova-plugin
(when build error, use github url: cordova plugin add cordova plugin add https://github.com/vinoosir/rayka-cordova-plugin.git)
```

### Phonegap build service (config.xml) ###
https://build.phonegap.com/ - Apps - [specific project] - Update code - Zip file including config.xml
```c
<gap:plugin name="rayka-cordova-plugin" source="npm" />
```

### Construct2 ###
Download construct2 plugin<br>
http://dashboard.raykaad.com/index.php?do=additional&id=Construct


## Use Plugin ##

### 1.Add banner 

you can add the banner at the TOP/BOTTOM/XY of your app.
`windows.rayka.Position` holds  positions const.

```js
window.rayka.addBanner(window.rayka.Position.BOTTOM, 0, 0);
window.rayka.addBanner(window.rayka.Position.TOP, 0, 0);
window.rayka.addBanner(window.rayka.Position.XY, 10, 50);
```

### 2.Remove banner 

```js
window.rayka.removeBanner();
```

###  3.Show popup ad 
first cache popup ad.

```js
window.rayka.cachePopup("ZONE_ID");
```

to show popup ad use `window.rayka.showPopup();`.

```js
window.rayka.onPopupReady = function () {
	window.rayka.showPopup();
};
window.rayka.onPopupFail = function () {
	alert('Popup Not Ready.');
};
```

you can use this event:

```js
window.rayka.onPopupClick = function () {
	alert('onPopupClick');
};
window.rayka.onPopupClose = function () {
	alert('onPopupClose');
};
window.rayka.onPopupFail = function () {
	alert('onPopupFail');
};
window.rayka.onPopupReady = function () {
	alert('onPopupReady');
};
window.rayka.onPopupRequest = function () {
	alert('onPopupRequest');
};
window.rayka.onPopupShow = function () {
	alert('onPopupShow');
};
```

###  4.Show video ad
first cache video ad.

```js
window.rayka.cacheVideo("ZONE_ID");
```

to show video ad use this commands:

```js
window.rayka.showVideo();
```

```js
window.rayka.onVideoReady = function () {
	window.rayka.showVideo();
};
window.rayka.onVideoFail = function () {
	alert('onVideoFail.');
};
```

you can use this event:

```js
window.rayka.onVideoClick = function () {
	alert('onVideoClick');
};
window.rayka.onVideoFail = function () {
	alert('onVideoFail');
};
window.rayka.onVideoReady = function () {
	alert('onVideoReady');
};
window.rayka.onVideoRequest = function () {
	alert('onVideoRequest');
};
window.rayka.onVideoStart = function () {
	alert('onVideoStart');
};
window.rayka.onVideoComplete = function () {
	alert('onVideoComplete');
};
window.rayka.onVideoNotComplete = function () {
	alert('onVideoNotComplete');
};
```

## Examples ##
<a href="https://github.com/vinoosir/rayka-cordova-plugin/blob/master/example/index.html">Click to see!</a><br>