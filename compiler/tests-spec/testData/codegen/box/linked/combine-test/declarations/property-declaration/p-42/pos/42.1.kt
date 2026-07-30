// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: setter calls other method for coercion
 */

// TESTCASE NUMBER: 1
class Box {
    fun validate(v: Int) = v.coerceAtLeast(0)
    var x: Int = 0
        set(value) {
            field = validate(value)
        }
}

fun test() = Box().apply { x = -5 }.x

fun box(): String {
    if (test() != 0) return "NOK"
    return "OK"
}
