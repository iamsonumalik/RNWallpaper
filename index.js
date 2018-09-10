
import { NativeModules } from 'react-native';

const { RNWallpaper } = NativeModules;

const setWallpaper = (url, callback = (res) => {}) => {
    RNWallpaper.setWallpaper(url, callback)
}
export default {
    setWallpaper,
};
