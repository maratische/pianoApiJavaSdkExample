package com.example.piano

import com.tinypass.client.TinypassClientApi
import com.tinypass.client.id.model.PublisherLoginRequest
import com.tinypass.client.id.model.PublisherRegisterRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val appProperties: AppProperties,
) {

    fun index(): String {
        val api = TinypassClientApi("https://sandbox.tinypass.com/id/api/v1", appProperties.apiToken)
        val publisherIdentityApi = api.publisherIdentity()
        val publisherLoginRequest = PublisherLoginRequest()
        val register = PublisherRegisterRequest()
        register.aid = appProperties.aid
        register.email = UUID.randomUUID().toString() + "@aaa.com"
        register.password = "P@s#w0rd"
        register.firstName = "firstName"
        register.lastName = "lastName"
        val rgResponse = publisherIdentityApi.registerRequest().authorization(appProperties.apiToken).body(register).execute()
//        publisherLoginRequest.aid = app.aid
//        publisherLoginRequest.email = email
//        publisherLoginRequest.password = email
//        val pbResponse = publisherIdentityApi.loginRequest().authorization(app.apiToken).body(publisherLoginRequest).execute()
//        assertNotNull(pbResponse)
//        assertNotNull(pbResponse.accessToken)
        val identityTokenApi = api.identityToken()
        val response =
            identityTokenApi.tokenVerifySessionRequest().clientId(appProperties.aid).authorization(rgResponse.accessToken).execute()
        return response.toString()
    }

}
