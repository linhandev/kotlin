// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: notNull property delegate
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    var x: Int by Delegates.notNull()
}

fun test() = Box().apply { x = 42 }.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
