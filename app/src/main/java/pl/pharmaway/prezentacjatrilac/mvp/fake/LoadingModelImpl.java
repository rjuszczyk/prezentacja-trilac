package pl.pharmaway.prezentacjatrilac.mvp.fake;

import android.os.Handler;

import pl.pharmaway.prezentacjatrilac.mvp.Cancelable;
import pl.pharmaway.prezentacjatrilac.mvp.LoadingModel;

public class LoadingModelImpl implements LoadingModel {
    @Override
    public Cancelable checkUpdate(final CheckUpdateCallback checkUpdateCallback) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkUpdateCallback.onLoaded(true);
            }
        };
        handler.postDelayed(runnable, 2000);

        SimpleCancelable simpleCancelable = new SimpleCancelable(new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacks(runnable);
            }
        });

        return simpleCancelable;
    }

    @Override
    public Cancelable downloadDatabase(final DownloadDatabaseCallback downloadDatabaseCallback) {
        final SimpleCancelable simpleCancelable = new SimpleCancelable(new Runnable() {
            @Override
            public void run() {

            }
        });
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (double i = 0; i < 1; i+=0.1) {
                    if(simpleCancelable.isCanceled()) return;
                    final double finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            downloadDatabaseCallback.onProgress(finalI);
                        }
                    });
                    sleep(1000);
                }

                sleep(3000);
                if(simpleCancelable.isCanceled()) return;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadDatabaseCallback.onDownloaded();
                    }
                });
            }

            void sleep(long millis) {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        return simpleCancelable;
    }
}
