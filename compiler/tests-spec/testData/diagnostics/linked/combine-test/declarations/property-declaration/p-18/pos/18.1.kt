// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: lazy property caches Int and exposes initializer counter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    var inits = 0
    val x: Int by lazy {
        inits++
        42
    }
}

fun case_1() {
    val b = Box()
    checkSubtype<Int>(b.x)
    checkSubtype<Int>(b.x)
    checkSubtype<Int>(b.inits)
}
