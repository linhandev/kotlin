// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, getters-and-setters -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: default accessors read and write backing field at runtime
 */

// TESTCASE NUMBER: 1
class Holder {
    var value: Int = 1
        get
        set
}

fun box(): String {
    val h = Holder()
    h.value = 5
    return if (h.value == 5) "OK" else "NOK"
}
