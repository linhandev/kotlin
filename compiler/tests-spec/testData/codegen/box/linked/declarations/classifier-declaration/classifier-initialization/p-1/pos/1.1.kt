// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: initialization order super then properties then init blocks
 */

// TESTCASE NUMBER: 1
open class Base {
    val log = mutableListOf<String>()
    init { log.add("base") }
}

class Child(val n: Int) : Base() {
    val a = log.apply { add("a") }.let { n }
    init { log.add("init") }
    val b = log.apply { add("b") }.let { n + 1 }
}

fun box(): String {
    val c = Child(5)
    return if (c.log == listOf("base", "a", "init", "b") && c.a == 5 && c.b == 6) "OK" else "NOK: ${c.log}"
}
