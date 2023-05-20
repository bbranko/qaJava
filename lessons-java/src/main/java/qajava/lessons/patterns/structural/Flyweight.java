package qajava.lessons.patterns.structural;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Idea behind Flyweight pattern is data reuse, especially so, if data being otherwise duplicated is costly.
 * Since we are reducing "weight"(memory usage) of an object by minimizing data duplication, hence the name of end result as fly-weight.
 * <p>
 * advanced idea: we can dynamically create flyweights by pairing it with factory method that would internally store a collection
 * of different permutation of duplicated data. However, great care should be taken to ensure this collection is finite and with few elements
 * throughout a lifecycle of an application.
 * <p>
 * Caching - Flyweight pattern is implementation of a caching mechanism...
 */
public class Flyweight {
  //example code
  public static void main(String[] args) {
    //imagine we are making a bullet-hell game which at some point draws 10000 bullets on screen
    //each bullet has an image(sprite), color overlay, and x, y position associated with it

    //naive approach would be to create needed objects in its entirety each time, we can simulate it with following code:
    List<Particle> naiveBulletParticles = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      NaiveBulletParticles bullet = new NaiveBulletParticles(
        "pathToBullet.jpg",
        Color.RED,
        0,
        0
      );
      naiveBulletParticles.add(bullet);
    }
    //issue here is that all our bullets have same appearance, yet we are loading 10KB image for each bullet separately!
    //in case of 10000 bullets, 10000*10KB is almost 100MB memory usage for no reason at all!!!


    //Flyweight pattern approach would be to recognize that we only have that many sprites in our game (one in case of bullet),
    //load them once and then reuse them through the lifecycle of our app
    List<Particle> betterBulletParticles = new ArrayList<>();
    byte[] bulletImage = loadImage("pathToBullet.jpg");
    for (int i = 0; i < 10000; i++) {
      BetterBulletParticles bullet = new BetterBulletParticles(
        bulletImage, //we are now reusing same image over and over again
        Color.RED,
        0,
        0
      );
      betterBulletParticles.add(bullet);
    }
    //with reuse, our image size for all bullets stays the same no matter the amount of them!


    //advanced idea:
    //what if we implemented singleton ImageCache that all particle would use to get their images
    List<Particle> betterBetterBulletParticles = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      BetterBetterBulletParticles bullet = new BetterBetterBulletParticles(
        Color.RED,
        0,
        0
      );
      betterBetterBulletParticles.add(bullet);
    }
    //having ImageCache invoked during object construction to get a cached image data is only but one possible approach
    //Cache(Flyweight pattern) and it's behaviour (ie how long it lasts) should be implemented and adjusted per on case basis.
  }

  //...but this really should be in a specialized class
  private static byte[] loadImage(String image) {
    //Imagine here a code that loads our 10KB bullet image from provided path.
    //We simulate it by returning 10KB byte array.
    return new byte[10240];
  }
}

//common particle interface
interface Particle {
  //methods such as: updateLocation, draw, checkCollision etc...
}

//as stated, each bullet has an image(sprite), color overlay, and x, y position associated with it
class NaiveBulletParticles implements Particle {
  final byte[] image;
  Color color;
  int x, y;

  public NaiveBulletParticles(String pathToImage, Color color, int x, int y) {
    this.image = loadImage(pathToImage);
    this.color = color;
    this.x = x;
    this.y = y;
  }

  private byte[] loadImage(String pathToImage) {
    //Imagine here a code that loads our 10KB bullet image from provided path.
    //We simulate it by returning 10KB byte array.
    return new byte[10240];
  }
}

//Bullet class itself does not have to change all that much
class BetterBulletParticles implements Particle {
  final byte[] image;
  Color color;
  int x, y;

  public BetterBulletParticles(byte[] image, Color color, int x, int y) {
    this.image = image;
    this.color = color;
    this.x = x;
    this.y = y;
  }
}

//this is but only one possible approach to using cache manager
class BetterBetterBulletParticles implements Particle {
  //if path to image is always the same, we can set it up as constant
  final String PATH_TO_IMG = "pathToBullet.jpg";
  final byte[] image;
  Color color;
  int x, y;

  public BetterBetterBulletParticles(Color color, int x, int y) {
    this.image = ImageCache.getInstance().get(PATH_TO_IMG);
    this.color = color;
    this.x = x;
    this.y = y;
  }
}

//this is only but one possible implementation of ImageCache
class ImageCache {
  final Map<String, byte[]> pathToImageDataMap;

  private ImageCache() {
    this.pathToImageDataMap = new HashMap<>();
  }

  //see lesson about Singleton pattern if you do not recognize this ;)
  private static class InstanceHolder {
    final static ImageCache instance = new ImageCache();
  }

  public static ImageCache getInstance() {
    return InstanceHolder.instance;
  }

  public byte[] get(String pathToImage) {
    if (!pathToImageDataMap.containsKey(pathToImage)) {
      //if value is not yet cached we need to cache it...
      byte[] image = loadImage(pathToImage);
      pathToImageDataMap.put(pathToImage, image);
    }

    return pathToImageDataMap.get(pathToImage);
  }

  private byte[] loadImage(String pathToImage) {
    //Imagine here a code that loads image from provided path.
    //We simulate it by returning 10KB byte array.
    return new byte[10240];
  }
}
//note: be very careful with cache implementation that things cached are finite and relatively few.
//When caching dynamic but slow changing objects, some method of clearing the cache must be implemented!
//it could be time based, trigger based, based on idea similar to round buffer etc...
