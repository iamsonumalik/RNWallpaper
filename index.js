
import { NativeModules } from 'react-native';

const { RNWallpaper } = NativeModules;

const setWallpaper = (url, callback = (res) => {}) => {
    RNWallpaper.setWallpaper(url, callback)
}

const openImage = (url) => {
    RNWallpaper.openImage(url)
}
export default {
    setWallpaper,
    openImage
};
