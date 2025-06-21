package cn.woyioii.justtakeaway.util;

/**
 * 短信发送工具类
 * 注意：此类依赖阿里云SMS服务，需要添加相关依赖和配置
 */
public class SMSUtils {

    /**
     * 发送短信
     * 
     * @param signName     签名
     * @param templateCode 模板
     * @param phoneNumbers 手机号
     * @param param        参数
     */
    public static void sendMessage(String signName, String templateCode, String phoneNumbers, String param) {
        // 模拟发送短信，实际项目中需要配置阿里云SMS服务
        System.out.println("模拟发送短信到: " + phoneNumbers + ", 验证码: " + param);

        // 实际实现需要添加阿里云SMS依赖：
        // <dependency>
        // <groupId>com.aliyun</groupId>
        // <artifactId>dysmsapi20170525</artifactId>
        // <version>2.0.24</version>
        // </dependency>

        // 并配置AccessKey和Secret
        /*
         * DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
         * "accessKeyId", "accessKeySecret");
         * IAcsClient client = new DefaultAcsClient(profile);
         * 
         * SendSmsRequest request = new SendSmsRequest();
         * request.setSysRegionId("cn-hangzhou");
         * request.setPhoneNumbers(phoneNumbers);
         * request.setSignName(signName);
         * request.setTemplateCode(templateCode);
         * request.setTemplateParam("{\"code\":\""+param+"\"}");
         * try {
         * SendSmsResponse response = client.getAcsResponse(request);
         * System.out.println("短信发送成功");
         * }catch (ClientException e) {
         * e.printStackTrace();
         * }
         */
    }
}
