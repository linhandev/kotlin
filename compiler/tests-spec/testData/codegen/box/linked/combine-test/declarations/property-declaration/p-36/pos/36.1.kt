// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: lateinit isInitialized is false before init and true after
 */

// TESTCASE NUMBER: 1
class Box {
    lateinit var x: String
    fun isXInitialized(): Boolean = this::x.isInitialized
}

fun test(): Boolean {
    val b = Box()
    if (b.isXInitialized()) return false
    b.x = "ok"
    return b.isXInitialized()
}

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
