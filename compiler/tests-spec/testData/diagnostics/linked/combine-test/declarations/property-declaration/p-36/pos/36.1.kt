// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: lateinit isInitialized property has type Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    lateinit var x: String
    fun isXInitialized(): Boolean = this::x.isInitialized
}

fun case_1() {
    val b = Box()
    checkSubtype<Boolean>(b.isXInitialized())
    b.x = "ok"
    checkSubtype<Boolean>(b.isXInitialized())
}
