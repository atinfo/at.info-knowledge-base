import org.monte.media.Format;
import org.monte.media.FormatKeys.*;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureVideo {
	public static ScreenRecorder screenLogger = null;
	public static String pathToFile = "c:/videolog/";

	public static void main(String[] argv) {
		// initialize web driver
		WebDriver driver = new FirefoxDriver();
		driver.get("http://automated-testing.info");

		// capture video
		initScreen();
		startVideoCapturing();
		stopVideoCapturing();

		// initialize web driver
		driver.quit();
	}

	/**
	 * initializes a graphic configurations
	 */
	public static void initScreen() {
		// get the graphics configuration of the current screen
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration();

		// set screen recorder configuration
		try {
			screenLogger = new ScreenRecorder(gc, null,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							DepthKey, 24, FrameRateKey, Rational.valueOf(15),
							QualityKey, 1.0f,
							KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
							FrameRateKey, Rational.valueOf(30)),
					null, new File(pathToFile));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (AWTException awte) {
			awte.printStackTrace();
		}
	}

	/**
	 * starts the screen recorder
	 */
	public static void startVideoCapturing() {
		try {
			screenLogger.start();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * stops the screen recorder
	 */
	public static void stopVideoCapturing() {
		try {
			screenLogger.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
