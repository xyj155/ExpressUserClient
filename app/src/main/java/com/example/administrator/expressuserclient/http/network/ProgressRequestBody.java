package com.example.administrator.expressuserclient.http.network;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * Created by $wu on 2017-08-16 下午 4:00.
 * 带进度的上传
 */

public class ProgressRequestBody extends RequestBody {
    private File file;
    private ProgressListener listener;

    public ProgressRequestBody(File file, ProgressListener listener) {
        this.file = file;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return MultipartBody.FORM;
    }

    @Override
    public void writeTo(BufferedSink sink) {
        Source source = null;
        Buffer buffer = new Buffer();
        try {
            source = Okio.source(file);
            double hasRead = 0;
            for (long readCount; (readCount = source.read(buffer, 2048)) != -1; ) {
                sink.write(buffer, readCount);
                hasRead = hasRead + readCount;
                listener.progress((int) (hasRead / contentLength() * 100));
            }
            buffer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public long contentLength() throws IOException {
        return file.length();
    }


    /**
     * 关于进度的接口
     */
    public interface ProgressListener {
        void progress(int percent);
    }
}
