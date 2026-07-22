// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: init block runs between surrounding property initializers
 */

// TESTCASE NUMBER: 1
class C {
    val log = mutableListOf<String>()
    val a = log.apply { add("a") }.let { 1 }
    init { log.add("init") }
    val b = log.apply { add("b") }.let { 2 }
}

fun box(): String {
    val c = C()
    return if (c.log == listOf("a", "init", "b")) "OK" else "NOK: ${c.log}"
}
