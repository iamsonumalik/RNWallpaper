
# RNWallpaper

## Getting started

`$ npm install rnwallpaper --save`

### Mostly automatic installation

`$ react-native link rnwallpaper`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import sonu.malik.wallpaper.RNWallpaperPackage;` to the imports at the top of the file
  - Add `new RNWallpaperPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':rnwallpaper'
  	project(':rnwallpaper').projectDir = new File(rootProject.projectDir, 	'../node_modules/rnwallpaper/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':rnwallpaper')
  	```


## Usage
```javascript
import Wallpaper from 'rnwallpaper';

Wallpaper.setWallpaper('http://yourwebsite.com/anyImage.png', (res) => Alert.alert("Response: ", JSON.stringify(res)))

Wallpaper.openImage('content://media/external/images/media/Y')
```
  
