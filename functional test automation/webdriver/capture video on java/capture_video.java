import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class capture_video
{
	public static ScreenRecorder screenLogger = null;
	public static String pathToFile = "c:/videolog/";

	public static void main(String[] argv)
	{
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
	public static void initScreen()
	{
		// get the graphics configuration of the current screen
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration();

		// set screen recorder configuration
		try
		{
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
		}
		catch (IOException p_ex)
		{
			p_ex.printStackTrace();
		}
		catch (AWTException p_ex)
		{
			p_ex.printStackTrace();
		}
	}

	/**
	 * starts the screen recorder
	 */
	public static void startVideoCapturing()
	{
		try
		{
			screenLogger.start();
		}
		catch (IOException p_ex)
		{
			p_ex.printStackTrace();
		}
	}

	/**
	 * stops the screen recorder
	 */
	public static void stopVideoCapturing()
	{
		try
		{
			screenLogger.stop();
		}
		catch (Exception p_ex)
		{
			p_ex.printStackTrace();
		}
	}
}
