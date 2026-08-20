// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: lazy property initializes once
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
var count = 0

class Box {
    val x: Int by lazy {
        ++count
        42
    }
}

fun case_1() {
    checkSubtype<Int>(Box().let { it.x + it.x + count })
}
