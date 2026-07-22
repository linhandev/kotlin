// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: using primary constructor does not invoke secondary constructor body
 */

// TESTCASE NUMBER: 1
class C(val x: Int) {
    companion object {
        var secondaryRan = false
    }

    constructor() : this(0) {
        secondaryRan = true
    }
}

fun box(): String {
    C.secondaryRan = false
    val c = C(42)
    return if (c.x == 42 && !C.secondaryRan) "OK" else "NOK: x=${c.x} secondaryRan=${C.secondaryRan}"
}
