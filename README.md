# loopj_http_client_example
- An Android or iOS app should be "proxy aware" for testing using BrowserStack features like BrowserStack Local testing or BrowserStack IP Whitelisting.
- This repository demonstrates usage of [loopj Android async http client](https://loopj.com/android-async-http/) in environments with a proxy.
- [Maven / Gradle](https://mvnrepository.com/artifact/com.loopj.android/android-async-http)


# Steps to run the code

- Clone the repository on your machine
- Open the Android project in Android studio
- Build the code to generate the .apk file and test it in an environment which has proxy.

# Observations / Results

- By default, loopj Android async http client does not auto detect the proxy. However, when the system proxy property values are set for the loopj http client object, then the proxy is detected by the loopj library and honoured for use.
- Example usage

```java
// Import statement for loopj http client
import com.loopj.android.http.*;
.
.
.
// fetching proxy configuration on the system
final String proxyHost = System.getProperty("http.proxyHost");
final String proxyPort = System.getProperty("http.proxyPort");
.
.
.
// loopj http client object
AsyncHttpClient client = new AsyncHttpClient();

// setting the proxy configuration for the loopj http client object if the proxy is present in the environment

if(proxyHost!=null && proxyPort!=null) {
  client.setProxy(proxyHost, Integer.parseInt(proxyPort));
}
```

Note: We have used the latest version of the loopj http library (V1.4.11) as of date which was released in June 2020. According to this [thread](https://github.com/android-async-http/android-async-http/issues/971), support for proxies was resolved in loopj in V1.4.10 which was released in July 2019.
