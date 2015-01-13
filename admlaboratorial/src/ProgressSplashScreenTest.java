
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class ProgressSplashScreenTest {

  
  public static void main(final String[] args) throws InterruptedException {
//
//    final InputStream imageStream = ProgressSplashScreenTest.class.getResourceAsStream("/imagens/splash.png");
//    final ProgressSplashScreen splashScreen = new ProgressSplashScreen(imageStream);
//    splashScreen.showProgress(25);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen.showProgress(50);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen.showProgress(75);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen.showProgress(100);
//    splashScreen.close();
//
//    final InputStream imageStream2 = ProgressSplashScreenTest.class.getResourceAsStream("/imagens/splash.png");
//    final ProgressSplashScreen splashScreen2 = new ProgressSplashScreen(imageStream2, 1, 100);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen2.showProgress(25);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen2.showProgress(75);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen2.showProgress(100);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen2.close();
//
//    final InputStream imageStream3 = ProgressSplashScreenTest.class.getResourceAsStream("/imagens/splash.png");
//    final ProgressSplashScreen splashScreen3 = new ProgressSplashScreen(imageStream3);
//    splashScreen3.showProgress(25, 1);
//    TimeUnit.SECONDS.sleep(1);
//    splashScreen3.showProgress(25, 75, 2);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen3.showProgress(90, 2);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen3.showProgress(100, 2);
//    TimeUnit.SECONDS.sleep(2);
//    splashScreen3.close();
//splash.png
    final InputStream imageStream4 = ProgressSplashScreenTest.class.getResourceAsStream("/imagens/splash.png");
    final ProgressSplashScreen splashScreen4 = new ProgressSplashScreen(imageStream4, 1, 100);
    splashScreen4.showProgress(25, 1);
    TimeUnit.SECONDS.sleep(1);
    splashScreen4.showProgress(25, 75, 2);
    TimeUnit.SECONDS.sleep(2);
    splashScreen4.showProgress(90, 2);
    TimeUnit.SECONDS.sleep(2);
    splashScreen4.showProgress(100, 2);
    TimeUnit.SECONDS.sleep(2);
    splashScreen4.close();
  }
}