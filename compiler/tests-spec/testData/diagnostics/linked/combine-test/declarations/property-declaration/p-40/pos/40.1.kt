// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: Delegates.notNull property has Int type after assignment
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    var x: Int by Delegates.notNull()
}

fun case_1() {
    val b = Box()
    b.x = 42
    checkSubtype<Int>(b.x)
}
