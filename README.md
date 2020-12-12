# loopj_http_client_example
- This repository demonstrates usage of loopj Android async http client with proxy. (https://mvnrepository.com/artifact/com.loopj.android/android-async-http, https://loopj.com/android-async-http/)


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
