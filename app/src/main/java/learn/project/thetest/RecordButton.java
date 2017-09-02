package learn.project.thetest;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.io.IOException;

/**
 * Created by Dell_Pc on 2017/9/2.
 */

public class RecordButton extends android.support.v7.widget.AppCompatButton {

    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    boolean mStartRecording = true;

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onRecord(mStartRecording);
            if (mStartRecording) {
                setText("");//停止录音
               Toast.makeText(mRecordButton,"停止录音",1).show();
            } else {
                setText("");
                Toast.makeText(mRecordButton,"开始录音",1).show();
            }
            mStartRecording = !mStartRecording;
        }
    };

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }




    public RecordButton(Context context) {
        super(context);
        setText("");
        setOnClickListener(clicker);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
