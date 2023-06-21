package com.example.piano

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RequestMapping("/index")
@RestController
class IndexController(
    private val userService: UserService,
) {

    @GetMapping("/index")
    fun Index(
    ): String? {
        val user: String = userService.index()
        return user
    }

}
