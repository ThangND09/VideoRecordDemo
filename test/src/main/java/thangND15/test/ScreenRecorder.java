package thangND15.test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class ScreenRecorder  {
	
	public static void main(String[] args) {
		ScreenRecorder screenRecorder = new ScreenRecorder();
		screenRecorder.startRecording("test");
		Scanner in = new Scanner(System.in);
		System.out.println("Enter any number to stop recording"); 
		int a = 0;
		a = in.nextInt(); 
		
		if (a != 0) {
			screenRecorder.stopRecording();
		}
	}
	
    private final Logger logger =  LoggerFactory.getLogger(ScreenRecorder.class);
    private static final String[] OPTIONS = {
            "--quiet",
            "--quiet-synchro",
            "--intf",
            "dummy"
    };

    private static final String MRL     = "screen://";
    private static final String SOUT    = ":sout=#transcode{vcodec=h264,vb=%d,scale=%f}:duplicate{dst=file{dst=%s}}";
    private static final String FPS     = ":screen-fps=%d";
    private static final String CACHING = ":screen-caching=%d";

    private static final int    fps     = 20;
    private static final int    caching = 500;
    private static final int    bits    = 1024;
    private static final float  scale   = 0.5f;

    private final MediaPlayerFactory mediaPlayerFactory;
    private final MediaPlayer mediaPlayer;

    public ScreenRecorder() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), System.getProperty("user.dir")+"/lib");
        System.setProperty("VLC_PLUGIN_PATH",  System.getProperty("user.dir")+"/lib/plugins");
        mediaPlayerFactory = new MediaPlayerFactory(OPTIONS);
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
    }

    public void startRecording(String testName) {
        String mp4FileName = getFile(testName);
        logger.info("start recording, "+ mp4FileName);
        mediaPlayer.playMedia(MRL, getMediaOptions(mp4FileName));
    }

    public void stopRecording() {
        logger.info("stop recording ");
        mediaPlayer.stop();
    }
    
    public void releaseRecordingResources() {
        mediaPlayer.release();
        mediaPlayerFactory.release();
    }

    private String getFile(String testName) {
        File dir = new File(System.getProperty("user.dir"), "mp4Result");
        dir.mkdirs();
        DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return dir.getAbsolutePath() + "/" + testName + "-" + df.format(new Date()) + ".mp4";
    }
    
    private String[] getMediaOptions(String destination) {
        return new String[] {
                String.format(SOUT, bits, scale, destination),
                String.format(FPS, fps),
                String.format(CACHING, caching)
        };
    }
}