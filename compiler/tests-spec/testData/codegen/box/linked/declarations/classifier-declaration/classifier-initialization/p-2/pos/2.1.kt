// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: secondary constructor body runs after primary initialization chain
 */

// TESTCASE NUMBER: 1
class A(val x: Int) {
    val log = mutableListOf<String>()

    init { log.add("primary") }

    constructor(s: String) : this(s.length) {
        log.add("secondary")
    }
}

fun box(): String {
    val a = A("abc")
    return if (a.x == 3 && a.log == listOf("primary", "secondary")) "OK" else "NOK: x=${a.x} log=${a.log}"
}
