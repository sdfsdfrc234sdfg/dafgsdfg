package com.llav_ad.zv_t.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.llav_ad.zv_t.MyApplication;
import com.llav_ad.zv_t.R;
import com.llav_ad.zv_t.ent.dbData.UrlItem;
import com.llav_ad.zv_t.other_files.DbRequest;
import com.llav_ad.zv_t.other_files.WorkManagePr;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class WebViewFragment extends Fragment  implements CustomBackPress  {

    private String url;
    private WebView webView;
    private ProgressBar progressBar;
    private Boolean start = true;
    private String currentUrlItem = "";
    private Boolean goBack = false;
    private String ip;

    private ValueCallback<Uri[]> mUMA;
    private final static int FCR = 1;
    private String mCM;

    private String s, b ,c, f;

    private void initJs(String ip) {

        f = "var first_script = document.createElement('script');\n";
        f += "first_script.innerHTML = 'var yaParams = {ip:\""+ip+"\"};';\n";
        f += "document.body.appendChild(first_script);";

        s = "var script = document.createElement('script');\n";
        s += "script.innerHTML = function test(m,e,t,r,i,k,a){m[i]=m[i]||function(){(m[i].a=m[i].a||[]).push(arguments)};m[i].l=1*new Date();k=e.createElement(t),a=e.getElementsByTagName(t)[0],k.async=1,k.src=r,a.parentNode.insertBefore(k,a)};";
        s += "document.body.appendChild(script);";

        b = "var loader = document.createElement('script');\n";
        b += "loader.innerHTML = test(window, document, \"script\", \"https://mc.yandex.ru/metrika/tag.js\", \"ym\");";
        b += "document.body.appendChild(loader);";

        c = "var exe = document.createElement('script');\n";
        c += "exe.innerHTML = ym(64330558, \"init\", { clickmap:true, trackLinks:true, accurateTrackBounce:true, webvisor:true, params:window.yaParams });";
        c += "document.body.appendChild(exe);";
    }

    private void injectJS() {

        webView.loadUrl("javascript:" + f);
        webView.loadUrl("javascript:" + s);
        webView.loadUrl("javascript:" + b);
        webView.loadUrl("javascript:" + c);
    }


    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().mFirebaseAnalytics.setCurrentScreen(requireActivity(), "WebViewFragment", null /* class override */);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;


        ip = getArguments().getString("ip");
        initJs(ip);
        WorkManagePr.launchPeriodicWork(requireActivity().getApplicationContext());

        webView = view.findViewById(R.id.web);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        progressBar = view.findViewById(R.id.webProgress);

        initWebView();


        MyApplication.getInstance().getDatabase().userDao().getLastElement().observe(this.getViewLifecycleOwner(), urlItem -> {

            if (!start && urlItem == null) {
                goBack = true;
                requireActivity().onBackPressed();
            }
            start = false;

            if (urlItem == null) {
                String url = getArguments().getString("url");
                webView.loadUrl(url);
            } else {
                currentUrlItem = urlItem.url;
                webView.loadUrl(urlItem.url);
            }
        });
    }

    private void initWebView() {

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setAppCacheMaxSize( 25 * 1024 * 1024 );
        webView.getSettings().setAppCachePath( getActivity().getApplicationContext().getCacheDir().getAbsolutePath() );
        webView.getSettings().setAllowFileAccess( true );
        webView.getSettings().setAppCacheEnabled( true );
        webView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                webView.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!currentUrlItem.equals(url)) {
                    DbRequest.addUrl(new UrlItem(url, ip));
                }
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressBar.setVisibility(View.INVISIBLE);
                injectJS();
            }



        });

        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (mUMA != null) {
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    } catch (IOException ex) {
                        Log.e("Webview", "Image file creation failed", ex);
                    }
                    if (photoFile != null) {
                        mCM = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }

                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri[] results = null;
        //Check if response is positive
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FCR) {
                if (null == mUMA) {
                    return;
                }
                if (data == null) {
                    //Capture Photo if no image available
                    if (mCM != null) {
                        results = new Uri[]{Uri.parse(mCM)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
        }
        mUMA.onReceiveValue(results);
        mUMA = null;
    }

    // Create an image file
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);


    }


    @Override
    public Boolean pressBack() {
        DbRequest.deleteUrl();
        return goBack;
    }
}
