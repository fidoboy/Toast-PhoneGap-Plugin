# PhoneGap Toast plugin for Android and iOS

by [Eddy Verbruggen](@eddyverbruggen)

## 0. Index

1. [Description](#1-description)
2. [Screenshots](#2-screenshots)
3. [Installation](#3-installation)
	3. [Automatically (CLI / Plugman)](#automatically-cli--plugman)
	3. [Manually](#manually)
	3. [PhoneGap Build](#phonegap-build)
4. [Usage](#4-usage)
5. [Credits](#5-credits)
6. [License](#6-license)

## 1. Description

This plugin allows you to show a native Toast (a little text popup) on iOS and Android.
It's great for showing a non intrusive native notification which is guaranteed always in the viewport of the browser.

* You can choose where to show the Toast: at the top, center or bottom of the screen.
* You can choose two durations: short (2 seconds), or long (5 seconds), after which the Toast automatically disappears.
* Compatible with [Cordova Plugman](https://github.com/apache/cordova-plugman).
* Pending official support by [PhoneGap Build](https://build.phonegap.com/plugins).

## 2. Screenshots

iOS 7

![ScreenShot](screenshot-ios-toast.png)

Android

![ScreenShot](screenshot-android-toast.png)

## 3. Installation

### Automatically (CLI / Plugman)
Toast is compatible with [Cordova Plugman](https://github.com/apache/cordova-plugman), compatible with [PhoneGap 3.0 CLI](http://docs.phonegap.com/en/3.0.0/guide_cli_index.md.html#The%20Command-line%20Interface_add_features), here's how it works with the CLI:

```
$ phonegap local plugin add https://github.com/EddyVerbruggen/Toast-PhoneGap-Plugin.git
```
or
```
$ cordova plugin add https://github.com/EddyVerbruggen/Toast-PhoneGap-Plugin.git
$ cordova prepare
```

Toast.js is brought in automatically. There is no need to change or add anything in your html.

### Manually

1\. Add the following xml to your `config.xml` in the root directory of your `www` folder:
```xml
<!-- for iOS -->
<feature name="Toast">
  <param name="ios-package" value="Toast" />
</feature>
```
```xml
<!-- for Android -->
<feature name="Toast">
  <param name="android-package" value="nl.xservices.plugins.Toast" />
</feature>
```

For iOS, you'll need to add the `QuartzCore.framework` to your project (it's for automatically removing the Toast after a few seconds).
Click your project, Build Phases, Link Binary With Libraries, search for and add `QuartzCore.framework`.

2\. Grab a copy of Toast.js, add it to your project and reference it in `index.html`:
```html
<script type="text/javascript" src="js/Toast.js"></script>
```

3\. Download the source files for iOS and/or Android and copy them to your project.

iOS: Copy the two `.h` and two `.m` files to `platforms/ios/<ProjectName>/Plugins`

Android: Copy `Toast.java` to `platforms/android/src/nl/xservices/plugins` (create the folders)

### PhoneGap Build (pending approval!)

Toast will soon work with PhoneGap build too, but only with PhoneGap 3.0 and up.

Just add the following xml to your `config.xml` to always use the latest version of this plugin:
```xml
<gap:plugin name="nl.x-services.plugins.toast" />
```
or to use this exact version:
```xml
<gap:plugin name="nl.x-services.plugins.toast" version="1.0" />
```

Toast.js is brought in automatically. There is no need to change or add anything in your html.

## 4. Usage
You have two choices to make when showing a Toast: where to show it and for how long.
* toast.show(message, duration, position)
- duration: short / long
- position: top / center / bottom

You can also use any of these convenience methods:
* toast.showShortTop(message)
* toast.showShortCenter(message)
* toast.showShortBottom(message)
* toast.showLongTop(message)
* toast.showLongCenter(message)
* toast.showLongBottom(message)

You can copy-paste these lines of code for a quick test:
```html
<button onclick="window.plugins.toast.shortTop('hi, this is cool!?', function(a){console.log('toast success: ' + a)}, function(b){alert('toast error: ' + b)})">Toast short top</button><br/><br/>
<button onclick="window.plugins.toast.longBottom('hi, this is cewl!', function(a){console.log('toast success: ' + a)}, function(b){alert('toast error: ' + b)})">Toast long bottom</button><br/><br/>
<button onclick="window.plugins.toast._show('hi!', 'bottom', 'xx_long', function(a){console.log('toast success: ' + a)}, function(b){alert('toast error: ' + b)})">Toast invalid options</button><br/><br/>
```

## 5. CREDITS ##

This plugin was enhanced for Plugman / PhoneGap Build by [Eddy Verbruggen](http://www.x-services.nl).
The Android code was entirely created by me.
I only has to slightly adjust [this excellent Toast project] (https://github.com/scalessec/Toast).

## 6. License

[The MIT License (MIT)](http://www.opensource.org/licenses/mit-license.html)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.