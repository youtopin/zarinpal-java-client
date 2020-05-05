package com.youtopin.zarinpal.api;

import lombok.Getter;

public interface Address {
    String paymentRequestAddress();
    String startPayAddress();
    String paymentVerificationAddress();

    @Getter
    enum Environment {
        SANDBOX(new Address() {
            public String paymentRequestAddress() {
                return "https://sandbox.zarinpal.com/pg/rest/WebGate/PaymentRequest.json";
            }

            public String startPayAddress() {
                return "https://sandbox.zarinpal.com/pg/StartPay/";
            }

            public String paymentVerificationAddress() {
                return "https://sandbox.zarinpal.com/pg/rest/WebGate/PaymentVerification.json";
            }
        }), PRODUCTION(new Address() {
            public String paymentRequestAddress() {
                return "https://www.zarinpal.com/pg/rest/WebGate/PaymentRequest.json";
            }

            public String startPayAddress() {
                return "https://www.zarinpal.com/pg/StartPay/";
            }

            public String paymentVerificationAddress() {
                return "https://www.zarinpal.com/pg/rest/WebGate/PaymentVerification.json";
            }
        });
        private final Address address;

        Environment(Address address) {
            this.address = address;
        }
    }
}
