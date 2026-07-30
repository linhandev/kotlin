// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: reading uninitialized lateinit throws UninitializedPropertyAccessException
 */

// TESTCASE NUMBER: 1
class Box {
    lateinit var x: String
}

fun test(): String {
    return try {
        Box().x
        "NOK"
    } catch (e: kotlin.UninitializedPropertyAccessException) {
        "OK"
    }
}

fun box(): String {
    if (test() != "OK") return "NOK"
    return "OK"
}
