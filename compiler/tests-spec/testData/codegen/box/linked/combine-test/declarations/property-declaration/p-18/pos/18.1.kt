// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: lazy property initializer runs once and caches the value
 */

// TESTCASE NUMBER: 1
class Box {
    var inits = 0
    val x: Int by lazy {
        inits++
        42
    }
}

fun test(): Int {
    val b = Box()
    val a = b.x
    val c = b.x
    if (a != 42 || c != 42) return -1
    return b.inits
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
