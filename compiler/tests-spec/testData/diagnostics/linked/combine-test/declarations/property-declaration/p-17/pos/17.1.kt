// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: lateinit var has non-null String type after assignment
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    lateinit var x: String
}

fun case_1() {
    val b = Box()
    b.x = "ok"
    checkSubtype<String>(b.x)
}
