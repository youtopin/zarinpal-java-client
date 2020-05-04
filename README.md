# zarinpal-java-client

[![](https://jitpack.io/v/youtopin/zarinpal-java-client.svg)](https://jitpack.io/#youtopin/zarinpal-java-client)

Zarinpal Java Client is a *simple* library used for zarinpal restful gateway.  

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


---

- Inspired by: [PHP Rest example](https://www.zarinpal.com/lab/%D9%86%D9%85%D9%88%D9%86%D9%87-%D8%B2%D8%B1%DB%8C%D9%86-%D9%BE%D8%A7%D9%84-%D8%B2%D8%A8%D8%A7%D9%86-php-rest/)
- Documentation: [Gateway Documentation](https://github.com/ZarinPal-Lab/Documentation-PaymentGateway)

---

# FAQ

- Q: Why is this documentation not written in persian?
    - Learn english!
    
- Q: Why using `MerchantProvider` when we could simply pass it as string?
    - Makes it more dynamic and easier to be changed later, but anyways that was how I needed it to be in our own project. You can change the code. Its very simple code!
