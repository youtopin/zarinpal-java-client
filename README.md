# zarinpal-java-client

[![](https://jitpack.io/v/youtopin/zarinpal-java-client.svg)](https://jitpack.io/#youtopin/zarinpal-java-client)

Zarinpal Java Client is a *simple* library used for zarinpal gateway.  

It is called a simple library because it does not handle the status codes for you, and it only covers payment request and verification.

## Add to project

To add this library to your project, you fist have to add jitpack repository:

```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Then for add the dependency:

```
<dependency>
    <groupId>com.github.youtopin</groupId>
    <artifactId>zarinpal-java-client</artifactId>
    <version>1.0.0-snapshot</version>
</dependency>
```

If you are using gradle, sbt, or leiningen, see [Jitpack](https://jitpack.io/#youtopin/zarinpal-java-client/)

## Usage

This library does not force you to enter the `MerchantCode` directly into services.
Instead you should implement `com.youtopin.zarinpal.service.MerchantProvider` and return the `MerchantCode` in any ways that suits you.

Then you can easily use `ZarinpalService` by passing your implementation of `MerchantProvider` as constructor parameter. You are done.

Now, `ZarinpalService` has methods for payment request and verification.