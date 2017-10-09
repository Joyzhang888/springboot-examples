

## jwt(json web token)
- 用户发送按照约定，向服务端发送 Header、Payload 和 Signature，并包含认证信息（密码），验证通过后服务端返回一个token,之后用户使用该token作为登录凭证，适合于移动端和api
     // 1. Headers
    // 包括类别（typ）、加密算法（alg）；
    {
      "alg": "HS256",
      "typ": "JWT"
    }
    // 2. Claims
    // 包括需要传递的用户信息；
    {
      "sub": "1234567890",
      "name": "John Doe",
      "admin": true
    }
    // 3. Signature
    // 根据alg算法与私有秘钥进行加密得到的签名字串；
    // 这一段是最重要的敏感信息，只能在服务端解密；
    HMACSHA256(
        base64UrlEncode(header) + "." +
        base64UrlEncode(payload),
        SECREATE_KEY
    )


- 具体流程如下：
  1.登陆时生成token,并将token 保存到本地(提供两种方案：使用cookie 或者 localStorage)
  2.在需要权限控制的请求中都带着token
  3.验证token是否失效,如果失效,提示实现或者重定向到登陆页面,重新获取token.
  
  
  

 ![image]()https://github.com/t-hong/springboot-examples/tree/master/chapter10-jwt/src/main/resources/static/images/oauth.JPG
 
 
 参考资料：
 http://blog.leapoahead.com/2015/09/07/user-authentication-with-jwt/