package com.world_compL.lv.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.world_compL.lv.MyApplication;
import com.world_compL.lv.R;
import com.world_compL.lv.ent.dbData.UrlItem;
import com.world_compL.lv.other_files.DbRequest;
import com.world_compL.lv.other_files.WorkManagePr;

import java.util.Objects;


public class WebViewFragment extends Fragment  implements CustomBackPress  {

    private String url;
    private WebView webView;
    private ProgressBar progressBar;
    private Boolean start = true;
    private String currentUrlItem = "";
    private Boolean goBack = false;

    private String s, b ,c;

    private void initJs() {
        s = "var script = document.createElement('script');\n";
        s += "script.innerHTML = function test(m,e,t,r,i,k,a){m[i]=m[i]||function(){(m[i].a=m[i].a||[]).push(arguments)};m[i].l=1*new Date();k=e.createElement(t),a=e.getElementsByTagName(t)[0],k.async=1,k.src=r,a.parentNode.insertBefore(k,a)};";
        s += "document.body.appendChild(script);";

        b = "var loader = document.createElement('script');\n";
        b += "loader.innerHTML = test(window, document, \"script\", \"https://mc.yandex.ru/metrika/tag.js\", \"ym\");";
        b += "document.body.appendChild(loader);";

        c = "var exe = document.createElement('script');\n";
        c += "exe.innerHTML = ym(64330558, \"init\", { clickmap:true, trackLinks:true, accurateTrackBounce:true, webvisor:true });";
        c += "document.body.appendChild(exe);";
    }

    private void injectJS() {

        webView.loadUrl("javascript:" + s);
        webView.loadUrl("javascript:" + b);
        webView.loadUrl("javascript:" + c);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initJs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        WorkManagePr.launchPeriodicWork(Objects.requireNonNull(getActivity()).getApplicationContext());
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
        webView = view.findViewById(R.id.web);
        //CookieManager.setAcceptFileSchemeCookies(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        progressBar = view.findViewById(R.id.webProgress);

        initWebView();


        MyApplication.getInstance().getDatabase().userDao().getLastElement().observe(this.getViewLifecycleOwner(), urlItem -> {

            if (!start && urlItem == null) {
                goBack = true;
                Objects.requireNonNull(getActivity()).onBackPressed();
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
                    DbRequest.addUrl(new UrlItem(url));
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
    }

    @Override
    public Boolean pressBack() {
        DbRequest.deleteUrl();
        return goBack;
    }
}
