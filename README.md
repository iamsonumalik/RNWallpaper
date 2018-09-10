
# RNWallpaper

## Getting started

`$ npm install RNWallpaper --save`

### Mostly automatic installation

`$ react-native link RNWallpaper`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import sonu.malik.wallpaper.RNWallpaperPackage;` to the imports at the top of the file
  - Add `new RNWallpaperPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':RNWallpaper'
  	project(':RNWallpaper').projectDir = new File(rootProject.projectDir, 	'../node_modules/RNWallpaper/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':RNWallpaper')
  	```


## Usage
```javascript
import Wallpaper from 'RNWallpaper';

// TODO: What to do with the module?
Wallpaper;
```
  