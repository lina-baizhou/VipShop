package com.lina.market_project.config;


import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置类
 */
@Component
public class AliPayConfig {

    // 1. 设置参数（全局只需设置一次）
    static {
        Factory.setOptions(getOptions());
    }

    private static Config getOptions() {
        Config config = new Config();

        config.protocol = "https";

        // 沙箱环境为 openapi.alipaydev.com
        config.gatewayHost = "openapi.alipaydev.com";

        config.signType = "RSA2";

        config.appId = "2021000118606837";

        config.merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCK41HOh1BGg6VvgPl67zd1UIoGeCOyQ/7Jd+SGLkBwMtdsdQcC6nhLIQtXEsIIVLKvCq2Ue6krwLc1gpH5DjIczMYX+Rap7v1wNu0+2wTKwb3IR8E7h9Wmp/yXthmfYG9IIUHtIVCg3d8nVu3fZwHwgwMy9BzorY8zVNnt7awOe5cT2pwViv1VXSlDlqUm1aqZe2DpuCYOeM/JHsFr0flYzvpenekafqfT3nZFPOlBtrFOwYa+f1QK7HNsEpGeDOaC6tkoGaiPY3fxtZy+v59JT/PUuC6sB4F5S3x9ontjEEGE4qt0bMhkpvTJmfB6tC4v10DOr13d8bPtQcKsJ/JNAgMBAAECggEAZ7IvaxbGTc+OnkDag/nFG1cFpqzeYgO4YG2edrxjqiGbi8OYd4neRKGoWNuNeTDbtCsxzFHDzgH2CAKzwBLkQXJ2WIHGdRwVp0mrOqnF2yhp7PX+BNd3IhX8Vr1ERiV3EJCcK1MbYvAk47dYpNrArPT0V3/BccvbxgSNLcKtGqX8GwTZ6sycUwqH17LjFpwOiYpsXgDCHMFj9FmTlkiFp0H/9bmoFHPcHCD9xvqDfCZ+TTnd7vWNL8IbSy+Jw0zRncsadPmKWC1j/xSp0hiCud8mf0teKGO3+RlepiV8HJXGOpiZFqEmsz0w/fJYyWvksS0LuCn4AYhMDQbYzbPJ1QKBgQDFVU/vDtIuNyUbjZsdotMnRSVbS7zsUewN0uR3YDca78WTyCRIwDb7jjbM3v8pd08KWj+WNnrTpkc7M8lqENJg78gnUMHmzafdglbJVuuAJF/zA2ZPNiDCS3TwGrt59AXZOfJlEdJouRk3LmHa/2+BEAzhLAeBAl1KUuNQu8iHHwKBgQC0LdbXcq8KYDmOQvouP0MwPpJbL+hb3dELngVikVoBKqtoxrGjwLb+Usf4C3jh53PIUJ0Q4CVLRxglxVO5HlTD5LejnYfiD0qQFtVepxiAwh5sbJYaunHOwq7APz3HoxZ3wZO68yMV7NEBY7kWUzb/5aPKB4GyCEjMdQ5MlKO1EwKBgC30Rh8n1m6IzQ1zlrH6Hy4//gkaxC5MjapSeH4MXAjb2akmrLx8dtFyNvTS4Z0oebGxfqlemo/7Yw84ailXLYcBkB0Hw4AaWINPPSK6KTG4Rtfy7Lp5ontc0yivMQWffFUwF3DOC6/zmxmspYE4hyEJOdaeSEkU4eDTGLjOYk6FAoGAWkUE+Bfq/mXLMCz6LqVfQhJ3PNw5q5zsGMfopsNvtlnsWqgBY6svgydyLCGDRJqy9U7+AfBqftZ1kKbWFU7O5P33yy8nXuiOULY/3BYXPpsCpwuYAHgcgJwk9Qg6KYTZwtHm86jQkTF+XRXbKwYx7e7dKmCsZ8eiwVx7934YrqUCgYBPSWRfi3dQdF3oher63et7Rgqf9FsweuQZ/1Gw5dKyHpydrILnuyrfi3EX/Ok0sgHlfsHjnKkENmm9R/oK90NGxHKj8R5X65QNa0px71hrt8F4Vkt3BkWN09ia65cD1L7O6tPHu3+1ah1ykwobJsDlYASbPbCocPSRuwUUkpRteA==";
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl0X4uwrQrSw6tuHjviz2a47DG7Gd6rALGanVqideSPy9RICpn6Q/kjp5HjxJeK6hGd/3B00UQQV1I8R7Sl3W+7qqOxMFqPEH5qawqd/An34hZtdOnnHNtbE5rM/Om/N/ZJL2C7KPAb7BAXrjzdnsTebqjrk49fl8y1oNyZkhRHFtkL48L7hTQWMpIuyryE+fOokWTnGaBI4dzKOA6mHeO5bLvm54x14PdaPiGcNKbqEPRHFIE33tZA7cylV2b/s+bVNHG7/jQv1DXtBe46hyFYCHlL7ZqAsvC2/1qG3RMx/ecjg4yxp6pGaBFZdl+m9/8IXrJKhBwntTEzYWjeh4LQIDAQAB";

        //可设置异步通知接收服务地址（可选）（该地址需要外网能够访问）
        config.notifyUrl = "https://21e7-119-39-226-226.ngrok.io/shop-order/notify_url";


        return config;
    }
}
