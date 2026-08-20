// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: custom getter without backing field infers Int and updates side state
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(var name: String) {
    var reads = 0
    val length: Int
        get() {
            reads++
            return name.length
        }
}

fun case_1() {
    val b = Box("hello")
    checkSubtype<Int>(b.length)
    checkSubtype<Int>(b.reads)
}
